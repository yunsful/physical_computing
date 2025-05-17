package spring.physical_computing.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.physical_computing.controller.dto.WeatherRequest;
import spring.physical_computing.domain.WeatherData;
import spring.physical_computing.service.DataService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DataController {
    
    private final DataService dataService;
    
    @GetMapping("/home")
    public String getLatestData(Model model) {
        WeatherData latest = dataService.getLatest();
        model.addAttribute("weather", latest);
        return "home"; // resources/templates/home.html
    }
    
    @ResponseBody
    @PostMapping("/home")
    public String postData(@RequestBody WeatherRequest data) {
        dataService.save(data);
        return "OK";
    }
}
