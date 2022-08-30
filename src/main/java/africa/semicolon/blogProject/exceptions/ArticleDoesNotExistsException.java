package africa.semicolon.blogProject.exceptions;

import lombok.Data;

@Data
public class ArticleDoesNotExistsException extends RuntimeException {
    public ArticleDoesNotExistsException(String message) {
    }
}
