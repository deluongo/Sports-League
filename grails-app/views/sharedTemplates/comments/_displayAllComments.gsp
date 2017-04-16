<!-- Blog entries -->
<div class="w3-container" id = "displayComments">

    <g:form class="new-comment-form" name="new-comment-form" action="submitComment" id="new-comment-form">
        <!-- Comment -->
        <div class="col-md-12 form-group no-gap">
            <textarea class="form-control" name="textarea" id="comment-text" rows="5" placeholder="Your Comment"></textarea>
        </div>

        <input type="hidden" name="personIndex" value="${person.id}" />
        <input type="hidden" name="postIndex" value="${post.id}" />


        <!-- Display Blog entries -->
        <g:render template="/sharedTemplates/comments/displayComment" collection="${post?.dateSortedComments}" var="comment"/>

        <hr>
        <div class="new-message-error-messages"id="new-message-error-messages"></div>
        <div class="new-message-success-message" id="new-message-success-message"></div>
        <g:render template="/sharedTemplates/comments/submitComment"/>
    </g:form>
</div>
