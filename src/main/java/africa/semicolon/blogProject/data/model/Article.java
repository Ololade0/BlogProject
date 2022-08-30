package africa.semicolon.blogProject.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@NoArgsConstructor
public class Article {

    public String blogName;
    @Id
    private String id;
    private String title;
    private String body;
    @DBRef
    private List<Comment> comments = new ArrayList<>();

}

