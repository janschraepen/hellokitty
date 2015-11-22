<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

    </div>
    <div class="entity__form">
        <div class="entity__form--1col">
            <table>
                <tr>
                    <th><fmt:message key="table.header.name" bundle="${lang}"/></th>
                </tr>
                <c:forEach var="cat" items="${entity.cats}">
                    <tr>
                        <td>
                            <c:url value="${actionUrl}/cat/edit" var="catDetailUrl">
                                <c:param name="_event" value="edit" />
                                <c:param name="_referer" value="${referer}" />
                                <c:param name="uuid" value="${cat.id}" />
                            </c:url>
                            <a href="${catDetailUrl}" target="_self">${cat.name}</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</form>