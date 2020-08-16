package ua.nure.sivolotskiy.controller.user;

import ua.nure.sivolotskiy.controller.common.JspConstants;
import ua.nure.sivolotskiy.controller.common.PaginationUtils;
import ua.nure.sivolotskiy.entity.Booking;
import ua.nure.sivolotskiy.entity.Trip;
import ua.nure.sivolotskiy.entity.Vehicle;
import ua.nure.sivolotskiy.service.BookingService;
import ua.nure.sivolotskiy.service.TripService;
import ua.nure.sivolotskiy.service.VehicleService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/dispatcher/dispatcher-home")
public class DispatcherHomeServlet extends HttpServlet {
    private BookingService bookingService;
    private TripService tripService;
    private VehicleService vehicleService;

    @Override
    public void init(ServletConfig config) {
        bookingService = (BookingService) config.getServletContext().getAttribute(BookingService.class.toString());
        tripService = (TripService) config.getServletContext().getAttribute(TripService.class.toString());
        vehicleService = (VehicleService) config.getServletContext().getAttribute(VehicleService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int offset = PaginationUtils.getOffset(request);

        List<Vehicle> vehicleList = vehicleService.getAll();
        List<Booking> bookingList = bookingService.getAll();
        List<Trip> tripList = tripService.getAll(request.getParameter("order"), offset);


        request.setAttribute("vehicles", vehicleList);
        request.setAttribute("bookings", bookingList);
        request.setAttribute("trips", tripList);
        request.setAttribute("task", "task");

        request.getRequestDispatcher(JspConstants.HOMEPAGE_DISPATCHER_JSP).forward(request, response);
    }
}
