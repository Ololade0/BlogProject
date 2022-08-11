package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.Comment;
import africa.semicolon.blogProject.dtos.requests.AddCommentRequest;

public interface CommentServices {
    Comment addComment(AddCommentRequest addCommentRequest);
}
