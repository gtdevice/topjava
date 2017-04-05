package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepository repository;

    @Override
    public List<MealWithExceed> getAll(int id, int calories) {
        List<Meal> mealList = (List<Meal>) repository.getAll(id);
        return MealsUtil.getWithExceeded(mealList, calories);
    }

    @Override
    public List<MealWithExceed> getFiltred(LocalDate startDate, LocalTime startTime
            , LocalDate endDate, LocalTime endTime, int id, int calories) {
        List<Meal> mealList = (List<Meal>) repository.getFiltred(startDate, endDate, id);
        return MealsUtil.getFilteredWithExceeded(mealList, startTime, endTime, calories);
    }


    @Override
    public Meal get(int id, int userId) {
        Meal meal = repository.get(id);
        MealsUtil.CheckMealUserId(meal, userId);
        return meal;
    }

    @Override
    public Meal create(Meal meal, int userId) {
        meal.setUserId(userId);
        repository.save(meal);
        return meal;
    }

    @Override
    public void delete(int id, int userId) {
        MealsUtil.CheckMealUserId(repository.get(id), userId);
        repository.delete(id);
    }

    @Override
    public void update(Meal meal, int userId) {
        MealsUtil.CheckMealUserId(meal, userId);
        repository.save(meal);
    }
}