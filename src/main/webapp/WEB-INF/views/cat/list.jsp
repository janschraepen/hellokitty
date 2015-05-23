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
                <h1><fmt:message key="${title}" bundle="${lang}"/></h1>
                <h4><fmt:message key="${description}" bundle="${lang}"/></h4>
            </div>
            <jsp:include page="./../error.jsp" />
            <form action="${actionUrl}/cat/edit" method="POST">
                <input type="hidden" name="_event" value="" />
                <div class="entity_search">
                    <input type="text" name="search"/>
                    <input type="button" value="Zoeken" action="search" />
                </div>
                <div class="entity_actions">
                    <a href="/hellokitty" target="_self"><fmt:message key="label.close" bundle="${lang}"/></a> |
                    <input type="button" value="Toevoegen" action="new" />
                    <input type="button" value="Wijzigen" action="edit" />
                    <input type="button" value="Verwijderen" action="delete" />
                </div>
                <div class="entity__list">
                    <table>
                        <tr>
                            <th></th>
                            <th><fmt:message key="table.header.name" bundle="${lang}"/></th>
                            <th><fmt:message key="table.header.breed" bundle="${lang}"/></th>
                            <th><fmt:message key="table.header.gender" bundle="${lang}"/></th>
                            <th><fmt:message key="table.header.age" bundle="${lang}"/></th>
                            <th><fmt:message key="table.header.neutered" bundle="${lang}"/></th>
                            <th><fmt:message key="table.header.chipped" bundle="${lang}"/></th>
                        </tr>
                        <jsp:useBean id="now" class="java.util.Date" />
                        <fmt:formatDate var="year" value="${now}" pattern="yyyy" />
                        <c:forEach var="item" items="${listItems}">
                            <tr>
                                <td class="id"><input type="checkbox" name="uuid" value="${item.id}" /></td>
                                <td class="l">${item.name}</td>
                                <td class="l">${item.breed}</td>
                                <td class="l">${item.gender}</td>
                                <td class="l">
                                    ${item.age}
                                    (${year - item.age})
                                </td>
                                <td class="l">
                                    <input type="checkbox" <c:if test="${item.neutered}">checked="checked"</c:if> readonly />
                                </td>
                                <td class="l">
                                    <input type="checkbox" <c:if test="${item.chipped}">checked="checked"</c:if> readonly />
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </form>
        </div>
    </body>
</html>