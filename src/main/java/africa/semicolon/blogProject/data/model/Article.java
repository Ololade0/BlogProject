package africa.semicolon.blogProject.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@Data
@Document
@NoArgsConstructor
public class Article {

    @Id
    private String id;
    public String blogName;
    private String title;
    private String body;
    private List<Comment> comments = new ArrayList<>();

    }

