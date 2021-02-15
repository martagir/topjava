package ru.javawebinar.topjava.web;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import ru.javawebinar.topjava.service.MealService;

import static org.slf4j.LoggerFactory.getLogger;

public class DeleteMealServlet extends HttpServlet {
    private static final Logger log = getLogger(DeleteMealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("trying to delete meal with id = {}", request.getParameter("id"));
        MealService.deleteMeal(Integer.valueOf(request.getParameter("id")));

        response.sendRedirect("meals");
    }
}
