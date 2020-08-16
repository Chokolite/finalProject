<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Create Vehicle form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div id="login">
    <a href="" onclick="javascript:history.back(); return false;" id="back">
        <i class="fas fa-arrow-circle-left"></i>
    </a>
    <br>
    <div class="span_sign">
        <span><fmt:message key="createVehicle"/></span>
    </div>

    <form action="/admin/create-vehicle" method="post">

        <fieldset class="clearfix">
            <p><span class="fontawesome-table"></span>
                <input type="text" name="name" placeholder="<fmt:message key="name"/>" required>
            </p>
            <p><span class="fontawesome-table"></span>
                <input type="number" name="sits" placeholder="<fmt:message key="sits"/>" required>
            </p>
            <p><span class="fontawesome-book"></span>
                <select name="type" size="1">
                    <option value="SEDAN" selected><fmt:message key="sedan"/></option>
                    <option value="SUV"><fmt:message key="suv"/></option>
                    <option value="MINIVAN"><fmt:message key="minivan"/></option>
                    <option value="VAN"><fmt:message key="van"/></option>
                    <option value="MINITRUCK"><fmt:message key="minitruck"/></option>
                </select>
            </p>
            <p><span class="fontawesome-book"></span>
                <select name="trunk_size" size="1">
                    <option value="TINY" selected><fmt:message key="tiny"/></option>
                    <option value="SMALL"><fmt:message key="small"/></option>
                    <option value="MEDIUM"><fmt:message key="medium"/></option>
                    <option value="LARGE"><fmt:message key="large"/></option>
                </select>
            </p>

            <p>
                <span class="fontawesome-table"></span>
                <input type="text" name="car_class" placeholder="<fmt:message key="car_class"/>" required>
            </p>
            <p><span class="fontawesome-book"></span>
                <select name="condition" size="1">
                    <option value="GOOD" selected><fmt:message key="good"/></option>
                    <option value="BROKEN"><fmt:message key="broken"/></option>
                </select>
            </p>

            <p><input type="submit" name="submit" value="<fmt:message key="save"/>"></p>
        </fieldset>
    </form>
</div>
</body>
</html>