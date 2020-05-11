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

    public long id;
    public String mark;
    public String model;
    public String color;

}
