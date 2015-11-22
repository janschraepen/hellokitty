<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="nl"/>
<fmt:setBundle basename="be.janschraepen.hellokitty.i18n.Label" var="lang"/>

<form action="${actionUrl}/cat/edit" method="POST">
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
                    <td class="lbl"><fmt:message key="label.name" bundle="${lang}"/>*:</td>
                    <td class="val"><input type="text" name="name" value="${entity.name}" /></td>
                </tr>
                <tr>
                    <td class="lbl"><fmt:message key="label.breed" bundle="${lang}"/>:</td>
                    <td class="val"><input type="text" name="breed" value="${entity.breed}" /></td>
                </tr>
                <tr>
                    <td class="lbl"><fmt:message key="label.age" bundle="${lang}"/>:</td>
                    <td class="val">
                        <input type="text" name="age" value="${entity.age}" onkeypress="return isNumber(event)" />
                        <jsp:useBean id="now" class="java.util.Date" />
                        <fmt:formatDate var="year" value="${now}" pattern="yyyy" />
                        <div class="currentAge">${year - entity.age}</div>
                    </td>
                </tr>
                <tr>
                    <td class="lbl"><fmt:message key="label.gender" bundle="${lang}"/>*:</td>
                    <td class="val">
                        <input type="radio" name="gender" value="M" <c:if test="${entity.gender.name() == 'M'}">checked</c:if> /><fmt:message key="label.gender.m" bundle="${lang}"/>
                        <input type="radio" name="gender" value="V" <c:if test="${entity.gender.name() == 'V'}">checked</c:if> /><fmt:message key="label.gender.v" bundle="${lang}"/>
                    </td>
                </tr>
                <tr>
                    <td class="lbl"><fmt:message key="label.neutered" bundle="${lang}"/>*:</td>
                    <td class="val"><input type="checkbox" name="neutered" value="true" <c:if test="${entity.neutered}">checked</c:if> /></td>
                </tr>
                <tr>
                    <td class="lbl"><fmt:message key="label.chipped" bundle="${lang}"/>*:</td>
                    <td class="val"><input type="checkbox" name="chipped" value="true" <c:if test="${entity.chipped}">checked</c:if> /></td>
                </tr>
            </table>
        </div>
        <div class="entity__form--right">
            <table>
                <tr>
                    <td class="lbl"><fmt:message key="label.attention" bundle="${lang}"/>:</td>
                    <td class="val"><textarea rows="5" cols="40" name="attention">${entity.attention}</textarea></td>
                </tr>
                <tr>
                    <td class="lbl"><fmt:message key="label.behavioral" bundle="${lang}"/>:</td>
                    <td class="val"><textarea rows="5" cols="40" name="behavioral">${entity.behavioral}</textarea></td>
                </tr>
                <tr>
                    <td class="lbl"><fmt:message key="label.nutrition" bundle="${lang}"/>:</td>
                    <td class="val"><textarea rows="5" cols="40" name="nutrition">${entity.nutrition}</textarea></td>
                </tr>
                <tr>
                    <td class="lbl"><fmt:message key="label.extrainfo" bundle="${lang}"/>:</td>
                    <td class="val"><textarea rows="5" cols="40" name="extraInfo">${entity.extraInfo}</textarea></td>
                </tr>
            </table>
        </div>

    </div>
</form>

<script type="application/javascript">
    isAge($('form input[name="age"]'));
</script>