<%@ page import="be.janschraepen.hellokitty.web.RequestAttribute" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="nl"/>
<fmt:setBundle basename="be.janschraepen.hellokitty.i18n.Label" var="lang"/>

<% if (request.getAttribute(RequestAttribute.ERROR_MSG) != null) { %>
    <div class="entity__error">
        <fmt:message key="label.errorMessage" bundle="${lang}"/><br/>
        <%= request.getAttribute(RequestAttribute.ERROR_MSG) %>
    </div>
<% } %>