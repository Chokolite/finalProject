<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Edit Trip form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<a href="" onclick="javascript:history.back(); return false;" id="back">
    <i class="fas fa-arrow-circle-left">
        <fmt:message key="editTrip"/>
    </i>
</a>
    <div id="user_info" class="tableInfo"  style="margin: 0 auto; width: 100%;"  >


        <form action="/edit-trip" method="post">
            <table class="table">
                <tr>
                    <th><fmt:message key="id"/></th>
                    <th><fmt:message key="vehicleId"/></th>
                    <th><fmt:message key="bookingId"/></th>
                    <th><fmt:message key="createDate"/></th>
                    <th><fmt:message key="status"/> </th>
                </tr>
                <tr>
                    <td><input type="number" name="id" value="${trip.id}" required/></td>
                    <td><input type="number" name="vehicleId" value="${trip.vehicleId}" required/></td>
                    <td><input type="number" name="bookingId" value="${trip.booking.id}" required/></td>
                    <td><input type="date" name="date" value="${trip.createDate}" required/></td>
                    <td>
                        <p><select name="status" size="1">
                            <option value="OPEN" selected>Open</option>
                            <option value="IN_WORK">In work</option>
                            <option value="CLOSED">Closed</option>
                        </select></p>
                        <p></p>
                    </td>
                <tr style="background-color: transparent">
                <td colspan="7">
                    <input type="hidden" name="role" value="${user.role}">
                    <input id="accept"  type="submit" name="submit" value="<fmt:message key="save"/>"></td>
            </tr>
            </table>
        </form>


            <table class="table">
                <tr>
                    <th><fmt:message key="id"/></th>
                    <th><fmt:message key="userId"/></th>
                    <th><fmt:message key="trip"/> </th>
                    <th><fmt:message key="vehicleSpecification"/></th>
                </tr>
                <c:forEach var="booking" items="${bookings}">
                    <c:if test="${trip.id eq booking.trip.id}">
                <tr>
                    <td>${booking.id}</td>
                    <td>${booking.user.id}</td>
                    <td>${booking.trip.id}</td>
                    <td>${booking.vehicleSpecification}</td>
                </tr>
                    </c:if>
                </c:forEach>
            </table>

        <table class="table">
            <tr>
                <th><fmt:message key="id"/></th>
                <th><fmt:message key="name"/></th>
                <th><fmt:message key="sits"/></th>
                <th><fmt:message key="type"/></th>
                <th><fmt:message key="trunkSize"/></th>
                <th><fmt:message key="carClass"/></th>
                <th><fmt:message key="condition"/></th>

            </tr>
            <c:forEach var="vehicle" items="${vehicle}">
                <tr>
                    <td>${vehicle.id}</td>
                    <td>${vehicle.name}</td>
                    <td>${vehicle.sits}</td>
                    <td>${vehicle.type}</td>
                    <td>${vehicle.trunkSize}</td>
                    <td>${vehicle.carClass}</td>
                    <td>${vehicle.condition}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>