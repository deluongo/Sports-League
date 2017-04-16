<!-- Blog entries -->
<div class="w3-container" id = "displayComments">
    <!-- Display Blog entries -->
    <g:render template="/sharedTemplates/comments/displayComment" collection="${post?.dateSortedApprovedComments}" var="comment"/>

    <hr>

    <g:render template="/sharedTemplates/comments/submitComment"/>
</div>
