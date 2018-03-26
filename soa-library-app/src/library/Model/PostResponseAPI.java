package library.Model;

public class PostResponseAPI {

    private String message;
    private Integer status;
    private BookAPI data;

    public PostResponseAPI(String message, Integer status, BookAPI data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public BookAPI getData() {
        return data;
    }
}
