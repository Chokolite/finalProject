package ua.nure.sivolotskiy.controller.management;


import ua.nure.sivolotskiy.controller.common.ConverterUtils;
import ua.nure.sivolotskiy.controller.common.JspConstants;
import ua.nure.sivolotskiy.entity.Role;
import ua.nure.sivolotskiy.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/create-user")
public class CreateUserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute(UserService.class.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher(JspConstants.CREATE_USER_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Long userId;
        Role role = Role.valueOf(request.getParameter("role"));
        if(Role.ADMIN.equals(role)){
            userId = userService.save(ConverterUtils.convertRequestToAdmin(request));
        } else if(Role.DISPATCHER.equals(role)){
            userId = userService.save(ConverterUtils.convertRequestToDispatcher(request));
        } else {
            userId = userService.save(ConverterUtils.convertRequestToDriver(request));
        }
        response.sendRedirect("/admin/admin-home");
    }
}
