package africa.semicolon.blogProject.dtos.requests;

import lombok.Data;

@Data
public class DeleteRequest {
    private String name;
    private String body;
    private String title;
    private String email;

}
