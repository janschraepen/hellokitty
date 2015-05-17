<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="nl"/>
<fmt:setBundle basename="be.janschraepen.hellokitty.i18n.Label" var="lang"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="person.list.title" bundle="${lang}"/></title>

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
            <form action="${actionUrl}/person/edit" method="POST">
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
                            <td class="lbl"><fmt:message key="label.type" bundle="${lang}"/>:</td>
                            <td class="val">
                               <select name="personTypeId">
                                   <option value="-1"></option>
                                   <c:forEach items="${personTypes}" var="type">
                                       <option
                                            value="${type.id}"
                                            <c:if test="${entity.personTypeId eq type.id}"> selected </c:if>
                                       >${type.name} (${type.shortCode})</option>
                                   </c:forEach>
                               </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="lbl"><fmt:message key="label.firstName" bundle="${lang}"/>:</td>
                            <td class="val"><input type="text" name="firstName" value="${entity.firstName}" /></td>
                        </tr>
                        <tr>
                            <td class="lbl"><fmt:message key="label.lastName" bundle="${lang}"/>:</td>
                            <td class="val"><input type="text" name="lastName" value="${entity.lastName}" /></td>
                        </tr>
                        <tr>
                            <td class="lbl"><fmt:message key="label.addressLine1" bundle="${lang}"/>:</td>
                            <td class="val"><input type="text" name="addressLine1" value="${entity.addressLine1}" /></td>
                        </tr>
                        <tr>
                            <td class="lbl"><fmt:message key="label.addressLine2" bundle="${lang}"/>:</td>
                            <td class="val"><input type="text" name="addressLine2" value="${entity.addressLine2}" /></td>
                        </tr>
                        <tr>
                            <td class="lbl"><fmt:message key="label.telephone" bundle="${lang}"/>:</td>
                            <td class="val"><input type="text" name="telephone" value="${entity.telephone}" /></td>
                        </tr>
                        <tr>
                            <td class="lbl"><fmt:message key="label.gsm" bundle="${lang}"/>:</td>
                            <td class="val"><input type="text" name="gsm" value="${entity.gsm}" /></td>
                        </tr>
                        <tr>
                            <td class="lbl"><fmt:message key="label.email" bundle="${lang}"/>:</td>
                            <td class="val"><input type="text" name="email" value="${entity.email}" /></td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </body>
</html>