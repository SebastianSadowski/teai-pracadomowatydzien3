package pl.sadowski.sba2tydz2carapi.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sadowski.sba2tydz2carapi.model.Car;
import pl.sadowski.sba2tydz2carapi.repository.CarRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CarService {


    CarRepository carRepository;
    Logger logger = LoggerFactory.getLogger(CarService.class);


    @Autowired
    CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.getCarList();
    }

    public Optional<Car> getCarById(long id) {
        return carRepository.getCarList().stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    public List<Car> getCarsByColor(String color) {
        List<Car> cars = getAllCars().stream()
                .filter(car -> car.getColorName().equals(color))
                .collect(Collectors.toList());

        cars.forEach(c -> logger.info(c.toString()));
        return cars;
    }

    public boolean addCar(Car car) {
        return carRepository.getCarList().add(car);

    }

    public boolean removeCar(long id) {
        return getCarById(id).map(car -> carRepository.getCarList().remove(car)).get();
    }

}
