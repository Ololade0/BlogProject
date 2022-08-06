package africa.semicolon.blogProject.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;
@Data
public class Blog {
    @Id
    private String name;
    private String id;
    private String description;
    private String content;
    @DBRef
    private List<Article> articles = new ArrayList<>();


}
