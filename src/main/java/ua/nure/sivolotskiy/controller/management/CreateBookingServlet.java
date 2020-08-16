package ua.nure.sivolotskiy.controller.management;

import ua.nure.sivolotskiy.controller.common.ConverterUtils;
import ua.nure.sivolotskiy.controller.common.JspConstants;
import ua.nure.sivolotskiy.entity.Booking;
import ua.nure.sivolotskiy.entity.Role;
import ua.nure.sivolotskiy.entity.Trip;
import ua.nure.sivolotskiy.entity.User;
import ua.nure.sivolotskiy.service.BookingService;
import ua.nure.sivolotskiy.service.TripService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/create-booking")
public class CreateBookingServlet extends HttpServlet {
    private BookingService bookingService;
    private TripService tripService;

    @Override
    public void init(ServletConfig config){
        bookingService = (BookingService) config.getServletContext().getAttribute(BookingService.class.toString());
        tripService = (TripService) config.getServletContext().getAttribute(TripService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Long id = Long.valueOf(request.getParameter("id"));
        request.setAttribute("id", id);

        request.getRequestDispatcher(JspConstants.CREATE_BOOKING_JSP).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        User user = new User();
        user.setId(Long.valueOf(request.getParameter("user_id")));
        Trip trip = new Trip();
        trip.setId(Long.valueOf(request.getParameter("id")));
        String specification = request.getParameter("specification");
        System.out.println(specification);
        Booking booking = ConverterUtils.convertRequestToBooking(request);
        booking.setVehicle_specification(specification);
        booking.setTrip(trip);
        booking.setUser(user);

        bookingService.save(booking);
        String role = request.getParameter("role");
        if(String.valueOf(Role.ADMIN).equals(role)){
            response.sendRedirect("/admin/admin-home");
        }
        if(String.valueOf(Role.DISPATCHER).equals(role)){
            response.sendRedirect("/dispatcher/dispatcher-home");
        }
        if(String.valueOf(Role.DRIVER).equals(role)){
            response.sendRedirect("/driver/driver-home");
        }
    }
}
