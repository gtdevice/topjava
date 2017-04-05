package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 * Оставить в репозиторе только запрос к базе, логику и обработку перевести в сервис
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        LOG.info("save " + meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id) {
        LOG.info("Try delete Meal + " + id);
        if (repository.remove(id)!=null){
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id) {
        LOG.info("try to get meal + "+id);
        if (repository.get(id).getUserId()==AuthorizedUser.id())
            return repository.get(id);
        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        LOG.info("get all meal of user" + userId);
        List<Meal> meals = repository.values().stream()
                .filter(meal -> meal.getUserId()==userId)
                .collect(Collectors.toList());
        if (meals==null) return Collections.emptyList();
        Collections.sort(meals, (o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()));

        return meals;
    }

    @Override
    public Collection<Meal> getFiltred(LocalDate startDate, LocalDate endDate, int userId) {
        return getAll(userId).stream().filter(meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate))
                .collect(Collectors.toList());
    }

}

