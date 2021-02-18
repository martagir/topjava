package ru.javawebinar.topjava.web.meal;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    public Meal createOrUpdate(Meal meal) {
        return service.createOrUpdate(meal, SecurityUtil.authUserId());
    }

    public void delete(int id) {
        service.delete(id, SecurityUtil.authUserId());
    }

    public Meal get(int id) {
        return service.get(id, SecurityUtil.authUserId());
    }

    public List<Meal> getAll() {
        return service.getAll(SecurityUtil.authUserId());
    }

    public void update(Meal meal) {
        service.createOrUpdate(meal, SecurityUtil.authUserId());
    }


}