<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="nl"/>
<fmt:setBundle basename="be.janschraepen.hellokitty.i18n.Label" var="lang"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>INDEX</title>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/general.js" />" type="text/javascript"></script>

    <link href="<c:url value="/resources/css/general.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="index">
    <div class="index__title">
        <h1>PAGINA'S</h1>
    </div>
    <div class="index__list">
        <ul>
            <li><a href="${pageContext.request.contextPath}/person/type/list"><fmt:message key="navigation.persontype" bundle="${lang}"/></a></li>
            <li><a href="${pageContext.request.contextPath}/person/list"><fmt:message key="navigation.person" bundle="${lang}"/></a></li>
        </ul>
    </div>
</div>
</body>
</html>