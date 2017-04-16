<div class="blog-post-comments">
    <hr>
<!-- Comment 1 -->
    <g:if test="${comment.approved == false}">
        <g:if test="${comment.author == currentUser}">
            <div class="w3-panel w3-orange">
                <p class="dl-warning-panel w3-center-align">Your comment is pending the moderators approval and is only visible to you. </p>
            </div>

        </g:if>
    </g:if>
    <div class="bp-comment">
        <div class="w3-row">
            <div class="w3-container w3-col m12 l2">
                <div class="comment-avatar">
                    <img src="${comment.author.pictureURL}" class="dl-centered-and-cropped w3-circle dl-comment-avatar" alt="Avatar">
                </div>
            </div>
            <div class="w3-container w3-col m12 l10 dl-text-overflow dl-container">
                <div class="saved-post-info dl-overflow">
                    <h6 class="dl-post-name">${comment.author.username}</h6>
                    <span class="dl-comment-date">${comment.localTime}</span>
                    <div class="dl-text-overflow">
                        <p class="dl-comment-text dl-text-overflow">${comment.text}</p>
                    </div>
                </div>
                <br/>
                <div align="right">
                    <button class="dl-comment-reply-btn w3-btn w3-white remove-margin-b w3-hover-blue w3-right-align"><i class="fa fa-mail-reply-all"></i> Reply</button>
                    <!--class="dl-comment-reply-btn w3-button w3-hover-blue">-->
                </div>
            </div>
        </div><!-- / .bp-comment -->
    </div>



</div>