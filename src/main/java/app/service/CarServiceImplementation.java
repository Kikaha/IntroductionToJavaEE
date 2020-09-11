package app.service;

import app.domain.entities.Car;
import app.domain.models.service.CarServiceModel;
import app.domain.repository.CarStorage;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImplementation implements CarService{
    private ModelMapper modelMapper;

    @Inject
    public CarServiceImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void createCar(CarServiceModel car) {
        CarStorage.addCar(this.modelMapper.map(car, Car.class));
    }

    @Override
    public List<CarServiceModel> allCars() {
        return CarStorage.getCars().stream().map(x->this.modelMapper.map(x,CarServiceModel.class)).collect(Collectors.toList());
    }
}
