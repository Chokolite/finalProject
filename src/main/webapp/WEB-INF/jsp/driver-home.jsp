<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Driver Panel"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="la" tagdir="/WEB-INF/tags" %>

<la:lang/>

<div id="wrapper">
    <div id="user_info">
        <div id="title_user">
            <div id="icon"><i class="fas fa-user" style="padding-top: 8px;"></i></div>
            <div id="welcome_user"><fmt:message key="driver"/> ${user.name}</div>
            <a href="/logout"><i class="fas fa-sign-out-alt"></i></a>
        </div>
        <hr>
        <span><fmt:message key="email"/>: ${user.email}</span><br>
        <span><fmt:message key="acctype"/>: ${user.role}</span><br>
    </div>
    <div id="users" class="tableInfo">
        <hr>
        <table>
            <c:url value="/driver/driver-home" var="sortAmount">
                <c:set var="ascDesc" value="ASC"/>
            <c:choose>
            <c:when test="${param.order == 't.id,ASC'}">
                <c:set var="ascDesc" value="DESC"/>
            </c:when>
            <c:when test="${param.order == 't.id,DESC'}">
                <c:set var="ascDesc" value="ASC"/>
            </c:when>
            </c:choose>
                <c:param name="order" value="t.id,${ascDesc}"/>
            <c:if test="${param.id != null}">
                <c:param name="id" value="${param.id}"/>
            </c:if>
            </c:url>

            <c:url value="/driver/driver-home" var="sortDate">
                <c:set var="ascDesc" value="ASC"/>
            <c:choose>
            <c:when test="${param.order == 't.create_date,ASC'}">
                <c:set var="ascDesc" value="DESC"/>
            </c:when>
            <c:when test="${param.order == 't.create_date,DESC'}">
                <c:set var="ascDesc" value="ASC"/>
            </c:when>
            </c:choose>
                <c:param name="order" value="t.create_date,${ascDesc}"/>
            <c:if test="${param.status != null}">
                <c:param name="create_date" value="${param.create_date}"/>
            </c:if>
            </c:url>

            <c:url value="/driver/driver-home" var="sortStatus">
                <c:set var="ascDesc" value="ASC"/>
            <c:choose>
            <c:when test="${param.order == 't.status,ASC'}">
                <c:set var="ascDesc" value="DESC"/>
            </c:when>
            <c:when test="${param.order == 't.status,DESC'}">
                <c:set var="ascDesc" value="ASC"/>
            </c:when>
            </c:choose>
                <c:param name="order" value="t.status,${ascDesc}"/>
            <c:if test="${param.status != null}">
                <c:param name="status" value="${param.status}"/>
            </c:if>
            </c:url>

            <div style="display:block; text-align:center; ">
                <span><fmt:message key="trip"/></span>
            </div>
            <table style="width: -webkit-fill-available;">
                <tr>
                    <th><a href='<c:out value="${sortAmount}" />'><fmt:message key="id"/></a></th>
                    <th><fmt:message key="vehicle_id"/></th>
                    <th><fmt:message key="booking_id"/></th>
                    <th><a href='<c:out value="${sortDate}"/> '><fmt:message key="create_date"/></a></th>
                    <th><a href='<c:out value="${sortStatus}" />'><fmt:message key="status"/></a></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="trip" items="${trips}">
                    <tr>
                        <td>${trip.id}</td>
                        <td>${trip.vehicle.id}</td>
                        <td>${trip.booking.id}</td>
                        <td>${trip.create_date}</td>
                        <td>${trip.status}</td>
                        <td>
                            <c:if test="${trip.status eq 'OPEN'}">
                                <a type="submit" name="select"
                                   onclick="window.location='/user/create-booking?id=${trip.id}&user_id=${user.id}' "
                                   id="edit">
                                    <i class="fa fa-check" aria-hidden="true"></i>
                                </a>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="7">${trip.task}</td>
                    </tr>
                </c:forEach>
            </table>

            <hr>

            <table>
                <hr>
                <div style="display:block; text-align:center; ">
                    <span><fmt:message key="booking"/></span>
                </div>
                <table style="width: -webkit-fill-available;">
                    <tr>
                        <th><fmt:message key="id"/></th>
                        <th><fmt:message key="user_id"/></th>
                        <th><fmt:message key="trip"/></th>
                        <th><fmt:message key="vehicle_specification"/></th>
                        <th><fmt:message key="vehicle"/></th>
                        <th colspan="99"></th>
                    </tr>
                    <c:forEach var="booking" items="${bookings}">
                        <c:if test="${user.id eq booking.user.id}">
                            <tr>
                                <td>${booking.id}</td>
                                <td>${booking.user.id}</td>
                                <td>${booking.trip.id}</td>
                                <td>${booking.vehicle_specification}</td>
                                <td>
                                    <c:forEach var="trip" items="${trips}">
                                        <c:if test="${booking.id eq trip.booking.id}">
                                            ${trip.vehicle.id}

                                            <a type="submit" name="select"
                                               onclick="window.location='/driver/finish-trip?id=${trip.id}&bId=${booking.id}&vehicle_id=${trip.vehicle.id}'"
                                               id="edit">
                                                <i class="fa fa-check" aria-hidden="true"></i>
                                            </a>
                                        </c:if>
                                    </c:forEach>

                                </td>
                                <td>
                                    <a type="submit" name="select"
                                       onclick="window.location='/delete-booking?id=${booking.id}&role=${user.role}'"
                                       id="edit">
                                        <i class="fa fa-trash" aria-hidden="true"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
                <hr>

                <table>
                    <hr>
                    <div style="display:block; text-align:center; ">
                        <span><fmt:message key="vehicle"/></span>
                    </div>
                    <table style="width: -webkit-fill-available;">
                        <tr>
                            <th><fmt:message key="id"/></th>
                            <th><fmt:message key="name"/></th>
                            <th><fmt:message key="sits"/></th>
                            <th><fmt:message key="type"/></th>
                            <th><fmt:message key="car_class"/></th>
                            <th><fmt:message key="condition"/></th>


                        </tr>
                        <c:forEach var="vehicle" items="${vehicles}">
                            <tr>
                                <td>${vehicle.id}</td>
                                <td>${vehicle.name}</td>
                                <td>${vehicle.sits}</td>
                                <td>${vehicle.type}</td>
                                <td>${vehicle.car_class}</td>
                                <td>${vehicle.condition}</td>

                            </tr>
                        </c:forEach>
                    </table>

    </div>
</div>
</body>
</html>
