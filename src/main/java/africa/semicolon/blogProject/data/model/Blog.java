package africa.semicolon.blogProject.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("blog")
@NoArgsConstructor
@Data
public class Blog {
    @Id
    private String id;
    private String name;
    @DBRef
    private List<Article> articles = new ArrayList<>();


}
