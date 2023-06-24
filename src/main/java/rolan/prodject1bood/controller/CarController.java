package rolan.prodject1bood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rolan.prodject1bood.models.Car;
import rolan.prodject1bood.service.CarService;


import javax.validation.Valid;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carServiceList;

    @Autowired
    public CarController(CarService carServiceList) {
        this.carServiceList = carServiceList;
    }

    @GetMapping("")
    public String cars(Model model) {  // получим все автомобили
        model.addAttribute("cars", carServiceList.getAll());
        return "cars";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {  // получим машину по id
        model.addAttribute("car", carServiceList.getById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newCar(Model model) {
        model.addAttribute("car", new Car());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("car") @Valid Car car, //добавляем новую машину
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())               //проверяем на валидацию
            return "new";
        carServiceList.save(car);
        return "redirect:/cars"; //при добавлении возвращает нас на главную страницу с списком
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        ;
        model.addAttribute("car", carServiceList.getById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("car") @Valid Car car, //изменяем данные по машине
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())     //проверяем на валидацию
            return "edit";
        carServiceList.update(id, car);
        return "redirect:/cars";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) { //удаляем машину
        carServiceList.delete(id);

        return "redirect:/cars";
    }
}
