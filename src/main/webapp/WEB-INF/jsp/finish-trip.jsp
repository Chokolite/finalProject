<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Finish Trip form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<a href="" onclick="javascript:history.back(); return false;" id="back">
    <i class="fas fa-arrow-circle-left">
        <fmt:message key="finishTrip"/>
    </i>
</a>
    <div id="user_info" class="tableInfo"  style="margin: 0 auto; width: 100%;"  >

        <form action="/driver/finish-trip" method="post">
            <table class="table">
                <tr>
                    <th><fmt:message key="trip"/> <fmt:message key="id"/> </th>
                    <th><fmt:message key="vehicle_id"/></th>
                    <th><fmt:message key="booking_id"/></th>
                    <th><fmt:message key="create_date"/></th>
                    <th><fmt:message key="condition"/> </th>
                    <th><fmt:message key="status"/> </th>
                </tr>
                <tr>
                    <td><input type="number" name="id" value="${trip.id}" required/></td>
                    <td><input type="number" name="vehicle_id" value="${trip.vehicle.id}" required/></td>
                    <td><input type="number" name="booking_id" value="${trip.booking.id}" required/></td>
                    <td><input type="date" name="date" value="${trip.create_date}" readonly/></td>
                    <td><p><select name="condition" size="1" required>
                        <option value="GOOD"><fmt:message key="good"/></option>
                        <option value="BROKEN"><fmt:message key="broken"/></option>
                    </select></p>
                    </td>
                    <td>
                        <p><select name="status" size="1" required>
                            <option value="OPEN"><fmt:message key="open"/> </option>
                            <option value="IN_WORK"><fmt:message key="in_work"/></option>
                            <option value="CLOSED"><fmt:message key="closed"/></option>
                        </select></p>
                        <p></p>
                    </td>
                <tr style="background-color: transparent">
                <td colspan="7">
                    <input type="hidden" name="role" value="${user.role}">
                    <input id="accept"  type="submit" name="submit" value="<fmt:message key="save"/>">
                </td>
            </tr>
            </table>
        </form>

        <table class="table">
            <tr>
                <th><fmt:message key="id"/></th>
                <th><fmt:message key="name"/></th>
                <th><fmt:message key="sits"/></th>
                <th><fmt:message key="type"/></th>
                <th><fmt:message key="trunk_size"/></th>
                <th><fmt:message key="car_class"/></th>
                <th><fmt:message key="condition"/></th>

            </tr>
            <c:forEach var="vehicle" items="${vehicle}">
                <tr>
                    <td>${vehicle.id}</td>
                    <td>${vehicle.name}</td>
                    <td>${vehicle.sits}</td>
                    <td>${vehicle.type}</td>
                    <td>${vehicle.trunk_size}</td>
                    <td>${vehicle.car_class}</td>
                    <td>${vehicle.condition}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>