package com.example.hwanpenndemo1;

import com.example.hwanpenndemo1.beans.Customer;
import com.example.hwanpenndemo1.entity.Receiver;
import com.example.hwanpenndemo1.mongodbDao.CustomerRepository;
import com.example.libary.ServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
//redis mysql rabbitmq可以在windows服务里启动
//启动rabbitmq 安装目录下rabbitmq start_app  mongodb也是安装目录下mongod
//高并发可以考虑使用锁机制，1.代码层面的同步锁 synchronized 限制同一个对象的访问 缺点分布式无法生效
//2.数据库层面的锁 悲观锁和乐观锁 悲观锁为数据库自带 只要加上for update 便可以限制该表的这个字段的读取和操作
//缺点1000人的抢购一张票必须都要能获取票有一张的信息，但是只有一个人能操作，这种情况下悲观锁无法都读取数据
//乐观锁 数据加一个字段version  update t_order set qty=1 and version=version+1 where version=:version
//并发搞得情况下 数据库io压力非常大  可以考虑使用缓存（内存） 例如redis memcached 优先选用redis 可以支持持久化 值得是吧数据从内存中写入
//磁盘中  spring原理ioc和aop ioc控制反转简单说就是bean得控制权交给spring 不用每次都去new  aop简单说就是每次做一件事之前都先去做另一件事
//比如每次操作数据库都去写个日志或者检查事务 原理是包装类或者代理类
//nodejs 有事异步非阻塞   普通web服务的设计都是阻塞的  采用的是一个http请求开一个线程的方式
//多线程的劣势，线程切换，需要消耗cpu和内存，
//并发问题如果出现在请求层（高并发导致内存很容易爆，或者cpu处理不过来），可以使用集群把请求分发到不同的服务器，项目做分布式处理
// 项目架构可以使用springcloud搭建为服务，易扩展，那里压力大，哪里加机器，如果是数据层，也可以把数据库的拆分，做分布式数据库
//机器学习也一样，计算能力不够的情况下，可以采用分布式计算spark或者hadoop，

//@Import(ServiceConfiguration.class)
@SpringBootApplication
@EnableScheduling
@EnableAsync
//@EnableCaching
public class HwanpennDemo1Application extends AsyncConfigurerSupport {

//	final static String queueName = "hello";
//
//	@Bean
//	public Queue helloQueue() {
//		return new Queue("hello");
//	}
//
//	@Bean
//	public Queue userQueue() {
//		return new Queue("user");
//	}
//
//	//===============以下是验证topic Exchange的队列==========
//	@Bean
//	public Queue queueMessage() {
//		return new Queue("topic.message");
//	}
//
//	@Bean
//	public Queue queueMessages() {
//		return new Queue("topic.messages");
//	}
//	//===============以上是验证topic Exchange的队列==========
//
//
//	//===============以下是验证Fanout Exchange的队列==========
//	@Bean
//	public Queue AMessage() {
//		return new Queue("fanout.A");
//	}
//
//	@Bean
//	public Queue BMessage() {
//		return new Queue("fanout.B");
//	}
//
//	@Bean
//	public Queue CMessage() {
//		return new Queue("fanout.C");
//	}
//	//===============以上是验证Fanout Exchange的队列==========
//
//
//	@Bean
//	TopicExchange exchange() {
//		return new TopicExchange("exchange");
//	}
//	@Bean
//	FanoutExchange fanoutExchange() {
//		return new FanoutExchange("fanoutExchange");
//	}
//
//	/**
//	 * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
//	 * @param queueMessage
//	 * @param exchange
//	 * @return
//	 */
//	@Bean
//	Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
//		return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
//	}
//
//	/**
//	 * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
//	 *  @param queueMessages
//	 * @param exchange
//	 * @return
//	 */
//	@Bean
//	Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
//		return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
//	}
//
//	@Bean
//	Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
//		return BindingBuilder.bind(AMessage).to(fanoutExchange);
//	}
//
//	@Bean
//	Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
//		return BindingBuilder.bind(BMessage).to(fanoutExchange);
//	}
//
//	@Bean
//	Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
//		return BindingBuilder.bind(CMessage).to(fanoutExchange);
//	}
//
//
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(HwanpennDemo1Application.class);
//	@Bean
//	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
//											MessageListenerAdapter listenerAdapter) {
//
//		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
//		container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
//
//		return container;
//	}
//	@Bean
//	MessageListenerAdapter listenerAdapter(Receiver receiver) {
//		return new MessageListenerAdapter(receiver, "receiveMessage");
//	}
//	@Bean
//	Receiver receiver(CountDownLatch latch) {
//		return new Receiver(latch);
//	}
//	@Bean
//	CountDownLatch latch() {
//		return new CountDownLatch(1);
//	}
//	@Bean
//	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
//		return new StringRedisTemplate(connectionFactory);
//	}

//	final static String queueName = "spring-boot";
//	@Bean
//	Queue queue() {
//		return new Queue(queueName, false);
//	}
//	@Bean
//	TopicExchange exchange() {
//		return new TopicExchange("spring-boot-exchange");
//	}
//	@Bean
//	Binding binding(Queue queue, TopicExchange exchange) {
//		return BindingBuilder.bind(queue).to(exchange).with(queueName);
//	}
//	@Bean
//	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//											 MessageListenerAdapter listenerAdapter) {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
//		container.setQueueNames(queueName);
//		container.setMessageListener(listenerAdapter);
//		return container;
//	}
//	@Bean
//	MessageListenerAdapter listenerAdapter(Receiver receiver) {
//		return new MessageListenerAdapter(receiver, "receiveMessage");
//	}

//implements CommandLineRunner
	public static void main(String[] args) throws Exception {
		SpringApplication.run(HwanpennDemo1Application.class, args);
//		ApplicationContext ctx =  SpringApplication.run(HwanpennDemo1Application.class, args);
//
//		StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
//		CountDownLatch latch = ctx.getBean(CountDownLatch.class);
//		LOGGER.info("Sending message...");
//		template.convertAndSend("chat", "Hello from Redis!");
//		latch.await();
//		System.exit(0);
	}


	//mongodb 启动mongodb--安装目录下运行mongod.exe即可
	//运行方法需要类继承CommandLineRunner
//	@Autowired
//		private CustomerRepository repository;
//		@Override
//		public void run(String... args) throws Exception {
//			repository.deleteAll();
//
//			// save a couple of customers
//			repository.save(new Customer("Alice", "Smith"));
//			repository.save(new Customer("Bob", "Smith"));
//
//			// fetch all customers
//			System.out.println("Customers found with findAll():");
//			System.out.println("-------------------------------");
//			for (Customer customer : repository.findAll()) {
//				System.out.println(customer);
//			}
//			System.out.println();
//
//			// fetch an individual customer
//			System.out.println("Customer found with findByFirstName('Alice'):");
//			System.out.println("--------------------------------");
//			System.out.println(repository.findByFirstName("Alice"));
//
//			System.out.println("Customers found with findByLastName('Smith'):");
//			System.out.println("--------------------------------");
//			for (Customer customer : repository.findByLastName("Smith")) {
//				System.out.println(customer);
//			}
//	}


	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("GithubLookup-");
		executor.initialize();
		return executor;
	}
}
