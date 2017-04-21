package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;
import static java.time.LocalDateTime.of;
public class MealTestData {

    public static final int DINNER_ID = START_SEQ+2;
    public static final int TWO_DINNER_ID = START_SEQ+3;
    public static final int SUPPER_ID = START_SEQ+4;
    public static final int A_DINNER_ID = START_SEQ+2;
    public static final int A_TWO_DINNER_ID = START_SEQ+3;
    public static final int A_SUPPER_ID = START_SEQ+4;
    /**
     * ('dinner', 1000, 100000),
     ('2 dinner', 1000, 100000),
     ('supper', 500, 100000),
     ('dinner', 500, 100001),
     ('2 dinner', 900, 100001),
     ('supper', 200, 100001);
     */
    public static final Meal MEAL_1 = new Meal(DINNER_ID, of(2017, Month.MARCH, 30, 10,0), "dinner", 1000);
    public static final Meal MEAL_2 = new Meal(TWO_DINNER_ID, of(2017, Month.MARCH, 30, 12,0), "2 dinner", 1000);
    public static final Meal MEAL_3 = new Meal(SUPPER_ID, of(2017, Month.MARCH, 30, 20, 0), "supper", 500);
    public static final Meal MEAL_4 = new Meal(A_DINNER_ID, of(2017, Month.MARCH, 12, 10, 0), "dinner", 500);
    public static final Meal MEAL_5 = new Meal(A_TWO_DINNER_ID, of(2017, Month.MARCH, 12, 12, 0), "2 dinner", 900);
    public static final Meal MEAL_6 = new Meal(A_SUPPER_ID, of(2017, Month.MARCH, 12, 20, 0), "supper", 200);


    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();

    public static final List<Meal> MEALS = Arrays.asList(MEAL_1, MEAL_2, MEAL_3);

    public static Meal getCreated() {
               return new Meal(null, of(2017, Month.JUNE, 1, 18, 0), "Созданный ужин", 300);}

    public static Meal getUpdated() {
                return new Meal(DINNER_ID, MEAL_1.getDateTime(), "Обновленный завтрак", 200);}
}
