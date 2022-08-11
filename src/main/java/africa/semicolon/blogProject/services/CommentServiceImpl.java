package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.Comment;
import africa.semicolon.blogProject.data.repository.CommentRepository;
import africa.semicolon.blogProject.dtos.requests.AddCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentServices{
    @Autowired
    CommentRepository commentRepository;
    @Override
    public Comment addComment(AddCommentRequest addCommentRequest) {
        Comment comment = new Comment();
        comment.setBody(addCommentRequest.getBody());
        return commentRepository.save(comment);
    }
}
