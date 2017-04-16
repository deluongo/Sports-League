<!-- Blog entries -->
<div class="w3-container" id = "displayPendingComments">
    <!-- Display Blog entries -->
    <hr/>
    <g:render template="/sharedTemplates/management/comments/displayComment" collection="${person.user?.dateSortedPendingComments}" var="comment"/>

</div>
