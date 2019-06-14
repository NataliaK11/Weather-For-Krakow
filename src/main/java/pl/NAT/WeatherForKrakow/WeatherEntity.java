package pl.NAT.WeatherForKrakow;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="weather")
public class WeatherEntity {
    @Id
    @GeneratedValue
    private int id;
    private LocalDateTime date;
    private double temperature;

    public WeatherEntity(double temperature){
        this.temperature=temperature;
    }
}
