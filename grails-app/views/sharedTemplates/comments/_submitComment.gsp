<!-- Leave a comment -->
<div class="w3-container w3-padding w3-margin">
    <div class="comment-form">
        <h4 class="blog-section-title">Leave a comment</h4>

        <g:form name="new-comment-form" action="submitComment" id="${currentUser.id}-on-${post.id}-#${post.numComments}">
            <!-- Comment -->
            <div class="col-md-12 form-group no-gap">
                <textarea class="form-control" name="textarea" id="comment-form-text" rows="5" placeholder="Your Comment"></textarea>
            </div>

            <g:actionSubmit class="w3-btn w3-black w3-padding w3-margin remove-padding-b remove-margin-b w3-hover-red" align="right" name="submitComment" action="submitComment" value="Send Comment" />

        </g:form>


    </div><!-- / .comment-form -->
</div>