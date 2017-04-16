<!-- Blog entries -->
<div class="w3-col l12 s12">

    <!-- Page Owner's Options -->

    <!-- <sec:loggedInUserInfo field="username" /> -->
    <g:if test="${person.user == currentUser}">

        <!-- Buttons -->
         <div class="w3-container w3-card-2 w3-white w3-round w3-margin w3-center"><br>
            <button type="button" class="w3-btn w3-theme-d3 w3-margin-bottom" data-toggle="modal" data-target="#postModal"><i class="fa fa-pencil"></i>  New Post</button>
            <button type="button" class="w3-btn w3-theme-d2 w3-margin-bottom" data-toggle="modal" data-target="#managementModal"><i class="fa fa-bars"></i>  Blog Management</button>
            <button type="button" class="w3-btn w3-theme-d1 w3-margin-bottom" data-toggle="modal" data-target="#approvalModal"><i class="fa fa-comment"></i>  Comment Approval</button>
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
                                    <!-- Display Post Submit Form -->
                                    <g:render template="/sharedTemplates/posts/submitPost"/>
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
                            <!-- Display Post Management -->
                            <g:render template="/sharedTemplates/management/posts/saved"/>
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
                            <!-- Display Post Management -->
                            <g:render template="/sharedTemplates/management/comments/pending"/>
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

