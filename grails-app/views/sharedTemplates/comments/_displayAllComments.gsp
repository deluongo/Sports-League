<!-- Blog entries -->
<div class="w3-container" id = "displayComments-post${post.id}">


    <!-- Display Blog entries -->
    <g:render template="/sharedTemplates/comments/displayComment" collection="${post?.dateSortedComments}" var="comment"/>

    <hr>
    <div class="new-message-error-messages"id="new-message-error-messages"></div>
    <div class="new-message-success-message" id="new-message-success-message"></div>
    <g:render template="/sharedTemplates/comments/submitComment"/>
</div>
