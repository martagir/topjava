package ru.javawebinar.topjava.service;

import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.NOT_FOUND;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal created = service.create(getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }


    @Test
    public void delete() {
        service.delete(MEAL_1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_1_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }

    @Test
    public void get() {
        Meal meal_1 = service.get(MEAL_1_ID, USER_ID);
        assertMatch(meal_1, MealTestData.meal_1);
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_1_ID, ADMIN_ID));
    }

    // не работает. Исправить функционал
    @Test
    public void updateNotOwn() {
        assertThrows(NotFoundException.class, () -> service.update(meal_1, ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        assertEquals(3, service.getBetweenInclusive(LocalDate.of(2021, 2, 26), LocalDate.now(), USER_ID).size());
        assertEquals(0, service.getBetweenInclusive(LocalDate.of(2021, 2, 27), LocalDate.now(), USER_ID).size());
        assertMatch(service.getBetweenInclusive(LocalDate.of(2021, 2, 26), LocalDate.now(), USER_ID), meal_3, meal_2, meal_1);
    }

    @Test
    public void getAll() {
        List<Meal> userMeals = service.getAll(USER_ID);
        assertEquals(3, userMeals.size());
        assertMatch(userMeals.get(0), meal_3);
        assertMatch(userMeals.get(1), meal_2);
        assertMatch(userMeals.get(2), meal_1);

        List<Meal> adminMeals = service.getAll(ADMIN_ID);
        assertEquals(3, adminMeals.size());
        assertMatch(adminMeals.get(0), meal_6);
        assertMatch(adminMeals.get(1), meal_5);
        assertMatch(adminMeals.get(2), meal_4);
    }

    @Test
    public void update() {
        meal_1.setDescription("Уменьшаем калории");
        meal_1.setCalories(500);
        service.update(meal_1, USER_ID);
        Meal mealAfterUpdate = service.get(MEAL_1_ID, USER_ID);
        assertMatch(mealAfterUpdate, getUpdated());
    }
}