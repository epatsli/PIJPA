package pl.poznan.put.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.poznan.put.entity.Dish;
import pl.poznan.put.repository.DishRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DishController {

	@Autowired
	DishRepository repository;

	@GetMapping("/dishes")
	public List<Dish> getAllDishes() {
		System.out.println("Get all Dishes...");

		List<Dish> dishes = new ArrayList<>();
		repository.findAll().forEach(dishes::add);

		return dishes;
	}

	@PostMapping(value = "/dishes/create")
	public Dish postDish(@RequestBody Dish dish) {

		Dish _dish = repository.save(new Dish().builder().withName(dish.getName()).withPrice(dish.getPrice())
				.withType(dish.getType()).withDescription(dish.getDescription()).build());
		return _dish;
	}

	@DeleteMapping("/dishes/{id}")
	public ResponseEntity<String> deleteDish(@PathVariable("id") Long id) {
		System.out.println("Delete Dish with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Dish has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/dishes/delete")
	public ResponseEntity<String> deleteAllDishes() {
		System.out.println("Delete All Dishes...");

		repository.deleteAll();

		return new ResponseEntity<>("All Dishes have been deleted!", HttpStatus.OK);
	}

	@GetMapping(value = "dishes/name/{name}")
	public List<Dish> findByName(@PathVariable String name) {

		List<Dish> dishes = repository.findByName(name);
		return dishes;
	}
	
	@GetMapping(value = "dishes/type/{type}")
	public List<Dish> findByPrice(@PathVariable String type) {

		List<Dish> dishes = repository.findByType(type);
		return dishes;
	}

	@PutMapping("/dishes/{id}")
	public ResponseEntity<Dish> updateDish(@PathVariable("id") Long id, @RequestBody Dish dish) {
		System.out.println("Update Dish with ID = " + id + "...");

		Optional<Dish> dishData = repository.findById(id);

		if (dishData.isPresent()) {
			Dish _dish = dishData.get();
			_dish.setName(dish.getName());
			_dish.setPrice(dish.getPrice());
			_dish.setType(dish.getType());
			_dish.setDescription(dish.getDescription());
			_dish.setAvailable(dish.isAvailable());
			return new ResponseEntity<>(repository.save(_dish), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
