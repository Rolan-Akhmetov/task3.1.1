package rolan.prodject1bood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rolan.prodject1bood.models.Car;
import rolan.prodject1bood.repositories.CarRepositories;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarService {

    private final CarRepositories carRepositories;

    @Autowired
    public CarService(CarRepositories carRepositories) {
        this.carRepositories = carRepositories;
    }

    public List<Car> getAll() {
        return carRepositories.findAll();
    }

    public Car getById(int id) {
        Optional<Car> carOptional = carRepositories.findById(id);
        return carOptional.orElse(null);
    }

    @Transactional
    public void save(Car car) {
        carRepositories.save(car);
    }

    @Transactional
    public void update(int id, Car updateCar) {
        updateCar.setId(id);
        carRepositories.save(updateCar);
    }

    @Transactional
    public void delete(int id) {
        carRepositories.deleteById(id);
    }
}
