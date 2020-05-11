package pl.sadowski.sba2tydz2carapi.repository;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import pl.sadowski.sba2tydz2carapi.model.Car;

import java.util.ArrayList;
import java.util.List;

@Repository

public class CarRepository {


    @Getter
    @Setter
    public List<Car> carList;

    CarRepository(){
        carList = new ArrayList<>();
    }

    @EventListener(ApplicationReadyEvent.class)
    void initializeCarList(){
        carList.add(new Car(1L, "AUDI", "A6", "RED"));
        carList.add(new Car(2L, "AUDI", "A5", "BLUE"));
        carList.add(new Car(3L, "OPEL", "Astra", "WHITE"));
    }
}
