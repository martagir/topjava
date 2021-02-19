package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {
    private static int LOGGED_USER_ID = 1;

    public static int authUserId() {
        return LOGGED_USER_ID;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }

    public static void setLoggedUserId(int id) {
        LOGGED_USER_ID = id;
    }
}