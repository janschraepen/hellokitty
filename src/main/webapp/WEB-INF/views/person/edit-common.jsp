<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="nl"/>
<fmt:setBundle basename="be.janschraepen.hellokitty.i18n.Label" var="lang"/>

<form action="${actionUrl}/person/edit" method="POST">
    <input type="hidden" name="_event" value="save" />
    <input type="hidden" name="uuid" value="${entity.id}" />

    <div class="entity_actions">
        <input type="button" value="Terug" action="back" />
        <input type="button" value="Bewaren" action="save" />
        <input type="button" value="Verwijderen" action="delete" />
    </div>
    <div class="entity__form">
        <table>
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
        </table>
    </div>
</form>