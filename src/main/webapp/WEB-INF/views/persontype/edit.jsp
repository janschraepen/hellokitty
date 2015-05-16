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

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="<c:url value="/resources/js/general.js" />" type="text/javascript"></script>

        <link href="<c:url value="/resources/css/general.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="entity">
            <div class="entity__title">
                <h1>${title}</h1>
                <h4><fmt:message key="${description}" bundle="${lang}"/></h4>
            </div>
            <jsp:include page="./../error.jsp" />
            <form action="http://localhost:8080/hellokitty/person/type/edit" method="POST">
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
                            <td class="lbl"><fmt:message key="label.code" bundle="${lang}"/>:</td>
                            <td class="val"><input type="text" name="shortCode" value="${entity.shortCode}" /></td>
                        </tr>
                        <tr>
                            <td class="lbl"><fmt:message key="label.description" bundle="${lang}"/>:</td>
                            <td class="val"><input type="text" name="name" value="${entity.name}" /></td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </body>
</html>