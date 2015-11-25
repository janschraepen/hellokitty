<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="nl"/>
<fmt:setBundle basename="be.janschraepen.hellokitty.i18n.Label" var="lang"/>

<form action="${actionUrl}/cat/upload" method="POST" enctype="multipart/form-data">
    <input type="hidden" name="_event" value="" />
    <input type="hidden" name="_referer" value="${referer}" />
    <input type="hidden" name="uuid" value="${entity.id}" />

    <div class="entity_pic">
        <div>
            <input type="file" name="picture" />
            <input type="button" value="Toevoegen" action="add-pic" />
        </div>
    </div>
    <div class="entity_actions">

    </div>
    <div class="entity__form pic">
        <div class="entity__form--1col">
            <img src="${actionUrl}/picture/cat/${entity.id}" alt="${entity.name}" />
        </div>
    </div>
</form>