package app.domain.repository;

import app.domain.entities.Car;

import java.util.ArrayList;
import java.util.List;

public final class CarStorage {
    private static List<Car> carsList = new ArrayList<>();

    public static void addCar(Car car){
        carsList.add(car);
    }

    public static List<Car> getCars(){
        return carsList;
    }

}
