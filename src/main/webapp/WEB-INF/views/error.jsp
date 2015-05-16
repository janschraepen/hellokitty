<% if (request.getAttribute("errorMessage") != null) { %>
    <div class="entity__error">
        <fmt:message key="${errorMessage}" bundle="${lang}"/>
    </div>
<% } %>