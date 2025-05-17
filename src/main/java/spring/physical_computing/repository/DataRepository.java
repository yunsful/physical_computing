package spring.physical_computing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.physical_computing.domain.WeatherData;

import java.util.Optional;

public interface DataRepository extends JpaRepository<WeatherData, Long> {
    
    Optional<WeatherData> findTopByOrderByDateDescTimeDesc();
    
}
