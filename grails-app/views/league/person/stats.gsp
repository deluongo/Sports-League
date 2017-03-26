

<!DOCTYPE html>
<html>
<title>${person.firstName} ${person.lastName} Stats</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
</style>
<body class="w3-light-grey">

    <!-- Page Container -->
    <div class="w3-content w3-margin-top" style="max-width:1400px;">

        <!-- The Grid -->
        <div class="w3-row-padding">

            <!-- Left Column -->
            <div class="w3-third">

                <div class="w3-white w3-text-grey w3-card-4">
                    <div class="w3-display-container">
                        <img src="${person.pictureURL}" style="width:100%" alt="Avatar">
                        <div class="w3-display-bottomleft w3-container w3-text-white">
                            <h2>${person.firstName} ${person.lastName}</h2>
                        </div>
                    </div>
                    <div class="w3-container w3-margin-top">
                        <div class="w3-row">
                            <div class="w3-col m5 l5 w3-margin-top">
                                <p><i class="fa fa-shirtsinbulk fa-fw w3-margin-right w3-large w3-text-teal"></i>Number: ${person.number}</p>
                                <p><i class="fa fa-male fa-fw w3-margin-right w3-large w3-text-teal"></i>Height: ${person.height}</p>
                                <p><i class="fa fa-balance-scale fa-fw w3-margin-right w3-large w3-text-teal"></i>Weight: ${person.weight}</p>
                            </div>
                            <div class="w3-col m7 l7 w3-margin-top">
                                <p><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i>Birth Date: ${person.birthDate.toString().substring(0, 10)}</p>
                                <p><i class="fa fa-globe fa-fw w3-margin-right w3-large w3-text-teal"></i>Birth Place: ${person.birthPlace}</p>
                                <p><i class="fa fa-graduation-cap fa-fw w3-margin-right w3-large w3-text-teal"></i>College: ${person.universityAttended}</p>
                            </div>
                        </div>
                        <hr>
                        <p class="w3-large"><b><i class="fa fa-asterisk fa-fw w3-margin-right w3-text-teal"></i>Skills</b></p>
                        <p>Long Range</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:90%">90%</div>
                        </div>
                        <p>Mid Range</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:80%">
                                <div class="w3-center w3-text-white">80%</div>
                            </div>
                        </div>
                        <p>Layups & Dunks</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:75%">75%</div>
                        </div>
                        <p>Passing</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:50%">50%</div>
                        </div>
                        <p>Ball Handeling</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:80%">80%</div>
                        </div>
                        <br>

                        <p class="w3-large w3-text-theme"><b><i class="fa fa-globe fa-fw w3-margin-right w3-text-teal"></i>Intangibles</b></p>
                        <p>Leadership</p>
                        <div class="w3-light-grey w3-round-xlarge">
                            <div class="w3-round-xlarge w3-teal" style="height:24px;width:55%"></div>
                        </div>
                        <p>Reputation</p>
                        <div class="w3-light-grey w3-round-xlarge">
                            <div class="w3-round-xlarge w3-teal" style="height:24px;width:25%"></div>
                        </div>
                        <br>
                    </div>
                </div><br>

                <!-- End Left Column -->
            </div>

            <!-- Right Column -->
            <div class="w3-twothird">

                <div class="w3-container w3-card-2 w3-white w3-margin-bottom">
                    <div class="w3-panel w3-border w3-border-teal">
                        <div class="w3-row">
                            <div class="w3-col m6 l9">
                                 <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-user fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Player Stats</h2>
                            </div>
                            <div class="w3-col m6 l3">
                                <div class="w3-margin-top w3-margin-bottom">
                                    <g:form resource="${this.person}" method="DELETE">
                                        <g:link class="edit w3-btn w3-ripple w3-green w3-margin-top w3-margin-bottom" action="edit" resource="${this.person}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                                        <input class="delete w3-btn w3-ripple w3-red w3-margin-top w3-margin-bottom" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                    </g:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="nav w3-card-2" role="navigation">
                    <ul class="nav nav-tabs">
                        <li class="<g:if test="${tabIndex == "personal"}">active</g:if>"><g:link action="person" params="[personIndex: person.id, tabIndex: 'personal']">Personal</g:link></li>
                        <li class="<g:if test="${tabIndex == "season"}">active</g:if>"><g:link action="person" params="[personIndex: person.id, tabIndex: 'season']">Season Stats</g:link></li>
                        <li class="<g:if test="${tabIndex == "games"}">active</g:if>"><g:link action="person" params="[personIndex: person.id, tabIndex: 'games']">Games</g:link></li>
                    </ul>
                </div>

                <div id="show-person" class="content scaffold-show" role="main">
                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <div>

                        <g:if test="${tabIndex == "personal"}">
                            <div class="w3-container w3-card-2 w3-white w3-margin-bottom">
                                <h2 class="w3-text-grey w3-padding-8"><i class="fa fa-id-card-o fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Bio</h2>
                                <div class="w3-container">
                                    <p>${person.bio}</p>
                                </div>
                                <div class="w3-row">
                                    <div class="w3-col m4 l4 w3-margin-top w3-margin-left">
                                        <p><i class="fa fa-shirtsinbulk fa-fw w3-margin-right w3-large w3-text-teal"></i>Number: ${person.number}</p>
                                        <p><i class="fa fa-male fa-fw w3-margin-right w3-large w3-text-teal"></i>Height: ${person.height}</p>
                                        <p><i class="fa fa-balance-scale fa-fw w3-margin-right w3-large w3-text-teal"></i>Weight: ${person.weight}</p>
                                    </div>
                                    <div class="w3-col m6 l6 w3-margin-top w3-margin-left">
                                        <p><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i>Birth Date: ${person.birthDate.toString().substring(0, 10)}</p>
                                        <p><i class="fa fa-globe fa-fw w3-margin-right w3-large w3-text-teal"></i>Birth Place: ${person.birthPlace}</p>
                                        <p><i class="fa fa-graduation-cap fa-fw w3-margin-right w3-large w3-text-teal"></i>College: ${person.universityAttended}</p>
                                    </div>
                                </div>
                                <br />
                            </div>
                        </g:if>

                        <g:if test="${tabIndex == "season"}">
                            <div class="w3-container w3-card-2 w3-white w3-margin-bottom">
                                <div id="show-season-stats" class="content scaffold-show" role="main">
                                    <g:if test="${flash.message}">
                                        <div class="message" role="status">${flash.message}</div>
                                    </g:if>
                                    <div>
                                        <g:render template="/sharedTemplates/seasonStatsRow" />
                                    </div>
                                    <br />
                                </div>
                            </div>
                        </g:if>

                        <g:if test="${tabIndex == "games"}">
                            <div class="w3-container w3-card-2 w3-white w3-margin-bottom">
                                <div id="show-per-game-stats" class="content scaffold-show" role="main">
                                    <g:if test="${flash.message}">
                                        <div class="message" role="status">${flash.message}</div>
                                    </g:if>
                                    <div>
                                        <g:render template="/sharedTemplates/gameStatsRow" />
                                    </div>
                                    <br />
                                </div>
                            </div>
                        </g:if>

                    </div>
                    <br />
                </div>

                <!-- End Right Column -->
            </div>

            <!-- End Grid -->
        </div>

    <!-- End Page Container -->
    </div>

    <footer class="w3-container w3-teal w3-center w3-margin-top">
        <p>Find me on social media.</p>
        <i class="fa fa-facebook-official w3-hover-text-indigo w3-large"></i>
        <i class="fa fa-instagram w3-hover-text-purple w3-large"></i>
        <i class="fa fa-snapchat w3-hover-text-yellow w3-large"></i>
        <i class="fa fa-pinterest-p w3-hover-text-red w3-large"></i>
        <i class="fa fa-twitter w3-hover-text-light-blue w3-large"></i>
        <i class="fa fa-linkedin w3-hover-text-indigo w3-large"></i>
        <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">Devon Luongo</a></p>
    </footer>

    </body>
</html>

