<div class="blog-saved-posts dl-max-height dl-height-122">
    <hr>
    <!-- Comment 1 -->
    <div class="bp-comment dl-max-height">
        <div class="w3-row dl-max-height">
            <div class="w3-container w3-col m2 ">
                <div class="comment-avatar">
                    <img class="dl-centered-and-cropped w3-circle dl-comment-avatar" src="${post.pictureURL}" alt= "Post Background Image">
                </div>
            </div>
            <div class="w3-container w3-col m6  dl-text-overflow dl-container">
                <div class="saved-post-info dl-overflow">
                    <span class="w3-opacity w3-medium dl-post-date">${post.localTimePublished}</span>

                    <h3 class="dl-no-margin"><b>${post.title}</b></h3>

                    <p class="w3-text-grey">${post.description}</p>

                    <p class="w3-padding-16">${post.text.take(250)}</p>
                </div>
                <br/>
            </div>
            <div class="w3-container w3-col m4 dl-text-overflow dl-container dl-max-height">
                <div class="dl-max-height w3-container w3-cell w3-cell-middle dl-publish-parent">

                    <g:form class="publish-post-form" name="publish-post-form" action="approveComment" id="post-management-form-${post.id}">

                        <input type="hidden" name="personIndex" value="${person.id}" />

                        <g:actionSubmit class="w3-btn w3-padding-large w3-white w3-hover-blue w3-right" align="right" name="publishPost" action="publishPost" value="Publish  " />

                    </g:form>


                    <p><button class=""><b>Publish  </b> <span class="w3-tag"><i class="fa fa-check"></i></span></button></p>
                    <!--
                    <button class="w3-btn w3-padding-large w3-white w3-hover-blue w3-right w3-cell-middle"><b>View  </b> <span class="w3-tag"><i class="fa fa-check"></i></span></button> -->
                </div>
            </div>
        </div><!-- / .bp-comment -->
    </div>
</div>
