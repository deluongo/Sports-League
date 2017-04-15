<!-- Blog entries -->
<div class="w3-col l12 s12">

    <!-- Page Owner's Options -->

    <!-- <sec:loggedInUserInfo field="username" /> -->
    <g:if test="${person.user == currentUser}">

        <!-- Buttons -->
         <div class="w3-container w3-card-2 w3-white w3-round w3-margin w3-center"><br>
            <button type="button" class="w3-button w3-theme-d3 w3-margin-bottom" data-toggle="modal" data-target="#postModal"><i class="fa fa-pencil"></i>  New Post</button>
            <button type="button" class="w3-button w3-theme-d2 w3-margin-bottom" data-toggle="modal" data-target="#managementModal"><i class="fa fa-bars"></i>  Blog Management</button>
            <button type="button" class="w3-button w3-theme-d1 w3-margin-bottom" data-toggle="modal" data-target="#approvalModal"><i class="fa fa-comment"></i>  Comment Approval</button>
         </div>

        <hr>

        <!-- Blog Models Modals -->
        <div class="container">
            <!-- Modal -->
            <div class="modal fade" id="postModal" role="dialog">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header" style="padding:35px 50px;">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>

                            <h4 class="modal-title modal-h4"><i class="fa fa-pencil"></i>  New Post</h4>
                        </div>
                        <div class="modal-body remove-margin-b remove-padding-b">
                            <div class="modal-body remove-margin-b remove-padding-b">
                                <div class="container remove-margin-b remove-padding-b">

                                    <div id="validation-error-messages"></div>

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

                                            <g:actionSubmit class="w3-btn w3-black w3-padding w3-margin remove-padding-b remove-margin-b w3-hover-red" name="publishPost" action="publishPost" value="Save & Publish" />
                                            <g:actionSubmit class="w3-btn w3-white w3-padding w3-margin remove-padding-b remove-margin-b w3-hover-red" name="savePost" action="savePost" value="Save" />

                                            <button class="w3-btn w3-black w3-padding w3-margin remove-padding-b remove-margin-b w3-hover-red" name="publishPost" id="publishPost" type="submit">Save & Publish</button>
                                            <button class="w3-btn w3-white w3-padding w3-margin remove-padding-b remove-margin-b w3-hover-red" name="savehPost" id="savePost" >Save</button>

                                        </div>


                                    </g:form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <!-- Modal -->
            <div class="modal fade" id="managementModal" role="dialog">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Modal Header</h4>
                        </div>
                        <div class="modal-body">
                            <p>This is a large modal.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <!-- Modal -->
            <div class="modal fade" id="approvalModal" role="dialog">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Modal Header</h4>
                        </div>
                        <div class="modal-body">
                            <p>This is a large modal.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </g:if>


    <!-- Display Blog entries -->
    <div id="displayAllPosts">
        <div id="action-results-message"></div>
        <g:render template="/sharedTemplates/posts/displayAllPosts"/>
    </div>

    <!-- END BLOG ENTRIES -->
</div>

