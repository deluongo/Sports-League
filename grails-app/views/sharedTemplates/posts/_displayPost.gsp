
<!-- Blog entry -->
<div class="w3-card-4 w3-margin w3-white">
    <img src="${post.pictureURL}" alt= "Post Background Image" style="width:100%">

    <div class="w3-container w3-padding-16">
        <h3><b>${post.title}</b></h3>
        <h5 class="w3-text-black">${post.description}, <span class="w3-opacity w3-medium">${post.localTimePublished}</span></h5>
    </div>

    <div class="w3-container">
        <p class="w3-padding-16">${post.text}</p>
        <div class="w3-row w3-padding-16">
            <div class="w3-col l8 m4 s6">
                <p ><button class="w3-btn w3-padding-large w3-white w3-border"><b class="w3-hide-medium w3-hide-small">READ MORE </b> <b>»</b></button></p>
            </div>

            <div class="w3-col l4 m8 s6" align="right" onclick="commentAccordian('post-${post.id}-comments')">
                <p ><button class="w3-btn w3-padding-large w3-white w3-hover-blue w3-right"><b>Comments  </b> <span class="w3-tag">${post.numComments}</span></button></p>
            </div>

        </div>
    </div>

    <!-- Display Blog entries -->
    <div class="w3-hide" id="post-${post.id}-comments">
        <g:render template="/sharedTemplates/comments/displayAllComments"/>
    </div>


</div>

<hr>