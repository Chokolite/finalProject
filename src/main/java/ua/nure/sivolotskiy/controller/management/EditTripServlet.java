package ua.nure.sivolotskiy.controller.management;

import ua.nure.sivolotskiy.controller.common.ConverterUtils;
import ua.nure.sivolotskiy.controller.common.JspConstants;
import ua.nure.sivolotskiy.entity.*;
import ua.nure.sivolotskiy.service.BookingService;
import ua.nure.sivolotskiy.service.TripService;
import ua.nure.sivolotskiy.service.UserService;
import ua.nure.sivolotskiy.service.VehicleService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/edit-trip", "/dispatcher/edit-trip", "/edit-trip"})
public class EditTripServlet extends HttpServlet {
    private TripService tripService;
    private VehicleService vehicleService;
    private BookingService bookingService;

    @Override
    public void init(ServletConfig config){
        tripService = (TripService) config.getServletContext().getAttribute(TripService.class.toString());
        vehicleService = (VehicleService) config.getServletContext().getAttribute(VehicleService.class.toString());
        bookingService = (BookingService) config.getServletContext().getAttribute(BookingService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Booking> bookings = bookingService.getAll();
        List<Vehicle> vehicleList = vehicleService.getAll();
        Trip trip = tripService.getById(ConverterUtils.getIdFromRequest(request));
        trip.setVehicle(vehicleService.getById(trip.getId()));
        request.setAttribute("bookings", bookings);
        request.setAttribute("trip", trip);
        request.setAttribute("vehicle", vehicleList);

        request.getRequestDispatcher(JspConstants.EDIT_TRIP_JSP).forward(request, response);


    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Trip trip = ConverterUtils.convertRequestToTrip(request);
        tripService.update(trip);
        String role = request.getParameter("role");
        if(String.valueOf(Role.ADMIN).equals(role)){
            response.sendRedirect("/admin/admin-home");
        }
        if(String.valueOf(Role.DISPATCHER).equals(role)){
            response.sendRedirect("/dispatcher/dispatcher-home");
        }
        if(String.valueOf(Role.DRIVER).equals(role)){
            response.sendRedirect("/dispatcher/dispatcher-home");
        }
    }
}
