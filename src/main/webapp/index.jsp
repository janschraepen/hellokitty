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

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script src="<c:url value="/resources/js/general.js" />" type="text/javascript"></script>

    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <link href="<c:url value="/resources/css/general.css" />" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/resources/css/leftnav-menu.css" />" rel="stylesheet" type="text/css" />
</head>
<body>

    <div class="application">
        <div class="application__navigation">
            <jsp:include page="WEB-INF/views/leftnav.jsp" />
        </div>
        <div class="application__content">
            &nbsp;
        </div>
    </div>

</body>
</html>