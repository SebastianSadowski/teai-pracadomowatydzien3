package pl.sadowski.sba2tydz2carapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Setter
@Getter
@ToString
public class Car {

     long id;
     String mark;
     String model;
     Color color;

}
