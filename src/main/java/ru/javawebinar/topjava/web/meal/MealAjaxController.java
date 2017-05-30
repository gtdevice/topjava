package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by sony on 29.05.2017.
 */
@RequestMapping("/rest/profile/meals")
public class MealAjaxController extends AbstractMealController {

    @Autowired
    MealService mealService;

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") int id,
                               @RequestParam("dateTime") String localTime,
                               @RequestParam("description") String description,
                               @RequestParam("calories") int calories) {
        Meal meal = new Meal(LocalDateTime.parse(localTime), description, calories);
        if (meal.isNew()) {
            super.create(meal);
        } else {
            super.update(meal, id);
        }

    }
}
