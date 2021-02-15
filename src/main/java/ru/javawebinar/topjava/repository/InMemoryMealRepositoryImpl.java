package ru.javawebinar.topjava.repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import ru.javawebinar.topjava.model.Meal;

public class InMemoryMealRepositoryImpl implements MealRepository {

    private static AtomicInteger counter = new AtomicInteger(0);
    private static Map<Integer, Meal> mealRepository = new ConcurrentHashMap<>();
    static {
        mealRepository.put(counter.incrementAndGet(), new Meal(counter.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        mealRepository.put(counter.incrementAndGet(), new Meal(counter.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        mealRepository.put(counter.incrementAndGet(), new Meal(counter.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        mealRepository.put(counter.incrementAndGet(), new Meal(counter.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        mealRepository.put(counter.incrementAndGet(), new Meal(counter.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        mealRepository.put(counter.incrementAndGet(), new Meal(counter.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        mealRepository.put(counter.incrementAndGet(), new Meal(counter.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public synchronized Meal create(Meal meal) {
        int newId = counter.incrementAndGet();
        Meal newMeal = new Meal(newId, meal.getDateTime(), meal.getDescription(), meal.getCalories());
        mealRepository.put(newId, newMeal);
        return newMeal;
    }

    @Override
    public synchronized Meal get(int id) {
        return mealRepository.get(id);
    }

    @Override
    public synchronized List<Meal> getAll() {
        return new ArrayList<>(mealRepository.values());
    }

    @Override
    public synchronized Meal update(Meal meal) {
        mealRepository.put(meal.getId(), meal);

        return meal;
    }

    @Override
    public synchronized boolean delete(int id) {
        mealRepository.remove(id);

        return true;
    }
}
