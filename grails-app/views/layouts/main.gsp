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
                <li class="<g:if test="${nav == "standings"}">active</g:if>"></li><a href="season/showStandings">Standings</a></li>
                    <li><a href="season/leaderboard">Stat Leaders</a></li>
                    <li><a href="person">Players</a></li>
                    <li><a href="league/person/">Stephen Curry</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <g:layoutBody/>

    <asset:javascript src="application.js"/>

</body>
</html>
