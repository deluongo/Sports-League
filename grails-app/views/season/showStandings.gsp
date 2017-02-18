<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'team.label', default: 'Team')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
        .tg .tg-yw4l{vertical-align:top}
        </style>
    </head>
    <body>
        <a href="#list-team" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-team" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${teamList}" properties="['seed', 'homeGames', 'roadGames', 'location', 'name', 'gamesPlayed','wins', 'losses', 'winPercent',
                                                           'gamesBack', 'homeRecord', 'roadRecord', 'l10', 'streak', 'conference']" />

            <div class="pagination">
                <g:paginate total="${teamCount ?: 0}" />
            </div>
        </div>
        <div>
            <table class="tg">
                <tr>
                    <th class="tg-yw4l"></th>
                </tr>
            </table>
        </div>
    </body>
</html>