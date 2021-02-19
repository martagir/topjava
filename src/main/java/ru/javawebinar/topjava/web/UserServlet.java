package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private UserService userService;

    @Override
    public void init() {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            userService = appCtx.getBean(UserService.class);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");
        User registeredUser = userService.get(SecurityUtil.authUserId());
        request.setAttribute("isAdmin", registeredUser.isAdmin());
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");
        SecurityUtil.setLoggedUserId(Integer.valueOf(request.getParameter("userId")));
        User registeredUser = userService.get(SecurityUtil.authUserId());
        request.setAttribute("isAdmin", registeredUser.isAdmin());
        request.setAttribute("userId", SecurityUtil.authUserId());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
