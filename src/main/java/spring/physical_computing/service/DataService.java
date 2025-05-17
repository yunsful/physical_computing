package spring.physical_computing.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.physical_computing.controller.dto.WeatherRequest;
import spring.physical_computing.domain.WeatherData;
import spring.physical_computing.repository.DataRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class DataService {

    private final DataRepository dataRepository;
    
    public void save(WeatherRequest request) {
        
        WeatherData data = WeatherData.builder()
                .temperature(request.getTemperature())
                .humidity(request.getHumidity())
                .build();
        
        dataRepository.save(data);
    }

}
