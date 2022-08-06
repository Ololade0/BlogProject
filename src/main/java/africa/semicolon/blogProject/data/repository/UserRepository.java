package africa.semicolon.blogProject.data.repository;

import africa.semicolon.blogProject.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByEmail(String email);

    User findUserByPassword(String password);
}
