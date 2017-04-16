<!-- Blog entries -->
<div class="w3-container" id = "displayComments">
    <!-- Display Blog entries -->
    <g:render template="/sharedTemplates/comments/displayComment" collection="${post?.dateSortedApprovedComments}" var="comment"/>

    <hr>
    <div class="new-message-error-messages"id="new-message-error-messages"></div>
    <div class="new-message-success-message" id="new-message-success-message"></div>
    <g:render template="/sharedTemplates/comments/submitComment"/>
</div>
