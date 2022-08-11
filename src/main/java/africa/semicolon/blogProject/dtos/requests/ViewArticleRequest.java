package africa.semicolon.blogProject.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ViewArticleRequest {
    private String body;
    private String title;
    private String userId;
    private String userEmail;

//    public String getEmail() {
//        return userEmail;
//    }
}
