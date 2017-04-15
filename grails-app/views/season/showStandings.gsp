<!DOCTYPE html>
<html>
    <head>
        <title>NBA Standings</title>
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
                        <li id="standingsNav" class="active"><g:link controller="season" action="showStandings">Standings</g:link></li>
                        <li id="leaderboard"><g:link controller="season" action="leaderboard">Stat Leaders</g:link></li>
                        <li id="players"><g:link controller="person" action="index">Players</g:link></li>
                        <li id="player"><g:link controller="league" action="person">Stephen Curry</g:link></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- !PAGE CONTENT! -->
        <div class="w3-main">
            <div class="w3-row">
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