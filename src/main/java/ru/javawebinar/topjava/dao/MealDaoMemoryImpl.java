package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sony on 29.03.2017.
 */
public class MealDaoMemoryImpl implements MealDao {
    ConcurrentHashMap<Integer, Meal> dbMeal;
    AtomicInteger currId = new AtomicInteger(0);

    private int getId(){
        return currId.addAndGet(1);
    }
    @Override
    public void addMeal(Meal meal) {
        meal.setId(getId());
        dbMeal.put(meal.getId(), meal);
    }

    @Override
    public void deleteMeal(int idMeal) {
        dbMeal.remove(idMeal);
    }

    @Override
    public void updateMeal(Meal meal) {
        dbMeal.put(meal.getId(), meal);
    }

    @Override
    public List<Meal> getAllMeals() {
        return (List)dbMeal.values();
    }
}
