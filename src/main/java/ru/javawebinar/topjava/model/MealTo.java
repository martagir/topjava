package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MealTo {
    private Integer id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

//    private final AtomicBoolean excess;      // or Boolean[1],  filteredByAtomic
//    private final Boolean excess;            // filteredByReflection
//    private final Supplier<Boolean> excess;  // filteredByClosure
    private boolean excess;

    public MealTo(Integer id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

//    public Boolean getExcess() {
//        return excess.get();
//    }

    // for filteredBySetterRecursion
    public void setExcess(boolean excess) {
        this.excess = excess;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}
