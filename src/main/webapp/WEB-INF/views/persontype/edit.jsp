<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="nl"/>
<fmt:setBundle basename="be.janschraepen.hellokitty.i18n.Label" var="lang"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="persontype.list.title" bundle="${lang}"/></title>

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
                <jsp:include page="../leftnav.jsp" />
            </div>
            <div class="application__content">
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
                            <form action="${actionUrl}/person/type/edit" method="POST">
                                <input type="hidden" name="_event" value="save" />
                                <input type="hidden" name="_referer" value="${referer}" />
                                <input type="hidden" name="uuid" value="${entity.id}" />

                                <div class="entity_actions">
                                    <input type="button" value="Terug" action="back" />
                                    <input type="button" value="Bewaren" action="save" />
                                    <input type="button" value="Verwijderen" action="delete" />
                                </div>
                                <div class="entity__form">
                                    <div class="entity__form--left">
                                        <table>
                                            <tr>
                                                <td class="lbl"><fmt:message key="label.code" bundle="${lang}"/>*:</td>
                                                <td class="val"><input type="text" name="shortCode" value="${entity.shortCode}" /></td>
                                            </tr>
                                            <tr>
                                                <td class="lbl"><fmt:message key="label.description" bundle="${lang}"/>*:</td>
                                                <td class="val"><input type="text" name="name" value="${entity.name}" /></td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div class="entity__form--right">
                                        &nbsp;
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </body>
</html>