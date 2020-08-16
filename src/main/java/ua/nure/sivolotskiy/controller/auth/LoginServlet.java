package ua.nure.sivolotskiy.controller.auth;

import ua.nure.sivolotskiy.controller.common.JspConstants;
import ua.nure.sivolotskiy.controller.common.PasswordEncoder;
import ua.nure.sivolotskiy.entity.Role;
import ua.nure.sivolotskiy.entity.User;
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
import java.util.Objects;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute(UserService.class.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspConstants.USER_LOGIN_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = PasswordEncoder.encode(request.getParameter("password"));
        User user = userService.getUserByEmailAndPassword(email, password);
        if (Objects.nonNull(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if (Role.DRIVER.equals(user.getRole())) {
                response.sendRedirect("/driver/driver-home");
            }
            if(Role.DISPATCHER.equals(user.getRole())){
                response.sendRedirect("/dispatcher/dispatcher-home");
            }
            if(Role.ADMIN.equals(user.getRole())){
                response.sendRedirect("/admin/admin-home");
            }
        } else {
            ValidationException.builder().put("loginError", ValidationEnum.BAD_CREDENTIAL).throwIfErrorExists();
        }
    }
}
