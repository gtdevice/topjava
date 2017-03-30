package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by sony on 29.03.2017.
 */
public interface MealDao {
    void addMeal(Meal meal);
    void deleteMeal(int idMeal);
    void updateMeal(Meal meal);
    List<Meal> getAllMeals();

}
