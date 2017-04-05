package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.user.ProfileRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkIdConsistent;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll() {
        return service.getAll(AuthorizedUser.id(), AuthorizedUser.getCaloriesPerDay());
    }

    //отдать еду отфильтрованную
    public List<MealWithExceed> getFiltred(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        startDate = (startDate != null ? startDate : startDate.MIN);
        startTime = (startTime != null ? startTime : startTime.MIN);
        endDate = (endDate != null ? endDate : endDate.MAX);
        endTime = (endTime != null ? endTime : endTime.MAX);
        return service.getFiltred(startDate, startTime, endDate, endTime, AuthorizedUser.id()
                , AuthorizedUser.getCaloriesPerDay());
    }

    //Получить еду, проверив айди
    public Meal get(int id) {
        return service.get(id, AuthorizedUser.id());
    }

    public Meal create(Meal meal) {
        return service.create(meal, AuthorizedUser.id());
    }

    public void delete(int id) {
        service.delete(id, AuthorizedUser.id());
    }

    public void update(Meal meal) {
        service.update(meal, AuthorizedUser.id());
    }

}