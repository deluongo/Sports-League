<div class="blog-post-comments">
    <hr>
        <!-- Comment 1 -->
        <div class="bp-comment w3-padding">
            <div class="w3-container w3-cell">
                <div class="comment-avatar">
                        <img src="${comment.author.pictureURL}" class="dl-centered-and-cropped w3-circle dl-comment-avatar" alt="Avatar">
                </div>
            </div>
            <div class="w3-container w3-cell">
                <div class="comment-info">
                    <h6 class="dl-comment-name">${comment.author.username}</h6>
                    <span class="dl-comment-date">${println(comment.dateCreated)}</span>
                    <p class="dl-comment-text">${comment.text}</p>
                </div>
                <br/>
                <div align="right">
                <button class="dl-comment-reply-btn w3-btn w3-white remove-margin-b w3-hover-blue w3-right-align"><i class="fa fa-mail-reply-all"></i> Reply</button>
                <!--class="dl-comment-reply-btn w3-button w3-hover-blue">-->
                </div>
            </div>
        </div><!-- / .bp-comment -->
</div>
