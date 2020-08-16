<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Dispatcher Panel"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="la" tagdir="/WEB-INF/tags" %>

<la:lang/>

<div id="wrapper">
    <div id="user_info">
        <div id="title_user">
            <div id="icon"><i class="fas fa-user" style="padding-top: 8px;"></i></div>
            <div id="welcome_user"><fmt:message key="dispatcher"/> ${user.name}</div>
            <a href="/logout"><i class="fas fa-sign-out-alt"></i></a>
        </div>
        <hr>
        <span><fmt:message key="email"/>: ${user.email}</span><br>
        <span><fmt:message key="acctype"/>: ${user.role}</span><br>
    </div>
    <div id="users" class="tableInfo">

        <table>
            <c:url value="/dispatcher/dispatcher-home" var="sortAmount">
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

            <c:url value="/dispatcher/dispatcher-home" var="sortDate">
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

            <c:url value="/dispatcher/dispatcher-home" var="sortStatus">
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

            <hr>
            <a href="/create-trip" id="add"><i class="fas fa-plus-circle"></i></a>
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
                            <a type="submit" name="select"
                               onclick="window.location='/dispatcher/edit-trip?id='+${trip.id}" id="edit">
                                <i class="fa fa-cog" aria-hidden="true"></i>
                            </a>
                        </td>
                        <td>
                            <a type="submit" name="select"
                               onclick="window.location='/dispatcher/delete-trip?id=${trip.id}&role=${user.role}'"
                               id="edit">
                                <i class="fa fa-trash" aria-hidden="true"></i>
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="99">${trip.task}</td>
                    </tr>
                </c:forEach>
            </table>
            <table>
                <hr>
                <div style="display:block; text-align:center; ">
                    <span><fmt:message key="booking"/></span>
                </div>
                <table style="width: -webkit-fill-available; ">
                    <tr>
                        <th><fmt:message key="id"/></th>
                        <th><fmt:message key="user_id"/></th>
                        <th><fmt:message key="trip"/></th>
                        <th><fmt:message key="vehicle_specification"/></th>
                        <th></th>
                    </tr>
                    <c:forEach var="booking" items="${bookings}">
                        <tr>
                            <td>${booking.id}</td>
                            <td>${booking.user.id}</td>
                            <td>${booking.trip.id}</td>
                            <td>${booking.vehicle_specification}</td>
                            <td>
                                <a type="submit" name="select"
                                   onclick="window.location='/delete-booking?id=${booking.id}&role=${user.role}'"
                                   id="edit">
                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

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

                    <hr>


    </div>
</div>
</body>
</html>
