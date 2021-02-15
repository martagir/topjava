package ru.javawebinar.topjava.web;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import static org.slf4j.LoggerFactory.getLogger;

public class CreateReadUpdateMealServlet extends HttpServlet {
    private static final Logger log = getLogger(CreateReadUpdateMealServlet.class);
    private static final String UTF_8_CHARSET = "UTF-8";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meal");

        String idAsString = request.getParameter("id");
        if (StringUtils.isNotBlank(idAsString)) {
            Integer id = Integer.valueOf(idAsString);
            Meal meal = MealService.getMeal(id);
            request.setAttribute("id", id);
            request.setAttribute("dateTime", meal.getDateTime());
            request.setAttribute("description", meal.getDescription());
            request.setAttribute("calories", meal.getCalories());
        }


        request.getRequestDispatcher("/createReadUpdateMeal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("create or update meal");
        request.setCharacterEncoding(UTF_8_CHARSET);

        String idAsString = request.getParameter("id");
        String dateTimeAsString = request.getParameter("dateTime");
        LocalDateTime localDateTime = LocalDateTime.now();
        String caloriesAsString = request.getParameter("calories");
        int calories = 0;

        if (StringUtils.isNotBlank(dateTimeAsString)) {
            localDateTime = LocalDateTime.parse(dateTimeAsString);
        }

        if (StringUtils.isNotBlank(caloriesAsString)) {
            calories = Integer.valueOf(caloriesAsString);
        }

        if (StringUtils.isBlank(idAsString)) {
            MealService.createMeal(new Meal(null, localDateTime, request.getParameter("description"), calories));
        } else {
            Integer id = Integer.valueOf(request.getParameter("id"));
            MealService.update(new Meal(id, localDateTime, request.getParameter("description"), calories));
        }

        response.sendRedirect("meals");
    }
}
