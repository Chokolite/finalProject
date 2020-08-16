package ua.nure.sivolotskiy.controller.common;

import ua.nure.sivolotskiy.entity.*;
import ua.nure.sivolotskiy.exception.ValidationEnum;
import ua.nure.sivolotskiy.exception.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConverterUtils {

    public static Booking convertRequestToBooking(HttpServletRequest request) {
        Booking booking = new Booking();
        booking.setId(getIdFromRequest(request));
        booking.setVehicleSpecification(request.getParameter("vehicleSpecification"));
        User user = new User();
        user.setId(getIdFromRequest(request));

        return booking;
    }

    public static Trip convertRequestToTrip(HttpServletRequest request) {
        //Converting date from HTML-page to Java format and after that to SQL format
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
        } catch (ParseException e) {
            System.out.println("DATE PARSE EXCEPTION");
            e.printStackTrace();
        }
        java.sql.Date SQLDate = new java.sql.Date(date.getTime());
        //end convertings

        Trip trip = new Trip();
        Booking booking = new Booking();
        Vehicle vehicle = new Vehicle();

        String vehicleBuf = request.getParameter("vehicleId");
        String bookingBuf = request.getParameter("bookingId");

        if (bookingBuf == null) {
            booking.setId(0L);
        } else {
            booking.setId(Long.valueOf(bookingBuf));
        }
        if (vehicleBuf == null) {
            vehicle.setId(0L);
        } else {
            vehicle.setId(Long.parseLong(vehicleBuf));
        }

        trip.setVehicle(vehicle);
        trip.setBooking(booking);
        trip.setId(getIdFromRequest(request));
        trip.setCreateDate(SQLDate);
        trip.setStatus(Status.valueOf(request.getParameter("status")));
        trip.setTask(request.getParameter("task"));
        return trip;
    }

    public static Driver convertRequestToDriver(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String reRassword = request.getParameter("rePassword");
        if (!password.equals(reRassword)) {
            ValidationException.builder().put("passwordError", ValidationEnum.PASSWORDS_DO_NOT_MATCH).throwIfErrorExists();
        }
        Driver driver = new Driver();
        driver.setName(name);
        driver.setEmail(email);
        driver.setPassword(PasswordEncoder.encode(password));
        driver.setEnabled(true);
        driver.setRole(Role.DRIVER);

        return driver;
    }

    public static Dispatcher convertRequestToDispatcher(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String reRassword = request.getParameter("rePassword");
        if (!password.equals(reRassword)) {
            ValidationException.builder().put("passwordError", ValidationEnum.PASSWORDS_DO_NOT_MATCH).throwIfErrorExists();
        }
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setName(name);
        dispatcher.setEmail(email);
        dispatcher.setPassword(PasswordEncoder.encode(password));
        dispatcher.setEnabled(true);
        dispatcher.setRole(Role.DISPATCHER);

        return dispatcher;
    }

    public static User convertRequestToAdmin(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String reRassword = request.getParameter("rePassword");
        if (!password.equals(reRassword)) {
            ValidationException.builder().put("passwordError", ValidationEnum.PASSWORDS_DO_NOT_MATCH).throwIfErrorExists();
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(PasswordEncoder.encode(password));
        user.setEnabled(true);
        user.setRole(Role.ADMIN);

        return user;
    }
    public static Vehicle convertRequestToVehicle(HttpServletRequest request){
        String name = request.getParameter("name");
        Long sits = Long.valueOf(request.getParameter("sits"));
        Type type = Type.valueOf(request.getParameter("type"));
        TrunkSize trunkSize = TrunkSize.valueOf(request.getParameter("trunkSize"));
        String carClass = request.getParameter("carClass");
        Condition condition = Condition.valueOf(request.getParameter("condition"));
        Long id = Long.valueOf(request.getParameter("id"));

        Vehicle vehicle = new Vehicle();
        if(null!=id){
            vehicle.setId(id);
        }
        vehicle.setName(name);
        vehicle.setSits(sits);
        vehicle.setType(type);
        vehicle.setTrunkSize(trunkSize);
        vehicle.setCarClass(carClass);
        vehicle.setCondition(condition);

        return vehicle;
    }

    public static Long getIdFromRequest(HttpServletRequest request) {
        if (Objects.nonNull(request.getParameter("id"))) {
            return Long.parseLong(request.getParameter("id"));
        } else if (Objects.nonNull(request.getParameter("userId"))) {
            System.out.println(request.getParameter("userId"));
            return Long.parseLong(request.getParameter("userId"));
        } else {
            return null;
        }
    }

    private static Long getBookingIdFromRequest(HttpServletRequest request) {
        if (Objects.nonNull(request.getParameter("bookingId"))) {
            return Long.parseLong(request.getParameter("bookingId"));
        } else {
            return null;
        }
    }

    private static Long getTripIdFromRequest(HttpServletRequest request) {
        if (Objects.nonNull(request.getParameter("tripId"))) {
            return Long.parseLong(request.getParameter("tripId"));
        } else {
            return null;
        }
    }

    private static Long getVehicleIdFromRequest(HttpServletRequest request) {
        if (Objects.nonNull(request.getParameter("vehicleId"))) {
            return Long.parseLong(request.getParameter("vehicleId"));
        } else {
            return null;
        }
    }
}
