<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>NBA Standings</title><!-- Bootstrap is a responsive web design framework with CSS and JS that we'll be using later in the course -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/css/dataTables.bootstrap4.min.css" rel="stylesheet">
        <asset:stylesheet src="application.css"/>
    </head>
    <body>
        <a href="#list-team" class="skip bg-primary " tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div>
            <div class="row">
                <div class="nav" role="navigation">
                    <ul>
                        <li><g:link action="showStandings" params="[conferenceIndex: '1']">Western Conference</g:link></li>
                        <li><g:link action="showStandings" params="[conferenceIndex: '2']">Eastern Conference</g:link></li>
                    </ul>
                </div>
                <table id="example" class="table table-striped table-inverse table-bordered table-hover dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example_info" style="width: 100%;">
                    <thead>
                    <tr role="row">
                        <th>#</th>
                        <th>Team</th>
                        <th>W</th>
                        <th>L</th>
                        <th>Win %</th>
                        <th>GB</th>
                        <th>Home</th>
                        <th>Road</th>
                        <th>L10</th>
                        <th>Streak</th>
                    </thead>
                    <tbody>
                    <g:each status="i" in="${teamList.sort{it.seed}}" var="team">
                        <!-- Alternate CSS classes for the rows. -->
                        <tr role="row" class="${ (i % 2) == 0 ? 'even' : 'odd'}">
                            <td class="sorting_1">${team.seed?.encodeAsHTML()}</td>
                            <td>${team.location?.encodeAsHTML()} ${team.name?.encodeAsHTML()}</td>
                            <td>${team.wins?.encodeAsHTML()}</td>
                            <td>${team.losses?.encodeAsHTML()}</td>
                            <td>${team.winPercent?.encodeAsHTML()}</td>
                            <td>${team.gamesBack?.encodeAsHTML()}</td>
                            <td>${team.homeRecord?.encodeAsHTML()}</td>
                            <td>${team.roadRecord?.encodeAsHTML()}</td>
                            <td>${team.l10?.encodeAsHTML()}</td>
                            <td>${team.streak?.encodeAsHTML()}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>