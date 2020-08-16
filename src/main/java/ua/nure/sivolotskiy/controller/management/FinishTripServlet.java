package ua.nure.sivolotskiy.controller.management;

import ua.nure.sivolotskiy.controller.common.ConverterUtils;
import ua.nure.sivolotskiy.controller.common.JspConstants;
import ua.nure.sivolotskiy.entity.Booking;
import ua.nure.sivolotskiy.entity.Condition;
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

@WebServlet(urlPatterns = "/driver/finish-trip")
public class FinishTripServlet extends HttpServlet {

    private TripService tripService;
    private BookingService bookingService;
    private VehicleService vehicleService;

    @Override
    public void init(ServletConfig config){
        tripService = (TripService) config.getServletContext().getAttribute(TripService.class.toString());
        bookingService = (BookingService) config.getServletContext().getAttribute(BookingService.class.toString());
        vehicleService = (VehicleService) config.getServletContext().getAttribute(VehicleService.class.toString());

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Booking> bookings = bookingService.getAll();
        List<Vehicle> vehicleList = vehicleService.getAll();
        Trip trip = tripService.getById(ConverterUtils.getIdFromRequest(request));
        trip.setVehicle(vehicleService.getById(Long.valueOf(request.getParameter("vehicle_id"))));
        trip.setBooking(bookingService.getById(Long.valueOf(request.getParameter("bId"))));
        request.setAttribute("bookings", bookings);
        request.setAttribute("trip", trip);
        request.setAttribute("vehicle", vehicleList);

        request.getRequestDispatcher(JspConstants.FINISH_TRIP_JSP).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Trip trip = ConverterUtils.convertRequestToTrip(request);
        Vehicle vehicle = new Vehicle();
        Booking booking = bookingService.getById(Long.valueOf(request.getParameter("booking_id")));

        vehicle.setId(Long.valueOf(request.getParameter("vehicle_id")));
        vehicle.setCondition(Condition.valueOf(request.getParameter("condition")));
        vehicleService.finish(vehicle);
        tripService.update(trip);
        bookingService.delete(booking.getId());

        response.sendRedirect("/driver/driver-home");

    }
}
