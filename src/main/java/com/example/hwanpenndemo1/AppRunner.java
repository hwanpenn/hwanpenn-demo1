package com.example.hwanpenndemo1;

//import forezp.beans.Customer;
//import forezp.dao.BookRepository;
//import forezp.mongodbDao.CustomerRepository;
import com.example.hwanpenndemo1.beans.Customer;
//import com.example.hwanpenndemo1.dao.BookRepository;
import com.example.hwanpenndemo1.entity.Receiver;
import com.example.hwanpenndemo1.mongodbDao.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by fangzhipeng on 2017/4/19.
 */
@Component
public class AppRunner implements CommandLineRunner {
////rabbitmq
//    private final RabbitTemplate rabbitTemplate;
//    private final Receiver receiver;
//    private final ConfigurableApplicationContext context;
//
//    public AppRunner(Receiver receiver, RabbitTemplate rabbitTemplate,
//                  ConfigurableApplicationContext context) {
//        this.receiver = receiver;
//        this.rabbitTemplate = rabbitTemplate;
//        this.context = context;
//    }





    @Autowired
    private CustomerRepository repository;






    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

//    private final BookRepository bookRepository;
//
//    public AppRunner(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    @Override
    public void run(String... args) throws Exception {
//rabbitmq
//        System.out.println("Sending message...");
//        rabbitTemplate.convertAndSend(HwanpennDemo1Application.queueName, "Hello from RabbitMQ!");
//        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//        context.close();



        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));
        // fetch all customers
//        System.out.println("Customers found with findAll():");
//        System.out.println("-------------------------------");
//        for (Customer customer : repository.findAll()) {
//            System.out.println(customer);
//        }
//        System.out.println();
//        // fetch an individual customer
//        System.out.println("Customer found with findByFirstName('Alice'):");
//        System.out.println("--------------------------------");
//        System.out.println(repository.findByFirstName("Alice"));
//        System.out.println("Customers found with findByLastName('Smith'):");
//        System.out.println("--------------------------------");
//        for (Customer customer : repository.findByLastName("Smith")) {
//            System.out.println(customer);
//        }






//        logger.info(".... Fetching books");
//        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
//        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
//        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
//        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
//        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
//        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
    }

}