<g:form class="w3-container remove-margin-b remove-padding-b" name="new-post-form" action="publishPost" id="${person.id}">
    <!--<form class="w3-container remove-margin-b remove-padding-b" id="new-post-form" method="post" action="/league/newPostSubmit">-->
    <div class="w3-row margin-10-b">
        <label for="postTitle">Title</label>
        <input type="text" class="w3-input" id="postTitle" name="postTitle" placeholder="Enter a blog title ..">
    </div>
    <div class="w3-cell-row margin-10-b">
        <div class="w3-container w3-cell remove-padding-l ">
            <label for="postDescription">Headline</label>
            <input type="text" class="w3-input" id="postDescription" name="postDescription" placeholder="Enter a headline ..">
        </div>
        <div class="w3-container w3-cell remove-padding-r ">
            <label for="backgroundImage">Image</label>
            <input type="URL" class="w3-input" id="backgroundImage" name="backgroundImage" placeholder="Enter a valid Image URL ..">
        </div>
    </div>
    <div class="w3-row margin-10-b">
        <label for="blogText">Post Content</label>
        <textarea type="text" class="w3-input" id="blogText" name="blogText" rows="8"></textarea>
    </div>
    <input type="hidden" id="personIndex" name="personIndex" value="${person.id}" />
    <input type="hidden" id="tabIndex" name="tabIndex" value="${person.id}" />
    <div class="form-group row w3-margin w3-padding remove-margin-b remove-padding-b w3-center">

        <g:actionSubmit class="w3-btn w3-black w3-padding w3-margin remove-padding-b remove-margin-b w3-hover-red" action="publishPost" value="Save & Publish" />
        <g:actionSubmit class="w3-btn w3-white w3-padding w3-margin remove-padding-b remove-margin-b w3-hover-red" action="publishPost" value="Save" />

        <!--<button class="w3-btn w3-black w3-padding w3-margin remove-padding-b remove-margin-b w3-hover-red" type="submit">Save & Publish</button>
                                            <button class="w3-btn w3-white w3-padding w3-margin remove-padding-b remove-margin-b w3-hover-red">Save</button>-->

    </div>


</g:form>