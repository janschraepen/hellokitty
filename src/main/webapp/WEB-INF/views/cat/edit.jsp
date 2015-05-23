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
                </ul>
                <div id="tabs-common">
                    <form action="${actionUrl}/cat/edit" method="POST">
                        <input type="hidden" name="_event" value="" />
                        <input type="hidden" name="uuid" value="${entity.id}" />

                        <div class="entity_actions">
                            <input type="button" value="Terug" action="back" />
                            <input type="button" value="Bewaren" action="save" />
                            <input type="button" value="Verwijderen" action="delete" />
                        </div>
                        <div class="entity__form">
                            <table>
                                <tr>
                                    <td class="lbl"><fmt:message key="label.name" bundle="${lang}"/>:</td>
                                    <td class="val"><input type="text" name="name" value="${entity.name}" /></td>
                                </tr>
                                <tr>
                                    <td class="lbl"><fmt:message key="label.breed" bundle="${lang}"/>:</td>
                                    <td class="val"><input type="text" name="breed" value="${entity.breed}" /></td>
                                </tr>
                                <tr>
                                    <td class="lbl"><fmt:message key="label.age" bundle="${lang}"/>:</td>
                                    <td class="val">
                                        <input type="text" name="age" value="${entity.age}" />
                                        <jsp:useBean id="now" class="java.util.Date" />
                                        <fmt:formatDate var="year" value="${now}" pattern="yyyy" />
                                        <div class="currentAge">${year - entity.age}</div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="lbl"><fmt:message key="label.gender" bundle="${lang}"/>:</td>
                                    <td class="val">
                                        <input type="radio" name="gender" value="M" <c:if test="${entity.gender.name() == 'M'}">checked</c:if> /><fmt:message key="label.gender.m" bundle="${lang}"/>
                                        <input type="radio" name="gender" value="V" <c:if test="${entity.gender.name() == 'V'}">checked</c:if> /><fmt:message key="label.gender.v" bundle="${lang}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="lbl"><fmt:message key="label.neutered" bundle="${lang}"/>:</td>
                                    <td class="val"><input type="checkbox" name="neutered" value="true" <c:if test="${entity.neutered}">checked</c:if> /></td>
                                </tr>
                                <tr>
                                    <td class="lbl"><fmt:message key="label.chipped" bundle="${lang}"/>:</td>
                                    <td class="val"><input type="checkbox" name="chipped" value="true" <c:if test="${entity.chipped}">checked</c:if> /></td>
                                </tr>
                                <tr>
                                    <td class="lbl"><fmt:message key="label.attention" bundle="${lang}"/>:</td>
                                    <td class="val"><input type="text" name="attention" value="${entity.attention}" /></td>
                                </tr>
                                <tr>
                                    <td class="lbl"><fmt:message key="label.behavioral" bundle="${lang}"/>:</td>
                                    <td class="val"><input type="text" name="behavioral" value="${entity.behavioral}" /></td>
                                </tr>
                                <tr>
                                    <td class="lbl"><fmt:message key="label.nutrition" bundle="${lang}"/>:</td>
                                    <td class="val"><input type="text" name="nutrition" value="${entity.nutrition}" /></td>
                                </tr>
                            </table>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>