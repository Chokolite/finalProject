package ua.nure.sivolotskiy.controller;

import ua.nure.sivolotskiy.controller.common.ConverterUtils;
import ua.nure.sivolotskiy.controller.common.JspConstants;
import ua.nure.sivolotskiy.entity.Driver;
import ua.nure.sivolotskiy.exception.ValidationEnum;
import ua.nure.sivolotskiy.exception.ValidationException;
import ua.nure.sivolotskiy.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/sign-up")
public class SignUpServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute(UserService.class.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspConstants.SIGN_UP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Driver driver = ConverterUtils.convertRequestToDriver(request);
        if (userService.existsByEmail(driver.getEmail())) {
            ValidationException.builder().put("emailError", ValidationEnum.EMAIL_EXISTS).throwIfErrorExists();
        }
        driver.setId(userService.save(driver));
        HttpSession session = request.getSession();
        session.setAttribute("DRIVER", driver);
        response.sendRedirect("/driver/driver-home");

    }
}

