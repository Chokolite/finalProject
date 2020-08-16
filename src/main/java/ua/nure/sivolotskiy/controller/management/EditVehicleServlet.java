package ua.nure.sivolotskiy.controller.management;

import ua.nure.sivolotskiy.controller.common.ConverterUtils;
import ua.nure.sivolotskiy.controller.common.JspConstants;
import ua.nure.sivolotskiy.entity.Vehicle;
import ua.nure.sivolotskiy.service.VehicleService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/edit-vehicle")
public class EditVehicleServlet extends HttpServlet {

    private VehicleService vehicleService;

    @Override
    public void init(ServletConfig config){
        vehicleService = (VehicleService) config.getServletContext().getAttribute(VehicleService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Vehicle vehicle = vehicleService.getById(Long.valueOf(request.getParameter("id")));
        request.setAttribute("vehicle", vehicle);

        request.getRequestDispatcher(JspConstants.EDIT_VEHICLE_JSP).forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        vehicleService.update(ConverterUtils.convertRequestToVehicle(request));

        response.sendRedirect("/admin/admin-home");
    }
}
