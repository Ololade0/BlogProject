package africa.semicolon.blogProject.exceptions;

public class BlogExistsException extends RuntimeException{
    public BlogExistsException(String message){
        super(message);
    }
}
