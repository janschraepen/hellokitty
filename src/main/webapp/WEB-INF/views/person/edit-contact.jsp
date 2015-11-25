<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="nl"/>
<fmt:setBundle basename="be.janschraepen.hellokitty.i18n.Label" var="lang"/>

<form action="${actionUrl}/person/edit" method="POST">
    <input type="hidden" name="_event" value="" />
    <input type="hidden" name="_referer" value="${referer}" />
    <input type="hidden" name="uuid" value="${entity.id}" />

    <div class="entity_contact">
        <div>
            <select name="contactType">
                <option value="-1"></option>
                <c:forEach items="${contactTypes}" var="contactType">
                    <option value="${contactType.name()}"><fmt:message key="${contactType.labelKey}" bundle="${lang}"/></option>
                </c:forEach>
            </select>
            <input type="text" name="contactValue" value="" />
            <input type="button" value="Toevoegen" action="add-contact" />
        </div>
    </div>
    <div class="entity_actions">
        <input type="button" value="Verwijderen" action="delete-contact" />
    </div>
    <div class="entity__form">
        <div class="entity__form--1col">
            <table>
                <tr>
                    <th></th>
                    <th class="w15pct"><fmt:message key="table.header.contactType" bundle="${lang}"/></th>
                    <th><fmt:message key="table.header.value" bundle="${lang}"/></th>
                </tr>
                <c:forEach var="contact" items="${entity.contacts}">
                    <tr>
                        <td class="id"><input type="checkbox" name="contact-uuid" value="${contact.id}" /></td>
                        <td class="w15pct"><fmt:message key="${contact.type.labelKey}" bundle="${lang}"/></td>
                        <td>${contact.value}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</form>