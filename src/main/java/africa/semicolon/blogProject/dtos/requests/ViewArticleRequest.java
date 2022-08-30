package africa.semicolon.blogProject.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ViewArticleRequest {
    public String blogName;
    private String body;
    private String article;
    private String title;
    private String userId;
    private String userEmail;


    }

