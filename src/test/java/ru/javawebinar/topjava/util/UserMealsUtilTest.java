package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import static org.junit.Assert.*;

public class UserMealsUtilTest {
private static List<UserMeal> meals;

    @Before
    public void setUp() {
         meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
    }

    @Test
    public void filteredByCycles() {
        List<UserMealWithExcess> mealsTo = UserMealsUtil.filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        assertEquals(2, mealsTo.size());

        UserMealWithExcess firstElement = mealsTo.get(0);
        assertEquals("Завтрак", firstElement.getDescription());
        assertEquals(500, firstElement.getCalories());
        assertFalse(firstElement.isExcess());

        UserMealWithExcess secondElement = mealsTo.get(1);
        assertEquals("Завтрак", secondElement.getDescription());
        assertEquals(1000, secondElement.getCalories());
        assertTrue(secondElement.isExcess());
    }

    @Test
    public void filteredByStreams() {
        List<UserMealWithExcess> mealsTo = UserMealsUtil.filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        assertEquals(2, mealsTo.size());

        UserMealWithExcess firstElement = mealsTo.get(0);
        assertEquals("Завтрак", firstElement.getDescription());
        assertEquals(500, firstElement.getCalories());
        assertFalse(firstElement.isExcess());

        UserMealWithExcess secondElement = mealsTo.get(1);
        assertEquals("Завтрак", secondElement.getDescription());
        assertEquals(1000, secondElement.getCalories());
        assertTrue(secondElement.isExcess());
    }
}