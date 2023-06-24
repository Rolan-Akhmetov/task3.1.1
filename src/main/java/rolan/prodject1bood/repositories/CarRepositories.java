package rolan.prodject1bood.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rolan.prodject1bood.models.Car;

@Repository
public interface CarRepositories extends JpaRepository<Car,Integer> {
}
