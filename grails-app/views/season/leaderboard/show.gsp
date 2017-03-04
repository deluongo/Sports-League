<!DOCTYPE html>
<html>
    <title>W3.CSS Template</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}
        .w3-sidenav a,.w3-sidenav h4 {font-weight:bold}
    </style>
    <body class="w3-light-grey w3-content" style="max-width:1600px">
        <!-- !PAGE CONTENT! -->
        <div class="w3-main">

            <!-- Header -->
            <header class="w3-container" id="portfolio">
                <a href="#"><img src="/w3images/avatar_g2.jpg" style="width:65px;" class="w3-circle w3-right w3-margin w3-hide-large w3-hover-opacity"></a>
                <span class="w3-opennav w3-hide-large w3-xxlarge w3-hover-text-grey" onclick="w3_open()"><i class="fa fa-bars"></i></span>
                <h1><b>Leaderboard</b></h1>
                <div class="w3-section w3-bottombar w3-padding-16">
                    <button class="w3-button w3-white"><g:link class="w3-button w3-white" action="showStandings" params="[conferenceIndex: '1']">Western Conference</g:link></button>
                    <button class="w3-button w3-white"><g:link class="w3-button w3-white" action="showStandings" params="[conferenceIndex: '2']">Eastern Conference</g:link></button>
                </div>
            </header>

            <!-- First Photo Grid-->
            <div class="w3-row-padding">
                <div class="w3-third w3-container w3-margin-bottom">
                    <img src="${scoringLeaders[0].pictureURL}" alt="${scoringLeaders[0].firstName} ${scoringLeaders[0].lastName}" style="width:100%" class="w3-hover-opacity">
                    <div class="w3-container w3-white">
                        <p><b>PPG Leaders</b></p>
                        <g:each status="i" in="${scoringLeaders}" var="pointsLeader">
                            <p>${i+1}. <span>${pointsLeader.firstName?.encodeAsHTML()} ${pointsLeader.lastName?.encodeAsHTML()}</span> <span>${pointsLeader.team.location?.encodeAsHTML()}</span> ${pointsLeader.pointsPerGame?.encodeAsHTML()}</p>
                        </g:each>
                    </div>
                </div>
                <div class="w3-third w3-container w3-margin-bottom">
                    <img src="${assistLeaders[0].pictureURL}" alt="${assistLeaders[0].firstName} ${assistLeaders[0].lastName}" style="width:100%" class="w3-hover-opacity">
                    <div class="w3-container w3-white">
                        <p><b>APG Leaders</b></p>
                        <g:each status="i" in="${assistLeaders}" var="assLeader">
                            <p>${i+1}. <span>${assLeader.firstName?.encodeAsHTML()} ${assLeader.lastName?.encodeAsHTML()}</span> <span>${assLeader.team.location?.encodeAsHTML()}</span> ${assLeader.assistsPerGame?.encodeAsHTML()}</p>
                        </g:each>
                    </div>
                </div>
                <div class="w3-third w3-container">
                    <img src="${reboundLeaders[0].pictureURL}" alt="${reboundLeaders[0].firstName} ${reboundLeaders[0].lastName}" style="width:100%" class="w3-hover-opacity">
                    <div class="w3-container w3-white">
                        <p><b>RPG Leaders</b></p>
                        <g:each status="i" in="${reboundLeaders}" var="reboundLeader">
                            <p>${i+1}. <span>${reboundLeader.firstName?.encodeAsHTML()} ${reboundLeader.lastName?.encodeAsHTML()}</span> <span>${reboundLeader.team.location?.encodeAsHTML()}</span> ${reboundLeader.reboundsPerGame?.encodeAsHTML()}</p>
                        </g:each>
                    </div>
                </div>
            </div>

            <!-- Second Photo Grid-->
            <div class="w3-row-padding">
                <div class="w3-third w3-container">
                    <img src="${stealLeaders[0].pictureURL}" alt="${stealLeaders[0].firstName} ${stealLeaders[0].lastName}" style="width:100%" class="w3-hover-opacity">
                    <div class="w3-container w3-white">
                        <p><b>FG% Leaders</b></p>
                        <g:each status="i" in="${stealLeaders}" var="stealLeader">
                            <p>${i+1}. <span>${stealLeader.firstName?.encodeAsHTML()} ${stealLeader.lastName?.encodeAsHTML()}</span> <span>${stealLeader.team.location?.encodeAsHTML()}</span> ${stealLeader.stealsPerGame?.encodeAsHTML()}</p>
                        </g:each>
                    </div>
                </div>
                <div class="w3-third w3-container w3-margin-bottom">
                    <img src="${shootingPercentLeaders[0].pictureURL}" alt="${shootingPercentLeaders[0].firstName} ${shootingPercentLeaders[0].lastName}" style="width:100%" class="w3-hover-opacity">
                    <div class="w3-container w3-white">
                        <p><b>FG% Leaders</b></p>
                        <g:each status="i" in="${shootingPercentLeaders}" var="shootingLeader">
                            <p>${i+1}. <span>${shootingLeader.firstName?.encodeAsHTML()} ${shootingLeader.lastName?.encodeAsHTML()}</span> <span>${shootingLeader.team.location?.encodeAsHTML()}</span> ${shootingLeader.shootingPercentage?.encodeAsHTML()}</p>
                        </g:each>
                    </div>
                </div>
                <div class="w3-third w3-container w3-margin-bottom">
                    <img src="${threePercentLeaders[0].pictureURL}" alt="${threePercentLeaders[0].firstName} ${threePercentLeaders[0].lastName}" style="width:100%" class="w3-hover-opacity">
                    <div class="w3-container w3-white">
                        <p><b>3PT% Leaders</b></p>
                        <g:each status="i" in="${threePercentLeaders}" var="threeLeader">
                            <p>${i+1}. <span>${threeLeader.firstName?.encodeAsHTML()} ${threeLeader.lastName?.encodeAsHTML()}</span> <span>${threeLeader.team.location?.encodeAsHTML()}</span> ${threeLeader.threePointPercentage?.encodeAsHTML()}</p>
                        </g:each>
                    </div>
                </div>
            </div>

            <!-- Pagination -->
            <div class="w3-center w3-padding-32">
                <div class="w3-bar">
                    <a href="#" class="w3-bar-item w3-button w3-hover-black">«</a>
                    <a href="#" class="w3-bar-item w3-black w3-button">1</a>
                    <a href="#" class="w3-bar-item w3-button w3-hover-black">2</a>
                    <a href="#" class="w3-bar-item w3-button w3-hover-black">3</a>
                    <a href="#" class="w3-bar-item w3-button w3-hover-black">4</a>
                    <a href="#" class="w3-bar-item w3-button w3-hover-black">»</a>
                </div>
            </div>

            <!-- Images of Me -->
            <div class="w3-row-padding w3-padding-16" id="about">
                <div class="w3-col m6">
                    <img src="/w3images/avatar_g.jpg" alt="Me" style="width:100%">
                </div>
                <div class="w3-col m6">
                    <img src="/w3images/me2.jpg" alt="Me" style="width:100%">
                </div>
            </div>

            <div class="w3-container w3-padding-large" style="margin-bottom:32px">
                <h4><b>About Me</b></h4>
                <p>Just me, myself and I, exploring the universe of unknownment. I have a heart of love and an interest of lorem ipsum and mauris neque quam blog. I want to share my world with you. Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla. Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla.</p>
                <hr>

                <h4>Technical Skills</h4>
                <!-- Progress bars / Skills -->
                <p>Photography</p>
                <div class="w3-grey">
                    <div class="w3-container w3-dark-grey w3-padding w3-center" style="width:95%">95%</div>
                </div>
                <p>Web Design</p>
                <div class="w3-grey">
                    <div class="w3-container w3-dark-grey w3-padding w3-center" style="width:85%">85%</div>
                </div>
                <p>Photoshop</p>
                <div class="w3-grey">
                    <div class="w3-container w3-dark-grey w3-padding w3-center" style="width:80%">80%</div>
                </div>
                <p>
                    <button class="w3-button w3-dark-grey w3-padding-large w3-margin-top w3-margin-bottom">
                        <i class="fa fa-download w3-margin-right"></i>Download Resume
                    </button>
                </p>
                <hr>

                <h4>How much I charge</h4>
                <!-- Pricing Tables -->
                <div class="w3-row-padding" style="margin:0 -16px">
                    <div class="w3-third w3-margin-bottom">
                        <ul class="w3-ul w3-border w3-white w3-center w3-opacity w3-hover-opacity-off">
                            <li class="w3-black w3-xlarge w3-padding-32">Basic</li>
                            <li class="w3-padding-16">Web Design</li>
                            <li class="w3-padding-16">Photography</li>
                            <li class="w3-padding-16">1GB Storage</li>
                            <li class="w3-padding-16">Mail Support</li>
                            <li class="w3-padding-16">
                                <h2>$ 10</h2>
                                <span class="w3-opacity">per month</span>
                            </li>
                            <li class="w3-light-grey w3-padding-24">
                                <button class="w3-button w3-teal w3-padding-large w3-hover-black">Sign Up</button>
                            </li>
                        </ul>
                    </div>

                    <div class="w3-third w3-margin-bottom">
                        <ul class="w3-ul w3-border w3-white w3-center w3-opacity w3-hover-opacity-off">
                            <li class="w3-teal w3-xlarge w3-padding-32">Pro</li>
                            <li class="w3-padding-16">Web Design</li>
                            <li class="w3-padding-16">Photography</li>
                            <li class="w3-padding-16">50GB Storage</li>
                            <li class="w3-padding-16">Endless Support</li>
                            <li class="w3-padding-16">
                                <h2>$ 25</h2>
                                <span class="w3-opacity">per month</span>
                            </li>
                            <li class="w3-light-grey w3-padding-24">
                                <button class="w3-button w3-teal w3-padding-large w3-hover-black">Sign Up</button>
                            </li>
                        </ul>
                    </div>

                    <div class="w3-third">
                        <ul class="w3-ul w3-border w3-white w3-center w3-opacity w3-hover-opacity-off">
                            <li class="w3-black w3-xlarge w3-padding-32">Premium</li>
                            <li class="w3-padding-16">Web Design</li>
                            <li class="w3-padding-16">Photography</li>
                            <li class="w3-padding-16">Unlimited Storage</li>
                            <li class="w3-padding-16">Endless Support</li>
                            <li class="w3-padding-16">
                                <h2>$ 25</h2>
                                <span class="w3-opacity">per month</span>
                            </li>
                            <li class="w3-light-grey w3-padding-24">
                                <button class="w3-button w3-teal w3-padding-large w3-hover-black">Sign Up</button>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <!-- Contact Section -->
            <div class="w3-container w3-padding-large w3-grey">
                <h4 id="contact"><b>Contact Me</b></h4>
                <div class="w3-row-padding w3-center w3-padding-24" style="margin:0 -16px">
                    <div class="w3-third w3-dark-grey">
                        <p><i class="fa fa-envelope w3-xxlarge w3-text-light-grey"></i></p>
                        <p>email@email.com</p>
                    </div>
                    <div class="w3-third w3-teal">
                        <p><i class="fa fa-map-marker w3-xxlarge w3-text-light-grey"></i></p>
                        <p>Chicago, US</p>
                    </div>
                    <div class="w3-third w3-dark-grey">
                        <p><i class="fa fa-phone w3-xxlarge w3-text-light-grey"></i></p>
                        <p>512312311</p>
                    </div>
                </div>
                <hr class="w3-opacity">
                <form action="/action_page.php" target="_blank">
                    <div class="w3-group">
                        <label>Name</label>
                        <input class="w3-input w3-border" type="text" name="Name" required>
                    </div>
                    <div class="w3-group">
                        <label>Email</label>
                        <input class="w3-input w3-border" type="text" name="Email" required>
                    </div>
                    <div class="w3-group">
                        <label>Message</label>
                        <input class="w3-input w3-border" type="text" name="Message" required>
                    </div>
                    <button type="submit" class="w3-button w3-black w3-padding-large w3-margin-bottom"><i class="fa fa-paper-plane w3-margin-right"></i>Send Message</button>
                </form>
            </div>

            <!-- Footer -->
            <footer class="w3-container w3-padding-32 w3-dark-grey">
                <div class="w3-row-padding">
                    <div class="w3-third">
                        <h3>FOOTER</h3>
                        <p>Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla.</p>
                        <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
                    </div>

                    <div class="w3-third">
                        <h3>BLOG POSTS</h3>
                        <ul class="w3-ul w3-hoverable">
                            <li class="w3-padding-16">
                                <img src="/w3images/workshop.jpg" class="w3-left w3-margin-right" style="width:50px">
                                <span class="w3-large">Lorem</span><br>
                                <span>Sed mattis nunc</span>
                            </li>
                            <li class="w3-padding-16">
                                <img src="/w3images/gondol.jpg" class="w3-left w3-margin-right" style="width:50px">
                                <span class="w3-large">Ipsum</span><br>
                                <span>Praes tinci sed</span>
                            </li>
                        </ul>
                    </div>

                    <div class="w3-third">
                        <h3>POPULAR TAGS</h3>
                        <p>
                            <span class="w3-tag w3-black w3-margin-bottom">Travel</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">New York</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">London</span>
                            <span class="w3-tag w3-grey w3-small w3-margin-bottom">IKEA</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">NORWAY</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">DIY</span>
                            <span class="w3-tag w3-grey w3-small w3-margin-bottom">Ideas</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">Baby</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">Family</span>
                            <span class="w3-tag w3-grey w3-small w3-margin-bottom">News</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">Clothing</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">Shopping</span>
                            <span class="w3-tag w3-grey w3-small w3-margin-bottom">Sports</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">Games</span>
                        </p>
                    </div>

                </div>
            </footer>

            <div class="w3-black w3-center w3-padding-24">Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></div>

            <!-- End page content -->
        </div>

        <script>
            // Script to open and close sidenav
            function w3_open() {
                document.getElementById("mySidenav").style.display = "block";
                document.getElementById("myOverlay").style.display = "block";
            }

            function w3_close() {
                document.getElementById("mySidenav").style.display = "none";
                document.getElementById("myOverlay").style.display = "none";
            }
        </script>
    </body>
</html>




