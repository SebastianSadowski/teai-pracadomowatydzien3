package pl.sadowski.sba2tydz2carapi.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import pl.sadowski.sba2tydz2carapi.Service.CarService;
import pl.sadowski.sba2tydz2carapi.model.Car;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/cars")
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
        return carService.getCarById(id)
                .map(ca -> new ResponseEntity<>(ca, HttpStatus.FOUND))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public ResponseEntity<List<Car>> getCar(@Nullable @RequestParam Optional<String> color) {
        return color.isEmpty()?
         carService.getAllCars().isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(carService.getAllCars(), HttpStatus.FOUND):
         !carService.getCarsByColor(color.get()).isEmpty() ? new ResponseEntity<>(carService.getCarsByColor(color.get()), HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        return carService.getCarById(id).isPresent() ?
                carService.removeCar(id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
