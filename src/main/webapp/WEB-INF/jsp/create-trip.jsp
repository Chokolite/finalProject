<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Create Trip form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div id="login">
    <a href="" onclick="javascript:history.back(); return false;" id="back">
        <i class="fas fa-arrow-circle-left"></i>
    </a>
    <br>
    <div class="span_sign">
        <span><fmt:message key="createTrip"/></span>
    </div>

    <form action="/create-trip?role=${user.role}" method="post">

        <fieldset class="clearfix">
            <p><span class="fontawesome-table"></span>
                <input type="date" name="date" placeholder="<fmt:message key="createDate"/>" required>
            </p>

            <p><span class="fontawesome-book"></span>
                <input type="text" name="task" placeholder="<fmt:message key="task"/>" required>
            </p>

            <p><span class="fontawesome-book"></span>
            <select name="status" size="1">
            <option value="OPEN" selected>Open</option>
            <option value="IN_WORK">In work</option>
            <option value="CLOSED">Closed</option>
        </select>
            </p>
            <p></p>
            <p><input type="submit" name="submit" value="<fmt:message key="save"/>"></p>
        </fieldset>
    </form>
</div>
</body>
</html>