package ua.nure.sivolotskiy.controller.management;

import ua.nure.sivolotskiy.entity.Booking;
import ua.nure.sivolotskiy.entity.Role;
import ua.nure.sivolotskiy.entity.Trip;
import ua.nure.sivolotskiy.service.BookingService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete-booking")
public class DeleteBookingServlet extends HttpServlet {
    private BookingService bookingService;

    @Override
    public void init(ServletConfig config){
        bookingService = (BookingService) config.getServletContext().getAttribute(BookingService.class.toString());
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Booking booking = bookingService.getById(Long.valueOf(request.getParameter("id")));
        bookingService.delete(booking.getId());
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
