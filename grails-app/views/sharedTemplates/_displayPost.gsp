<!-- Blog entry -->
<div class="w3-card-4 w3-margin w3-white">
    <img src="${post.pictureURL}" alt= "Post Background Image" style="width:100%">
    <div class="w3-container w3-padding-16">
        <h3><b>${post.title}</b></h3>
        <h5 class="w3-text-black">${post.description}, <span class="w3-opacity w3-medium">${post.datePublished.toString().substring(0, 10)}</span></h5>
    </div>

    <div class="w3-container">
        <p class="w3-padding-16">${post.text}</p>
        <div class="w3-row w3-padding-16">
            <div class="w3-col m8 s12">
                <p><button class="w3-button w3-padding-large w3-white w3-border"><b>READ MORE »</b></button></p>
            </div>
            <div class="w3-col m4 w3-hide-small">
                <p><span class="w3-padding-large w3-right"><b>Comments  </b> <span class="w3-tag">0</span></span></p>
            </div>
        </div>
    </div>
</div>

<hr>