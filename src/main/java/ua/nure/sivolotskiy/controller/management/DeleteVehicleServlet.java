package ua.nure.sivolotskiy.controller.management;

import ua.nure.sivolotskiy.entity.Vehicle;
import ua.nure.sivolotskiy.service.VehicleService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/delete-vehicle")
public class DeleteVehicleServlet extends HttpServlet {

    private VehicleService vehicleService;

    @Override
    public void init(ServletConfig config){
        vehicleService = (VehicleService) config.getServletContext().getAttribute(VehicleService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Vehicle vehicle = vehicleService.getById(Long.valueOf(request.getParameter("id")));
        vehicleService.delete(vehicle.getId());
        response.sendRedirect("/admin/admin-home");
    }
}
