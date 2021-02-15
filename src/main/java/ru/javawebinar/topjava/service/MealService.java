package ru.javawebinar.topjava.service;

import java.time.LocalTime;
import java.util.List;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

public class MealService {
   private static MealRepository repository = new InMemoryMealRepositoryImpl();

    private static final int CALORIES_PER_DAY = 2000;

    public static List<MealTo> getMealToWithoutFilter() {
        return MealsUtil.filteredByCycles(repository.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
    }

    public static List<Meal> getAllMeals() {
        return repository.getAll();
    }

    public static Meal createMeal(Meal meal) {
        return repository.create(meal);
    }

    public static Meal update(Meal meal) {
        return repository.update(meal);
    }

    public static Meal getMeal(Integer id) {
        return repository.get(id);
    }

    public static boolean deleteMeal(Integer id) {
        return repository.delete(id);
    }
}
