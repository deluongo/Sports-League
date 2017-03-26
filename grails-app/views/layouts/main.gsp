<!doctype html>
<html lang="en" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>
            <g:layoutTitle default="Sports League"/>
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>

        <asset:stylesheet src="application.css"/>

        <g:layoutHead/>

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

        <g:layoutBody/>

        <asset:javascript src="application.js"/>

    </body>
</html>
