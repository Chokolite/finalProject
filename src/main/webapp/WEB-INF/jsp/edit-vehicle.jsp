<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Edit Vehicle form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<p>
<a href="" onclick="javascript:history.back(); return false;" id="back">
    <i class="fas fa-arrow-circle-left">
        <fmt:message key="editVehicle"/>
    </i>
</a>
</p>
<div id="user_info" class="tableInfo" >
    <form action="/admin/edit-vehicle" method="post">
        <table>
            <tr>
                <th><fmt:message key="id"/></th>
                <th><fmt:message key="name"/></th>
                <th><fmt:message key="sits"/></th>
                <th><fmt:message key="type"/></th>
                <th><fmt:message key="trunkSize"/></th>
                <th><fmt:message key="carClass"/></th>
                <th><fmt:message key="condition"/></th>
            </tr>

            <tr>
                <td><input type="number" name="id" value="${vehicle.id}" required></td>
                <td><input type="text" name="name" value="${vehicle.name}" required></td>
                <td><input type="number" name="sits" value="${vehicle.sits}" required></td>
                <td>
                    <select name="type" size="1">
                        <option value="${vehicle.type}" selected>${vehicle.type}</option>
                        <option value="SEDAN"><fmt:message key="sedan"/></option>
                        <option value="SUV"><fmt:message key="suv"/></option>
                        <option value="MINIVAN"><fmt:message key="minivan"/></option>
                        <option value="VAN"><fmt:message key="van"/></option>
                        <option value="MINITRUCK"><fmt:message key="minitruck"/></option>
                    </select>
                </td>
                <td>
                    <select name="trunkSize" size="1" required>
                        <option value="${vehicle.trunkSize}" selected>${vehicle.trunkSize}</option>
                        <option value="TINY"><fmt:message key="tiny"/></option>
                        <option value="SMALL"><fmt:message key="small"/></option>
                        <option value="MEDIUM"><fmt:message key="medium"/></option>
                        <option value="LARGE"><fmt:message key="large"/></option>
                    </select>
                </td>
                <td><input type="text" name="carClass" value="${vehicle.carClass}" required/></td>
                <td>
                    <p><select name="condition" size="1" required>
                        <option value="${vehicle.condition}" selected>${vehicle.condition}</option>
                        <option value="GOOD">Good</option>
                        <option value="BROKEN">Broken</option>
                    </select>
                    </p>
                </td>
            </tr>
            <tr style="background-color: transparent;">
                <td colspan="99">
                    <input id="accept" type="submit" name="submit" value="<fmt:message key="save"/>">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>