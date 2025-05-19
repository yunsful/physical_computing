package spring.physical_computing.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.physical_computing.controller.dto.WeatherRequest;
import spring.physical_computing.domain.WeatherData;
import spring.physical_computing.repository.DataRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DataService {

    private final DataRepository dataRepository;
    
    public void save(WeatherRequest request) {
        
        WeatherData data = WeatherData.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .temperature(request.getTemperature())
                .humidity(request.getHumidity())
                .sound(request.getSound())
                .build();
        
        dataRepository.save(data);
        log.info("temp={} hum={} sound={}", data.getTemperature(), data.getHumidity(), data.getSound());
    }
    
    public WeatherData getLatest() {
        return dataRepository.findTopByOrderByDateDescTimeDesc()
                .orElse(null);
    }

}
