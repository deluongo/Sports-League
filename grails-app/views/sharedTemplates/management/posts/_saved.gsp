<!-- <div class="w3-container" id = "displayComments"> -->




<!-- Saved Blog entries -->
<div id = "displaySavedPosts">
    <!-- Display Blog entries -->
    <g:render template="/sharedTemplates/management/posts/displayPost" collection="${person.user?.dateSortedPrivatePosts}" var="post"/>
</div>
