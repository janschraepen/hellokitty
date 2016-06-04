<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="nl"/>
<fmt:setBundle basename="be.janschraepen.hellokitty.i18n.Label" var="lang"/>

<form action="${actionUrl}/cat/edit" method="POST">
    <input type="hidden" name="_event" value="" />
    <input type="hidden" name="_referer" value="${referer}" />
    <input type="hidden" name="uuid" value="${entity.id}" />

    <div class="entity_person">
        <div>
            <select name="personType">
                <option value="-1"></option>
                <c:forEach items="${personTypes}" var="personType">
                    <option value="${personType.id}">${personType.name}</option>
                </c:forEach>
            </select>
            <input id="personAutocomplete">
            <input id="personId" type="hidden" name="person" value="-1"/>
            <script>
                $(function() {
                    var listOfPersons = [
                        <c:forEach items="${persons}" var="person">
                            {
                                "pk": "${person.id}",
                                "label": "${person.lastName} ${person.firstName}",
                                "address": "${person.addressLine1} ${person.addressLine2}"
                            },
                        </c:forEach>
                        {
                            "pk": "-1",
                            "label": "",
                            "address": ""
                        }
                    ];
                    $("#personAutocomplete").autocomplete({
                        minLength: 0,
                        source: listOfPersons,
                        focus: function(event, ui) {
                            $("#personAutocomplete" ).val(ui.item.label);
                            return false;
                        },
                        select: function(event, ui) {
                            $("#personAutocomplete").val(ui.item.label);
                            $("#personId").val( ui.item.pk );
                            return false;
                        }
                    }).autocomplete("instance")._renderItem = function(ul, item) {
                        return $( "<li>" )
                                .append("<a>" + item.label + "<br><span class=\"autocomplete-subtext\">" + item.address + "</span></a>")
                                .appendTo(ul);
                    };
                });
            </script>
            <input type="button" value="Toevoegen" action="add-person" />
        </div>
    </div>
    <div class="entity_actions">
        <input type="button" value="Verwijderen" action="delete-person" />
    </div>
    <div class="entity__form">
        <div class="entity__form--1col">
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
                        <td class="w20pct">${person.personType}</td>
                        <td class="w20pct">
                            <c:url value="${actionUrl}/person/edit" var="personDetailUrl">
                                <c:param name="_event" value="edit" />
                                <c:param name="_referer" value="${referer}" />
                                <c:param name="uuid" value="${person.personId}" />
                            </c:url>
                            <a href="${personDetailUrl}" target="_self">${person.personLastName} ${person.personFirstName}</a>
                        </td>
                        <td class="w30pct">${person.personAddressLine1}, ${person.personAddressLine2}</td>
                        <td>
                            <c:if test="${not empty person.personContacts}">
                                <div class="open">+</div>
                                <div class="close" style="display: none;">-</div>
                                <div class="contactCard" style="display: none;">${person.personContacts}</div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</form>