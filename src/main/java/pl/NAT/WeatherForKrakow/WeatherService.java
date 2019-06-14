package pl.NAT.WeatherForKrakow;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class WeatherService {

    @Autowired
    WeatherRepository weatherRepository;

    @Scheduled(fixedRate = 3600000)
    public void saveTemperature() {
        WeatherModel weatherModel = getRestTemplate().getForObject("http://api.openweathermap.org/data/2.5/weather?q=Kraków,pl&appid=03417069a46aa769d01e01c858da8ca6", WeatherModel.class);
        weatherRepository.save(new WeatherEntity(weatherModel.getMain().getTemp() - 273.15));

    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public double getAverageTemperature(Date startDate, Date endDate) {
        WeatherList weatherList = weatherRepository.findAllByDateBetween(startDate, endDate);
        double average = weatherList.weatherList.stream()
                .mapToDouble(s -> s.getTemperature())
                .average()
                .getAsDouble();

        return average;
    }
}
