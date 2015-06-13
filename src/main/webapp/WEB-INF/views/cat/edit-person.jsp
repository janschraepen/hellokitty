<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="nl"/>
<fmt:setBundle basename="be.janschraepen.hellokitty.i18n.Label" var="lang"/>

<form action="${actionUrl}/cat/edit" method="POST">
    <input type="hidden" name="_event" value="" />
    <input type="hidden" name="uuid" value="${entity.id}" />

    <div class="entity_person">
        <div>
            <select name="personType">
                <option value="-1"></option>
                <c:forEach items="${personTypes}" var="personType">
                    <option value="${personType.id}">${personType.name}</option>
                </c:forEach>
            </select>
            <select name="person">
                <option value="-1"></option>
                <c:forEach items="${persons}" var="person">
                    <option value="${person.id}">${person.lastName} ${person.firstName} | ${person.addressLine1} ${person.addressLine2}</option>
                </c:forEach>
            </select>
            <input type="button" value="Toevoegen" action="add-person" />
        </div>
    </div>
    <div class="entity_actions">
        <input type="button" value="Terug" action="back" />
        <input type="button" value="Verwijderen" action="delete-person" />
    </div>
    <div class="entity__form">
        <table>
            <tr>
                <th></th>
                <th><fmt:message key="table.header.personType" bundle="${lang}"/></th>
                <th><fmt:message key="table.header.person" bundle="${lang}"/></th>
                <th><fmt:message key="table.header.address" bundle="${lang}"/></th>
                <th><fmt:message key="table.header.contact" bundle="${lang}"/></th>
            </tr>
            <c:forEach var="person" items="${entity.persons}">
                <tr>
                    <td class="id"><input type="checkbox" name="person-uuid" value="${person.id}" /></td>
                    <td>${person.personType}</td>
                    <td>${person.personFirstName} ${person.personLastName}</td>
                    <td>${person.personAddressLine1} ${person.personAddressLine2}</td>
                    <td>${person.personContacts}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</form>