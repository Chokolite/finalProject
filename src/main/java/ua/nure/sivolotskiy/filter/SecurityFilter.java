package ua.nure.sivolotskiy.filter;

import org.apache.log4j.Logger;
import ua.nure.sivolotskiy.entity.Role;
import ua.nure.sivolotskiy.entity.User;
import ua.nure.sivolotskiy.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

public class SecurityFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(SecurityFilter.class);
    private static Pattern adminPattern = Pattern.compile("/admin/.*");
    private static Pattern driverPattern = Pattern.compile("/driver/.*");
    private static Pattern dispatcherPattern = Pattern.compile("/dispatcher/.*");

    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) {
        userService = (UserService) filterConfig.getServletContext().getAttribute(UserService.class.toString());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        LOG.debug("SecurityFilter");
        boolean check = checkForAdmin((HttpServletRequest) request, (HttpServletResponse) response);
        check &= checkForDriver((HttpServletRequest) request, (HttpServletResponse) response);
        check &= checkForDispatcher((HttpServletRequest) request, (HttpServletResponse) response);
        if (check) {
            filterChain.doFilter(request, response);
        }
        request.setAttribute("errorPage", ((HttpServletResponse) response).getStatus());
    }

    @Override
    public void destroy() {

    }

    private boolean checkForAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer error = null;
        if (adminPattern.matcher(request.getServletPath()).matches()) {
            User user = getUser(request);
            if (Objects.nonNull(user)) {
                if (!isUserEnabled(user)) {
                    error = HttpServletResponse.SC_UNAUTHORIZED;
                    request.getSession().removeAttribute("user");
                } else if (!Role.ADMIN.equals(user.getRole())) {
                    error = HttpServletResponse.SC_FORBIDDEN;
                }
            } else {
                error = HttpServletResponse.SC_UNAUTHORIZED;
            }
        }

        if (Objects.isNull(error)) {
            return true;
        } else {
            response.sendError(error);
            return false;
        }
    }
    private boolean checkForDispatcher(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer error = null;
        if (dispatcherPattern.matcher(request.getServletPath()).matches()) {
            User user = getUser(request);
            if (Objects.nonNull(user)) {
                if (!isUserEnabled(user)) {
                    error = HttpServletResponse.SC_UNAUTHORIZED;
                    request.getSession().removeAttribute("user");
                } else if (!Role.DISPATCHER.equals(user.getRole())) {
                    error = HttpServletResponse.SC_FORBIDDEN;
                }
            } else {
                error = HttpServletResponse.SC_UNAUTHORIZED;
            }
        }

        if (Objects.isNull(error)) {
            return true;
        } else {
            response.sendError(error);
            return false;
        }
    }

    private boolean checkForDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer error = null;
        if (driverPattern.matcher(request.getServletPath()).matches()) {
            User user = getUser(request);
            if (Objects.nonNull(user)) {
                if (!isUserEnabled(user)) {
                    error = HttpServletResponse.SC_UNAUTHORIZED;
                    request.getSession().removeAttribute("user");
                } else if (!Role.DRIVER.equals(user.getRole())) {
                    error = HttpServletResponse.SC_FORBIDDEN;
                }
            } else {
                error = HttpServletResponse.SC_UNAUTHORIZED;
            }
        }

        if (Objects.isNull(error)) {
            return true;
        } else {
            response.sendError(error);
            return false;
        }
    }

    private boolean isUserEnabled(User user) {
        return userService.isEnabledById(user.getId());
    }

    private User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (Objects.nonNull(session)) {
            return (User) session.getAttribute("user");
        }
        return null;
    }
}
