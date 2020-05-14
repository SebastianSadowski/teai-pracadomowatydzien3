package pl.sadowski.sba2tydz2carapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import javax.validation.Payload;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@Setter
@Getter
@ToString
public class Car extends RepresentationModel<Car> {
     @NonNull long id;
     String mark;
     @NonNull
     String model;
     @NonNull
     Color color;

     public String getColorName() {
          return color.getColor();
     }


}
