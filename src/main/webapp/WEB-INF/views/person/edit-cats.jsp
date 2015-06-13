<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="nl"/>
<fmt:setBundle basename="be.janschraepen.hellokitty.i18n.Label" var="lang"/>

<form action="${actionUrl}/person/edit" method="POST">
    <input type="hidden" name="_event" value="" />
    <input type="hidden" name="uuid" value="${entity.id}" />

    <div class="entity_cat">
        <div>

        </div>
    </div>
    <div class="entity_actions">
        <input type="button" value="Terug" action="back" />
    </div>
    <div class="entity__form">
        <table>
            <tr>
                <th><fmt:message key="table.header.name" bundle="${lang}"/></th>
            </tr>
            <c:forEach var="cat" items="${entity.cats}">
                <tr>
                    <td>${cat.name}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</form>