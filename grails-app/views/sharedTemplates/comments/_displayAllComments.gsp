<!-- Blog entries -->
<div id = "displayComments">
    <!-- Display Blog entries -->
    <g:render template="/sharedTemplates/comments/displayComment" collection="${post.comments.dateSortedApprovedComments}" var="post"/>
</div>
