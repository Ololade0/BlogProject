package africa.semicolon.blogProject.exceptions;

import lombok.Data;

@Data
public class ArticleExistsException extends RuntimeException {
    public ArticleExistsException(String message) {
    }
}
