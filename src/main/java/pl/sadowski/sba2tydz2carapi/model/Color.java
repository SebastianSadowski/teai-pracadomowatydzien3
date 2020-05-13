package pl.sadowski.sba2tydz2carapi.model;

import lombok.Getter;
import lombok.ToString;

@ToString
public enum Color {

    WHITE("white"),
    BLACK("black"),
    GREEN("green"),
    YELLOW("yellow");


@Getter
    private final String color;
    Color(String color){
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }
}
