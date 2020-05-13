package pl.sadowski.sba2tydz2carapi.model;

import lombok.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;


@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class Car extends RepresentationModel<Car> {

     @NonNull long id;
     @NonNull String mark;
     @NonNull String model;
     @NonNull Color color;
     private Link link;

     public String getColorName() {
          return color.getColor();
     }


}
