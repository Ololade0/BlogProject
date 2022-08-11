package africa.semicolon.blogProject.dtos.requests;

import lombok.Data;

@Data
public class AddCommentRequest {
    public String userEmail;
    private String body;
    private String title;
    private String blogName;

}
