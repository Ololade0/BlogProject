package africa.semicolon.blogProject.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Data
public class CreateBlogRequest {
    private String name;
    private String userEmail;
}
