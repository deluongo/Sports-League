<!DOCTYPE html>
<html>
    <head>
        <title>NBA Standings</title>
        <asset:stylesheet src="application.css"/>
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />

        <!--  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css" /> -->
        <!-- <link rel="stylesheet" href="//cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css" /> -->

        <script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js">
        </script>
        <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js">
        </script>

        <script type="text/javascript" class="init">

            $(document).ready(function() {
                $('#standings').DataTable({bFilter: false, bInfo: false, searching: false, paging: false});
            } );

        </script>
    </head>
    <body>
        <div>
            <div class="row">
                <h1>Standings</h1>
                <div class="nav" role="navigation">
                    <ul class="nav nav-tabs">
                        <li><g:link action="showStandings" params="[conferenceIndex: '1']">Western Conference</g:link></li>
                        <li class="active"><g:link action="showStandings" params="[conferenceIndex: '2']">Eastern Conference</g:link></li>
                    </ul>
                </div>
                <table id="standings" class="table table-striped table-inverse table-bordered table-hover dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example_info" style="width: 100%;">
                    <thead>
                    <tr role="row">
                        <th>#</th>
                        <th>Team</th>
                        <th>W</th>
                        <th>L</th>
                        <th>Win %</th>
                        <th class='hidden-xs'>GB</th>
                        <th class='hidden-xs'>Home</th>
                        <th class='hidden-xs'>Road</th>
                        <th>L10</th>
                        <th class='hidden-xs'>Streak</th>
                        <th class='hidden-xs'>Scored</th>
                        <th class='hidden-xs'>Allowed</th>
                        <th class='hidden-xs'>Diff.</th>
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
                            <td class='hidden-xs'>${team.gamesBack?.encodeAsHTML()}</td>
                            <td class='hidden-xs'>${team.homeRecord?.encodeAsHTML()}</td>
                            <td class='hidden-xs'>${team.roadRecord?.encodeAsHTML()}</td>
                            <td>${team.l10?.encodeAsHTML()}</td>
                            <td class='hidden-xs'>${team.streak?.encodeAsHTML()}</td>
                            <td class='hidden-xs'>${team.scored?.encodeAsHTML()}</td>
                            <td class='hidden-xs'>${team.allowed?.encodeAsHTML()}</td>
                            <td class='hidden-xs'>${team.delta?.encodeAsHTML()}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>