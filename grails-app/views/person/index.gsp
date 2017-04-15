 <!DOCTYPE html>
<html>
    <head>
        <title>Players List</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <asset:stylesheet src="bootstrap.css"/>
        <asset:stylesheet src="devon.css"/>

        <style>
        body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}
        .w3-sidenav a,.w3-sidenav h4 {font-weight:bold}
        </style>

        <!--  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css" /> -->
        <!-- <link rel="stylesheet" href="//cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css" /> -->

        <script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js">
        </script>
        <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js">
        </script>

        <script type="text/javascript" class="init">

            $(document).ready(function() {
                $('#players-table').DataTable({bFilter: false, bInfo: false, searching: false, paging: false});
            } );

        </script>
    </head>
    <body class="w3-light-grey w3-content" style="max-width:1600px">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Devon's League</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li id="standingsNav"><g:link controller="season" action="showStandings">Standings</g:link></li>
                        <li id="leaderboard"><g:link controller="season" action="leaderboard">Stat Leaders</g:link></li>
                        <li id="players" class="active"><g:link controller="person" action="index">Players</g:link></li>
                        <li id="player"><g:link controller="league" action="person">Stephen Curry</g:link></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- !PAGE CONTENT! -->
        <div class="w3-main">
            <div class="w3-row">
                <h1 class="margin-16">Players List</h1>
                <table id="players-table" class="table table-striped table-inverse table-bordered table-hover dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example_info">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Team</th>
                        <th>#</th>
                        <th>Height</th>
                        <th>Weight</th>
                        <th class='hidden-xs'>University</th>
                        <th class='hidden-xs'>Birthplace</th>
                        <th class='hidden-xs'>Birthdate</th>
                    </tr>
                    </thead>
                    <tbody>
                        <g:each status="i" in="${personList.sort{it.lastName}}" var="player">
                            <!-- Alternate CSS classes for the rows. -->
                            <g:if test="${player.role == "player"}">
                                <tr role="row" class="${ (i % 2) == 0 ? 'even' : 'odd'}">
                                        <td><a href="/league/person/stats/${player.id}" class="w3-hover-text-blue ">${player.firstName?.encodeAsHTML()} ${player.lastName?.encodeAsHTML()}</a></td>
                                        <td>${player.team.name?.encodeAsHTML()}</td>
                                        <td>${player.number?.encodeAsHTML()}</td>
                                        <td>${player.height?.encodeAsHTML()}</td>
                                        <td>${player.weight?.encodeAsHTML()}</td>
                                        <td class='hidden-xs'>${player.universityAttended?.encodeAsHTML()}</td>
                                        <td class='hidden-xs'>${player.birthPlace?.encodeAsHTML()}</td>
                                        <td class='hidden-xs'>${player.birthDate.toString().take(9)?.encodeAsHTML()}</td>
                                </tr>
                            </g:if>
                        </g:each>

                        <div class="pagination">
                            <g:paginate total="${personCount ?: 0}" />
                        </div>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>