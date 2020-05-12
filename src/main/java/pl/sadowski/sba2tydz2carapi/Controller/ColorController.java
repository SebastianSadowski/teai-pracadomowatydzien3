package pl.sadowski.sba2tydz2carapi.Controller;


import io.swagger.models.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sadowski.sba2tydz2carapi.Service.CarService;
import pl.sadowski.sba2tydz2carapi.model.Car;
import pl.sadowski.sba2tydz2carapi.model.Color;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/colors", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ColorController {

    CarService carService;
Logger logger = LoggerFactory.getLogger(ColorController.class);
    @Autowired
    ColorController(CarService carService){
        this.carService = carService;
    }


    @GetMapping("/")
    public ResponseEntity<List<String>> getColorOfAvailableCars(){
        List<String> collect = carService.getAllCars().stream()
                .map(Car::getColor)
                .map(Color::getColor)
                .collect(Collectors.toList());

        return collect.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
                :new ResponseEntity<>(collect, HttpStatus.FOUND);
    }
}
