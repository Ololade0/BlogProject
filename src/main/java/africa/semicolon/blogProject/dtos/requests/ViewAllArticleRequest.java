package africa.semicolon.blogProject.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ViewAllArticleRequest {
    private String body;
    private String title;
    private String userId;
    private String userEmail;

}
