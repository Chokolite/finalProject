package ua.nure.sivolotskiy.config;

import org.apache.log4j.Logger;
import ua.nure.sivolotskiy.repository.BookingRepository;
import ua.nure.sivolotskiy.repository.TripRepository;
import ua.nure.sivolotskiy.repository.UserRepository;
import ua.nure.sivolotskiy.repository.VehicleRepository;
import ua.nure.sivolotskiy.repository.impl.BookingRepositoryImpl;
import ua.nure.sivolotskiy.repository.impl.TripRepositoryImpl;
import ua.nure.sivolotskiy.repository.impl.UserRepositoryImpl;
import ua.nure.sivolotskiy.repository.impl.VehicleRepositoryImpl;
import ua.nure.sivolotskiy.service.BookingService;
import ua.nure.sivolotskiy.service.TripService;
import ua.nure.sivolotskiy.service.UserService;
import ua.nure.sivolotskiy.service.VehicleService;
import ua.nure.sivolotskiy.service.impl.BookingServiceImpl;
import ua.nure.sivolotskiy.service.impl.TripServiceImpl;
import ua.nure.sivolotskiy.service.impl.UserServiceImpl;
import ua.nure.sivolotskiy.service.impl.VehicleServiceImpl;
import ua.nure.sivolotskiy.transaction.TransactionManager;
import ua.nure.sivolotskiy.transaction.impl.TransactionManagerImpl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class ApplicationContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ApplicationContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.debug("context init start");
        ServletContext context = servletContextEvent.getServletContext();

        TransactionManager transactionManager = initTransactionManager();
        initServices(context, transactionManager);
        LOGGER.debug("context init finish");
    }

    private void initServices(ServletContext context, TransactionManager transactionManager) {
        UserRepository userRepository = new UserRepositoryImpl();
        BookingRepository bookingRepository = new BookingRepositoryImpl();
        TripRepository tripRepository = new TripRepositoryImpl();
        VehicleRepository vehicleRepository = new VehicleRepositoryImpl();

        UserService userService = new UserServiceImpl(transactionManager, userRepository);
        BookingService bookingService = new BookingServiceImpl(transactionManager, bookingRepository);
        VehicleService vehicleService = new VehicleServiceImpl(transactionManager, vehicleRepository);
        TripService tripService = new TripServiceImpl(transactionManager, tripRepository, bookingService, vehicleService);

        tripService.setBookingService(bookingService);
        tripService.setVehicleService(vehicleService);

        context.setAttribute(UserService.class.toString(), userService);
        context.setAttribute(BookingService.class.toString(), bookingService);
        context.setAttribute(VehicleService.class.toString(), vehicleService);
        context.setAttribute(TripService.class.toString(), tripService);
    }

    private TransactionManager initTransactionManager() {
        try {
            DataSource dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/mydb"); ///node JNDI tree for jdbc
            return new TransactionManagerImpl(dataSource);
        } catch (NamingException e) {
            throw new RuntimeException("data source cant init");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
