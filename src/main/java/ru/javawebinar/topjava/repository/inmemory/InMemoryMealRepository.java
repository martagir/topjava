package ru.javawebinar.topjava.repository.inmemory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, meal.getUserId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (userId != meal.getUserId()) {
            return null;
        }
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int mealId, int userId) {
        Meal meal =  repository.get(mealId);
        return userId == meal.getUserId() && repository.remove(mealId) != null;
    }

    @Override
    public Meal get(int mealId, int userId) {
        Meal meal =  repository.get(mealId);

        return userId == meal.getUserId() ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.values().stream().filter(meal -> userId == meal.getUserId()).sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<Meal> getFiltered(LocalDate date, LocalTime time, int userId) {
//        repository.values().stream()
//                .filter(meal -> (userId == meal.getUserId()) && DateTimeUtil.isBetweenHalfOpen(meal.getDate(), ))
//                .sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());
    }
}

