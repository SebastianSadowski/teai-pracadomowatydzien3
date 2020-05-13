package pl.sadowski.sba2tydz2carapi.Controller;


import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import pl.sadowski.sba2tydz2carapi.Service.CarService;
import pl.sadowski.sba2tydz2carapi.model.Car;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarsController {

    CarService carService;

    @Autowired
    CarsController(CarService carService) {
        this.carService = carService;
    }

//    @GetMapping("/")
//    public ResponseEntity<List<Car>> getAll() {
//
//        return carService.getAllCars().isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(carService.getAllCars(), HttpStatus.FOUND);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> carById = carService.getCarById(id);
        carById.ifPresent(car -> car.addIf(!car.hasLinks(), () -> linkTo(CarsController.class).slash(car.getId()).withSelfRel()));
        return carById
                .map(car -> new ResponseEntity<>(carById.get(), HttpStatus.FOUND))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/")
    public ResponseEntity<List<Car>> getCar(@Nullable @RequestParam(name = "color") Optional<String> color) {
        List<Car> cars = color.isPresent() ? carService.getCarsByColor(color.get()) : carService.getAllCars();
        cars.forEach(car -> {
            car.addIf(!car.hasLinks(), () -> linkTo(CarsController.class).slash(car.getId()).withSelfRel());
            car.addIf(!car.hasLink("Cars with the same color"),() -> linkTo(CarsController.class).slash("/?color=" + car.getColorName()).withRel("Cars with the same color"));
        });

        cars.forEach(car -> System.out.println(car.getLinks()));
        return !cars.isEmpty() ? new ResponseEntity<>(cars, HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {

        return carService.getCarById(car.getId()).isEmpty() ?
                carService.addCar(car) ? new ResponseEntity<>(car, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("/")
    public ResponseEntity<Car> modifyCar(@RequestBody Car car) {
        return carService.removeCar(car.getId()) ?
                carService.addCar(car) ? new ResponseEntity<>(car, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable long id) {
        return carService.getCarById(id)
                .map(ca -> carService.removeCar(ca.getId()) ? new ResponseEntity<>(ca, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
