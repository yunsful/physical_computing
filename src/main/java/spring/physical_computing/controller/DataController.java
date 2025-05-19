package spring.physical_computing.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.physical_computing.controller.dto.DataResponse;
import spring.physical_computing.controller.dto.WeatherRequest;
import spring.physical_computing.domain.WeatherData;
import spring.physical_computing.service.DataService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DataController {
    
    private final DataService dataService;
    private Boolean controlWindowOpen = false;
    private Boolean controlWindowClosed = false;
    private Boolean temperatureToggling = true;
    private Boolean humidityToggling = true;
    private Boolean soundToggling = true;
    
    @GetMapping("/home")
    public String getLatestData(Model model) {
        WeatherData latest = dataService.getLatest();
        model.addAttribute("weather", latest);
        model.addAttribute("temperatureToggling", temperatureToggling);
        model.addAttribute("humidityToggling", humidityToggling);
        model.addAttribute("soundToggling", soundToggling);
        return "home"; // resources/templates/home.html
    }
    
    @ResponseBody
    @PostMapping("/home")
    public DataResponse postData(@RequestBody WeatherRequest data) {
        dataService.save(data);
        DataResponse response = DataResponse.builder()
                .controlWindowOpen(controlWindowOpen)
                .controlWindowClosed(controlWindowClosed)
                .temperatureToggling(temperatureToggling)
                .humidityToggling(humidityToggling)
                .soundToggling(soundToggling)
                .build();
        
        if (controlWindowOpen) {
            controlWindowOpen = false;
        }
        if (controlWindowClosed) {
            controlWindowClosed = false;
        }
        
        return response;
    }
    
    @PostMapping("/temperature")
    public String toggleTemperature(Model model) {
        temperatureToggling = !temperatureToggling;
        model.addAttribute("toggle", temperatureToggling);
        return "redirect:/home";
    }
    
    @PostMapping("/humidity")
    public String toggleHumidity(Model model) {
        humidityToggling = !humidityToggling;
        model.addAttribute("toggle", humidityToggling);
        return "redirect:/home";
    }
    
    @PostMapping("/sound")
    public String toggleSound(Model model) {
        soundToggling = !soundToggling;
        model.addAttribute("toggle", soundToggling);
        return "redirect:/home";
    }
    
    @PostMapping("/open")
    public String controlWindowOpen() {
        controlWindowOpen = true;
        return "redirect:/home";
    }
    
    @PostMapping("/close")
    public String controlWindowClose() {
        controlWindowClosed = true;
        return "redirect:/home";
    }
}
