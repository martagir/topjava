package ru.javawebinar.topjava.util;


import java.util.Arrays;
import java.util.List;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

public class UserUtil {

    public static final List<User> users = Arrays.asList(
            new User(null, "Диппер", "dipper1@grf.com", "dipper@grf.com", Role.USER),
            new User(null, "Диппер", "dipper2@grf.com", "dipper@grf.com", Role.USER),
            new User(null, "Мэйбл", "mabel@grf.com", "mabel@grf.com", Role.USER),
            new User(null, "Дядя Стэн", "stan@grf.com", "stan@grf.com", Role.USER, Role.ADMIN),
            new User(null, "Зус", "soos@grf.com", "soos@grf.com", Role.USER),
            new User(null, "Венди", "wendy@grf.com", "wendy@grf.com", Role.USER)
    );

}
