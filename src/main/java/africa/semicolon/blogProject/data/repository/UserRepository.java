package africa.semicolon.blogProject.data.repository;

import africa.semicolon.blogProject.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByEmail(String email);

    User getUserByEmail(String userEmail);

}
