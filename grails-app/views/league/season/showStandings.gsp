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
            <h2 id='title-text'>${seasonName?.encodeAsHTML()}</h2>
            <div class="nav" role="navigation">
                <ul>
                    <li><g:link class="westernStandings" action="showStandings">Western Conference</g:link></li>
                    <li><g:link class="easternStandings" action="showStandings">Eastern Conference</g:link></li>
                </ul>
                <div id="example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap4">
                    <div class="row"><div class="col-xs-12 col-md-6">
                        <div class="dataTables_length" id="example_length">
                            <label>Show
                                <select name="example_length" aria-controls="example" class="form-control input-sm">
                                    <option value="10">10</option><option value="25">25</option>
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                </select> entries
                            </label>
                        </div>
                    </div>
                        <div class="col-xs-12 col-md-6">
                            <div id="example_filter" class="dataTables_filter">
                                <label>Search:
                                    <input type="search" class="form-control input-sm" placeholder="" aria-controls="example">
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <table id="example" class="table table-striped table-inverse table-bordered table-hover dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example_info" style="width: 100%;">
                                <thead>
                                    <tr role="row">
                                        <th class="sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1"
                                            aria-sort="ascending" aria-label="#: activate to sort column descending" style="width: 142px;">#</th>
                                        <th class="sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1"
                                                aria-sort="ascending" aria-label="Team: activate to sort column descending" style="width: 142px;">Team</th>
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
                                <g:each status="i" in="${teamList}" var="team">
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
                </div>
            </div>
        </div>
    </body>
</html>