package ru.javawebinar.topjava.web;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.javawebinar.topjava.model.Meal;

public class MealFilteringServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String dateString = request.getParameter("startDate");
        String timeString = request.getParameter("startTime");

        LocalDate date = LocalDate.parse(dateString,  DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime time = LocalTime.parse(timeString);

//        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
//                LocalDateTime.parse(request.getParameter("dateTime")),
//                request.getParameter("description"),
//                Integer.parseInt(request.getParameter("calories")), SecurityUtil.authUserId());
//
//        log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
//        mealRestController.createOrUpdate(meal);
        response.sendRedirect("meals");
    }
}
