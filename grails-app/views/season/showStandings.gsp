<!DOCTYPE html>
<html>
    <head>
        <title>NBA Standings</title>
        <asset:stylesheet src="application.css"/>


        <!--  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css" /> -->
        <!-- <link rel="stylesheet" href="//cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css" /> -->

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
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
                        <li id="standingsNav" class="active"><g:link controller="season" action="showStandings">Standings</g:link></li>
                        <li id="leaderboard"><g:link controller="season" action="leaderboard">Stat Leaders</g:link></li>
                        <li id="players"><g:link controller="person" action="index">Players</g:link></li>
                        <li id="player"><g:link controller="league" action="person">Stephen Curry</g:link></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div>
            <div class="row">
                <h1 class="margin-16">Standings</h1>
                <div class="nav" role="navigation">
                    <ul class="nav nav-tabs">
                        <li class="<g:if test="${conferenceIndex == '1'}">active</g:if>"><g:link action="showStandings" params="[conferenceIndex: '1']">Western Conference</g:link></li>
                        <li class="<g:if test="${conferenceIndex == '2'}">active</g:if>"><g:link action="showStandings" params="[conferenceIndex: '2']">Eastern Conference</g:link></li>
                    </ul>
                </div>
                <g:render template="/sharedTemplates/standingsTable" />
            </div>
        </div>
    </body>
</html>