package pl.poznan.put.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.poznan.put.entity.Dish;

@Repository
public interface  DishRepository extends CrudRepository<Dish,Long>{
	List<Dish> findByType(String type);
	List<Dish> findByName(String name);
}
