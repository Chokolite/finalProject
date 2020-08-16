<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Create Booking"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="login">
    <span class="span_sign">
    <a href="" onclick="javascript:history.back(); return false;" id="back">
        <i class="fas fa-arrow-circle-left">
            <br>
            <fmt:message key="bcreate"/></i>
    </a>
    </span>

    <form action="/user/create-booking?role=${user.role}" method="post">
        <fieldset class="clearfix">
        <input type="text" name="user_id" value="${user.id}"/>
        <input type="text" name="id" value="${id}"/>
        <input type="text" name="specification" value="specification"/>
        <input type="submit" id="accept" name="submit" value="<fmt:message key="save"/>"/>
        </fieldset>
    </form>
</div>