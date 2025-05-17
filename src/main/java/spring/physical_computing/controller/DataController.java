package spring.physical_computing.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.physical_computing.controller.dto.WeatherRequest;

@Slf4j
@RestController
public class DataController {
    
    @PostMapping("/home")
    public String postData(@RequestBody WeatherRequest data) {
        log.info("temp={} hum={}", data.getTemperature(), data.getHumidity());
        return "OK";
    }
}
