package ua.nure.sivolotskiy.test.filter;

import org.junit.Test;
import ua.nure.sivolotskiy.entity.Role;
import ua.nure.sivolotskiy.entity.User;
import ua.nure.sivolotskiy.filter.SecurityFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class SecurityFilterTest {

    @Test
    public void test_on_check_admin_positive_doFilter() throws IOException, ServletException {
        SecurityFilter securityFilter = new SecurityFilter();
        FilterChain mockChain = mock(FilterChain.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpSession mockSession = mock(HttpSession.class);
        User mockUser = mock(User.class);

        when(mockRequest.getServletPath()).thenReturn("/admin/some-path");
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockSession.getAttribute("user")).thenReturn(mockUser);
        when(mockUser.getRole()).thenReturn(Role.ADMIN);

       securityFilter.doFilter(mockRequest, mockResponse, mockChain);

        verify(mockChain).doFilter(mockRequest, mockResponse);
    }

    @Test
    public void test_on_check_admin_negative_doFilter() throws IOException, ServletException {
        SecurityFilter securityFilter = new SecurityFilter();
        FilterChain mockChain = mock(FilterChain.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpSession mockSession = mock(HttpSession.class);
        User mockUser = mock(User.class);

        when(mockRequest.getServletPath()).thenReturn("/driver/some-path");
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockSession.getAttribute("user")).thenReturn(mockUser);
        when(mockUser.getRole()).thenReturn(Role.ADMIN);

        securityFilter.doFilter(mockRequest, mockResponse, mockChain);

        verify(mockResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}