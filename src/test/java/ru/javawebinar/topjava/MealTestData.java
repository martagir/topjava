package ru.javawebinar.topjava;

import java.time.LocalDateTime;
import java.util.Arrays;
import ru.javawebinar.topjava.model.Meal;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_1_ID = START_SEQ + 2;
    public static final int MEAL_2_ID = START_SEQ + 3;
    public static final int MEAL_3_ID = START_SEQ + 4;
    public static final int MEAL_4_ID = START_SEQ + 5;
    public static final int MEAL_5_ID = START_SEQ + 6;
    public static final int MEAL_6_ID = START_SEQ + 7;
    
    public static final Meal meal_1 = new Meal(MEAL_1_ID, LocalDateTime.now(), "Завтрак", 250);
    public static final Meal meal_2 = new Meal(MEAL_2_ID, LocalDateTime.now(), "Обед", 250);
    public static final Meal meal_3 = new Meal(MEAL_3_ID, LocalDateTime.now(), "Ужин", 250);
    public static final Meal meal_4 = new Meal(MEAL_4_ID, LocalDateTime.now(), "Завтрак", 250);
    public static final Meal meal_5 = new Meal(MEAL_5_ID, LocalDateTime.now(), "Обед", 250);
    public static final Meal meal_6 = new Meal(MEAL_6_ID, LocalDateTime.now(), "Ужин", 250);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.now(), "Ночной дожор",  1500);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal_1);
        updated.setCalories(500);
        updated.setDescription("Уменьшаем калории");
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("dateTime", "userId").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dateTime", "userId").isEqualTo(expected);
    }
}
