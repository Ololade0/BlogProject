package africa.semicolon.blogProject.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
public class AddArticleRequest {
    private String userId;
    private String body;
    private String title;
}
