package ua.nure.sivolotskiy.controller.management;

import ua.nure.sivolotskiy.controller.common.ConverterUtils;
import ua.nure.sivolotskiy.controller.common.JspConstants;
import ua.nure.sivolotskiy.entity.Role;
import ua.nure.sivolotskiy.entity.Trip;
import ua.nure.sivolotskiy.service.TripService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/delete-trip", "/dispatcher/delete-trip"})
public class DeleteTripServlet extends HttpServlet {

    private TripService tripService;

    @Override
    public void init(ServletConfig config){
        tripService = (TripService) config.getServletContext().getAttribute(TripService.class.toString());

    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Trip trip = tripService.getById(Long.valueOf(request.getParameter("id")));
        tripService.delete(trip.getId());
        String role = request.getParameter("role");
        if (!String.valueOf(Role.ADMIN).equals(role)) {
        } else {
            response.sendRedirect("/admin/admin-home");
        }
        if(String.valueOf(Role.DISPATCHER).equals(role)){
            response.sendRedirect("/dispatcher/dispatcher-home");
        }
        if(String.valueOf(Role.DRIVER).equals(role)){
            response.sendRedirect("/dispatcher/dispatcher-home");
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
