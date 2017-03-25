<table id="standings" class="table table-striped table-inverse table-bordered table-hover dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example_info" style="width: 100%;">
    <thead>
    <tr>
        <th>#</th>
        <th>Team</th>
        <th>W</th>
        <th>L</th>
        <th>Win %</th>
        <th class='hidden-xs'>GB</th>
        <th class='hidden-xs'>Home</th>
        <th class='hidden-xs'>Road</th>
        <th>L10</th>
        <th class='hidden-xs'>Streak</th>
        <th class='hidden-xs'>Scored</th>
        <th class='hidden-xs'>Allowed</th>
        <th class='hidden-xs'>Diff.</th>
    </tr>
    </thead>
    <tbody>
    <g:each status="i" in="${teamList.sort{it.seed}}" var="team">
        <!-- Alternate CSS classes for the rows. -->
        <tr role="row" class="${ (i % 2) == 0 ? 'even' : 'odd'}">
            <td>${team.seed?.encodeAsHTML()}</td>
            <td>${team.location?.encodeAsHTML()} ${team.name?.encodeAsHTML()}</td>
            <td>${team.wins?.encodeAsHTML()}</td>
            <td>${team.losses?.encodeAsHTML()}</td>
            <td>${team.winPercent?.encodeAsHTML()}</td>
            <td class='hidden-xs'>${team.gamesBack?.encodeAsHTML()}</td>
            <td class='hidden-xs'>${team.homeRecord?.encodeAsHTML()}</td>
            <td class='hidden-xs'>${team.roadRecord?.encodeAsHTML()}</td>
            <td>${team.l10?.encodeAsHTML()}</td>
            <td class='hidden-xs'>${team.streak?.encodeAsHTML()}</td>
            <td class='hidden-xs'>${team.scored?.encodeAsHTML()}</td>
            <td class='hidden-xs'>${team.allowed?.encodeAsHTML()}</td>
            <td class='hidden-xs'>${team.delta?.encodeAsHTML()}</td>
        </tr>
    </g:each>
    </tbody>
</table>