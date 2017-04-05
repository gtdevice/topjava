package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface MealService {
    List<MealWithExceed> getAll(int id, int calories);

    List<MealWithExceed> getFiltred(LocalDate startDate, LocalTime startTime, LocalDate endDate
            , LocalTime endTime, int id, int calories);

    Meal get(int id, int userId);

    Meal create(Meal meal, int id);

    void delete(int id, int userId);

    void update(Meal meal, int userId);
}