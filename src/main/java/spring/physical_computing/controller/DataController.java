package spring.physical_computing.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.physical_computing.controller.dto.WeatherRequest;
import spring.physical_computing.service.DataService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DataController {
    
    private final DataService dataService;
    
    @PostMapping("/home")
    public String postData(@RequestBody WeatherRequest data) {
        log.info("temp={} hum={}", data.getTemperature(), data.getHumidity());
        dataService.save(data);
        return "OK";
    }
}
