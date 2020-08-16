package ua.nure.sivolotskiy.controller.management;

import ua.nure.sivolotskiy.controller.common.ConverterUtils;
import ua.nure.sivolotskiy.controller.common.JspConstants;
import ua.nure.sivolotskiy.entity.Role;
import ua.nure.sivolotskiy.service.BookingService;
import ua.nure.sivolotskiy.service.TripService;
import ua.nure.sivolotskiy.service.VehicleService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

@WebServlet(urlPatterns = {"/create-trip"})
public class CreateTripServlet extends HttpServlet {
    BookingService bookingService;
    TripService tripService;
    VehicleService vehicleService;

    @Override
    public void init(ServletConfig config){
        bookingService = (BookingService) config.getServletContext().getAttribute(BookingService.class.toString());
        tripService = (TripService) config.getServletContext().getAttribute(TripService.class.toString());
        vehicleService = (VehicleService) config.getServletContext().getAttribute(VehicleService.class.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher(JspConstants.CREATE_TRIP_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        tripService.save(ConverterUtils.convertRequestToTrip(request));
        String role = request.getParameter("role");
        if(String.valueOf(Role.ADMIN).equals(role)){
            response.sendRedirect("/admin/admin-home");
        }
        if(String.valueOf(Role.DISPATCHER).equals(role)){
            response.sendRedirect("/dispatcher/dispatcher-home");
        }

    }
}
