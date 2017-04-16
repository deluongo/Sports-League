<div class="blog-post-comments">
    <!-- Comment 1 -->
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

                    <g:form class="approve-comment-form" name="approve-comment-form" action="approveComment" id="${comment.id}">

                        <input type="hidden" name="personIndex" value="${person.id}" />
                        <input type="hidden" name="commentIndex" value="${comment.id}" />

                        <g:actionSubmit class="dl-comment-reply-btn w3-btn w3-white remove-margin-b w3-hover-green w3-right-align" align="right" name="approveComment" action="approveComment" value="Approve Comment" />

                    </g:form>

                <!--class="dl-comment-reply-btn w3-button w3-hover-blue">-->
                </div>
            </div>
        </div><!-- / .bp-comment -->
    </div>
</div>

<hr>