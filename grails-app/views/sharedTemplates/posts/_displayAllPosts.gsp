<!-- Blog entries -->
<div id = "displayPosts">
    <!-- Display Blog entries -->
    <g:render template="/sharedTemplates/posts/displayPost" collection="${person.user.dateSortedPublicPosts}" var="post"/>
</div>
