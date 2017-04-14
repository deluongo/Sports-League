<!-- Blog entries -->
<div class="w3-col l12 s12">

    <!-- Page Owner's Options -->
     <div class="w3-container w3-card-2 w3-white w3-round w3-margin w3-center"><br>
        <button type="button" class="w3-button w3-theme-d3 w3-margin-bottom" data-toggle="modal" data-target="#postModal"><i class="fa fa-pencil"></i>  New Post</button>
        <button type="button" class="w3-button w3-theme-d2 w3-margin-bottom" data-toggle="modal" data-target="#managementModal"><i class="fa fa-bars"></i>  Blog Management</button>
        <button type="button" class="w3-button w3-theme-d1 w3-margin-bottom" data-toggle="modal" data-target="#approvalModal"><i class="fa fa-comment"></i>  Comment Approval</button>
     </div>

    <hr>

    <!-- Owner Options BS Modals -->
    <div class="container">
        <!-- Modal -->
        <div class="modal fade" id="postModal" role="dialog">
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


    <!-- Display Blog entries -->
    <g:render template="/sharedTemplates/displayPost" collection="${person.user.blogPosts}" var="post"/>


    <!-- END BLOG ENTRIES -->
</div>

