<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="nl"/>
<fmt:setBundle basename="be.janschraepen.hellokitty.i18n.Label" var="lang"/>

<div id='cssmenu'>
    <ul>
        <li>
            <a href='${pageContext.request.contextPath}/'>
                <span><fmt:message key="navigation.start" bundle="${lang}"/></span></a>
        </li>
        <li class='active has-sub'>
            <a href='${pageContext.request.contextPath}/person/type/list'>
                <span><fmt:message key="navigation.persontype" bundle="${lang}"/></span></a>
        </li>
        <li>
            <a href='${pageContext.request.contextPath}/person/list'>
                <span><fmt:message key="navigation.person" bundle="${lang}"/></span></a>
        </li>
        <li class='last'>
            <a href='${pageContext.request.contextPath}/cat/list'>
                <span><fmt:message key="navigation.cat" bundle="${lang}"/></span></a>
        </li>
    </ul>
</div>