

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
                <li class="<g:if test="${conferenceIndex == 1}">active</g:if>"><g:link action="showStandings" params="[conferenceIndex: '1']">Western Conference</g:link></li>
                <li class="<g:if test="${conferenceIndex == 1}">active</g:if>"><g:link action="showStandings" params="[conferenceIndex: '2']">Eastern Conference</g:link></li>
            </ul>
        </div>
        <div>
            <g:render template="/sharedTemplates/standingsTable" />
        </div>
    </div>
</div>
</body>
</html>