package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;
/**
 * Created by sony on 21.04.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }
    @Autowired
    private MealService mealService;
    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception{
        dbPopulator.execute();
    }



    @Test
    public void testGet() throws Exception {
        Meal meal = mealService.get(DINNER_ID, USER_ID);
        MealTestData.MATCHER.assertEquals(MEAL_1, meal);
    }

    @Test
    public void testDelete() throws Exception {
        mealService.delete(DINNER_ID, USER_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MEAL_2, MEAL_3), mealService.getAll(USER_ID));
    }
    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception{
        mealService.delete(DINNER_ID, 1);
    }

    @Test
    public void testGetBetweenDates() throws Exception {

    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {
        List<Meal> mealList = mealService.getAll(USER_ID);
        MealTestData.MATCHER.assertCollectionEquals(mealList, MEALS);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();
        mealService.update(updated, USER_ID);
        MealTestData.MATCHER.assertEquals(updated, mealService.get(DINNER_ID, USER_ID));
    }

    @Test
    public void testSave() throws Exception {
        Meal created = getCreated();
        mealService.save(created, USER_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MEAL_1, MEAL_2, MEAL_3, created), mealService.getAll(USER_ID));
    }
}