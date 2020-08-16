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

@WebServlet(urlPatterns = "/driver/driver-home")
public class DriverHomeServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int offset = PaginationUtils.getOffset(request);

        List<Booking> bookings = bookingService.getAll();
        List<Trip> trips = tripService.getAll(request.getParameter("order"), offset);
        List<Vehicle> vehicleList = vehicleService.getAll();

        request.setAttribute("vehicles", vehicleList);
        request.setAttribute("bookings", bookings);
        request.setAttribute("trips", trips);
        request.setAttribute("task", "task");

        request.getRequestDispatcher(JspConstants.HOMEPAGE_DRIVER_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Long trip_id = Long.valueOf(request.getParameter("trip_id"));

    }
}
