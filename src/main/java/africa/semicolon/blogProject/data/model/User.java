package africa.semicolon.blogProject.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
@Data
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private String UserName;
    @DBRef
    private Blog blog;


    }

