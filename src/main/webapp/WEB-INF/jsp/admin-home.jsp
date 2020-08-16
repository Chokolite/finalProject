<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Admin Panel"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="la" tagdir="/WEB-INF/tags" %>

<la:lang/>

<div id="wrapper">
    <div id="user_info">
        <div id="title_user">
            <div id="icon"><i class="fas fa-user" style="padding-top: 8px;"></i></div>
            <div id="welcome_user"><fmt:message key="admin"/> ${user.name}</div>
            <a href="/logout"><i class="fas fa-sign-out-alt"></i></a>
        </div>
        <hr>
        <span><fmt:message key="email"/>: ${user.email}</span><br>
        <span><fmt:message key="acctype"/>: ${user.role}</span><br>
    </div>
    <div id="users" class="tableInfo">
        <table>
            <hr>
            <a href="/admin/create-user" id="add"><i class="fas fa-plus-circle"></i></a>
            <div style="display:block; text-align:center; ">
                <span><fmt:message key="users"/></span>
            </div>
            <table style="width: -webkit-fill-available;">
                <tr>
                    <th><fmt:message key="id"/></th>
                    <th><fmt:message key="username"/></th>
                    <th><fmt:message key="email"/></th>
                    <th><fmt:message key="acctype"/></th>
                    <th><fmt:message key="block"/></th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.role}</td>
                        <td>
                            <form method="post" action="/admin/user-update-enabled" id="lockForm">
                                <fmt:message key="enabled" var="enabled"/>
                                <fmt:message key="disabled" var="disabled"/>
                                <input name="userId" type="hidden" value="${user.id}">
                                <input name="enabled" type="hidden" value="${!user.enabled}">
                                <input type="submit" value="${user.enabled ? disabled : enabled}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <hr>

            <table>
                <c:url value="/admin/admin-home" var="sortAmount">
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

                <c:url value="/admin/admin-home" var="sortDate">
                    <c:set var="ascDesc" value="ASC"/>
                    <c:choose>
                        <c:when test="${param.order == 't.createDate,ASC'}">
                            <c:set var="ascDesc" value="DESC"/>
                        </c:when>
                        <c:when test="${param.order == 't.createDate,DESC'}">
                            <c:set var="ascDesc" value="ASC"/>
                        </c:when>
                    </c:choose>
                    <c:param name="order" value="t.createDate,${ascDesc}"/>
                    <c:if test="${param.status != null}">
                        <c:param name="createDate" value="${param.createDate}"/>
                    </c:if>
                </c:url>

                <c:url value="/admin/admin-home" var="sortStatus">
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
                        <th><fmt:message key="vehicleId"/></th>
                        <th><fmt:message key="bookingId"/></th>
                        <th><a href='<c:out value="${sortDate}"/> '><fmt:message key="createDate"/></a></th>
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
                            <td>${trip.createDate}</td>
                            <td>${trip.status}</td>
                            <td>
                                <a type="submit" name="select"
                                   onclick="window.location='/user/create-booking?id=${trip.id}&userId=${user.id}'
                                           return false"
                                   id="edit">
                                    <i class="fa fa-check" aria-hidden="true"></i>
                                </a>
                            </td>
                            <td>
                                <a type="submit" name="select"
                                   onclick="window.location='/admin/edit-trip?id=${trip.id}'" id="edit">
                                    <i class="fa fa-cog" aria-hidden="true"></i>
                                </a>
                            </td>
                            <td>
                                <a type="submit" name="select"
                                   onclick="window.location='/admin/delete-trip?id=${trip.id}&role=${user.role}'"
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

                <hr>

                <table>
                    <hr>
                    <div style="display:block; text-align:center; ">
                        <span><fmt:message key="booking"/></span>
                    </div>
                    <table style="width: -webkit-fill-available;">
                        <tr>
                            <th><fmt:message key="id"/></th>
                            <th><fmt:message key="userId"/></th>
                            <th><fmt:message key="trip"/></th>
                            <th><fmt:message key="vehicleSpecification"/></th>
                            <th></th>
                        </tr>
                        <c:forEach var="booking" items="${bookings}">
                            <tr>
                                <td>${booking.id}</td>
                                <td>${booking.user.id}</td>
                                <td>${booking.trip.id}</td>
                                <td>${booking.vehicleSpecification}</td>
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

                    <hr>
                </table>
            </table>
        </table>
        <table>
            <hr>
            <a href="/admin/create-vehicle" id="add"><i class="fas fa-plus-circle"></i></a>
            <div style="display:block; text-align:center; ">
                <span><fmt:message key="vehicle"/></span>
            </div>
            <table style="width: -webkit-fill-available;">
                <tr>
                    <th><fmt:message key="id"/></th>
                    <th><fmt:message key="name"/></th>
                    <th><fmt:message key="sits"/></th>
                    <th><fmt:message key="type"/></th>
                    <th><fmt:message key="carClass"/></th>
                    <th><fmt:message key="condition"/></th>
                    <th></th>
                    <th colspan="99"></th>


                </tr>
                <c:forEach var="vehicle" items="${vehicles}">
                    <tr>
                        <td>${vehicle.id}</td>
                        <td>${vehicle.name}</td>
                        <td>${vehicle.sits}</td>
                        <td>${vehicle.type}</td>
                        <td>${vehicle.carClass}</td>
                        <td>${vehicle.condition}</td>
                        <td>
                            <a type="submit" name="select"
                               onclick="window.location='/admin/delete-vehicle?id=${vehicle.id}'"
                               id="edit">
                                <i class="fa fa-trash" aria-hidden="true"></i>
                            </a>
                        </td>
                        <td>
                            <a type="submit" name="select"
                               onclick="window.location='/admin/edit-vehicle?id=${vehicle.id}'" id="edit">
                                <i class="fa fa-cog" aria-hidden="true"></i>
                            </a>
                        </td>

                    </tr>
                </c:forEach>
            </table>
    </div>
    </body>
    </html>
