package com.example.hwanpenndemo1.restControllers;

//import com.example.libary.Service;
//import com.example.libary.Service;
import com.example.libary.Service;
import com.example.libary.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Import(ServiceConfiguration.class)
@RestController
public class HelloController {

//    @Autowired
//    private Service service;
    private final Service service;

    @Autowired
    public HelloController(Service service) {
        this.service = service;
    }

    @GetMapping("/module")
    public String module() {
        System.out.println("here is module");
        return service.message();
    }

    @RequestMapping("/restTest")
    public String restTest(){
        return "rest return json";
    }
}
