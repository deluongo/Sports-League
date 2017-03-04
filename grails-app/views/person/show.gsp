

<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                    <img src="https://static01.nyt.com/images/2016/02/29/sports/basketball/STEPHCURRY/STEPHCURRY-articleLarge.jpg" style="width:100%" alt="Avatar">
                    <div class="w3-display-bottomleft w3-container w3-text-white">
                        <h2>"${person.firstName} ${person.lastName}"</h2>
                    </div>
                </div>
                <div class="w3-container">
                    <p><i class="fa fa-shirtsinbulk fa-fw w3-margin-right w3-large w3-text-teal"></i>${person.number}</p>
                    <p><i class="fa fa-male fa-fw w3-margin-right w3-large w3-text-teal"></i>${person.height}</p>
                    <p><i class="fa fa-balance-scale fa-fw w3-margin-right w3-large w3-text-teal"></i>${person.weight}</p>
                    <p><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i>${person.birthDate}</p>
                    <p><i class="fa fa-globe fa-fw w3-margin-right w3-large w3-text-teal"></i>${person.birthPlace}</p>
                    <p><i class="fa fa-graduation-cap fa-fw w3-margin-right w3-large w3-text-teal"></i>${person.universityAttended}</p>
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
                    <br>
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
                <div class="w3-panel w3-border w3-border-green">
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
                <div id="show-person" class="content scaffold-show" role="main">
                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <div>
                        <g:render template="/sharedTemplates/gameStatsRow" />
                    </div>
                    <br />
                </div>
            </div>
            <div class="w3-container w3-card-2 w3-white">
                <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-certificate fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Bio</h2>
                <div class="w3-container">
                    <p>${person.bio}</p>
                </div>
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

