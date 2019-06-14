package pl.NAT.WeatherForKrakow;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WeatherRepository extends CrudRepository<WeatherEntity,Integer> {


    @Query(nativeQuery = true, name= "SELECT * FROM weather WHERE date BETWEEN CONVERT(datetime, date=?1 ) AND CONVERT(datetime, date=?1 23:59:59:999")
    WeatherList findAllByDateBetween(Date startDate, Date endDate);

}
