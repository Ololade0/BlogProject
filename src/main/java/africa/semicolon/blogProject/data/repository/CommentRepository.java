package africa.semicolon.blogProject.data.repository;

import africa.semicolon.blogProject.data.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {


}
