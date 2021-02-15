package ru.javawebinar.topjava.repository;

import java.util.List;
import ru.javawebinar.topjava.model.Meal;

public interface MealRepository {
    Meal create(Meal meal);
    Meal get(int id);
    List<Meal> getAll();
    Meal update(Meal meal);
    boolean delete(int id);

}
