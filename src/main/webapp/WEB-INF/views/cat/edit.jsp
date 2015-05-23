<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="nl"/>
<fmt:setBundle basename="be.janschraepen.hellokitty.i18n.Label" var="lang"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="cat.list.title" bundle="${lang}"/></title>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
        <script src="<c:url value="/resources/js/general.js" />" type="text/javascript"></script>

        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
        <link href="<c:url value="/resources/css/general.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="entity">
            <div class="entity__title">
                <h1>${title}</h1>
                <h4><fmt:message key="${description}" bundle="${lang}"/></h4>
            </div>
            <jsp:include page="./../error.jsp" />
            <div id="tabs" active-tab="<c:choose><c:when test="${activeTab != 0}">${activeTab}</c:when><c:otherwise>0</c:otherwise></c:choose>">
                <ul>
                    <li><a href="#tabs-common"><fmt:message key="tabs.common" bundle="${lang}"/></a></li>
                    <li><a href="#tabs-person"><fmt:message key="tabs.person" bundle="${lang}"/></a></li>
                </ul>
                <div id="tabs-common">
                    <jsp:include page="edit-common.jsp"/>
                </div>
                <div id="tabs-person">
                    <jsp:include page="edit-person.jsp"/>
                </div>
            </div>
        </div>
    </body>
</html>