package com.example.hwanpenndemo1;

//import forezp.beans.Customer;
//import forezp.dao.BookRepository;
//import forezp.mongodbDao.CustomerRepository;

import com.example.hwanpenndemo1.beans.Customer;
import com.example.hwanpenndemo1.mongodbDao.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//import com.example.hwanpenndemo1.dao.BookRepository;

/**
 * Created by fangzhipeng on 2017/4/19.
 */
@Component
public class AppRunner1 implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AppRunner1.class);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            String quote = restTemplate.getForObject(
                    "http://gturnquist-quoters.cfapps.io/api/random", String.class);
            log.info("开始访问api");
            log.info(quote.toString());
        };
    }







    @Override
    public void run(String... args) throws Exception {







    }

}