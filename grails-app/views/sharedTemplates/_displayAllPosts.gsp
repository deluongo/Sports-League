<!-- Blog entries -->
<div id = "displayPosts">
    <!-- Display Blog entries -->
    <g:render template="/sharedTemplates/displayPost" collection="${person.user.blogPosts}" var="post"/>
</div>
