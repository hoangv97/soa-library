package library.Model;

import java.util.ArrayList;

public class ResponseAPI {

    private String message;
    private Integer status;
    private ArrayList<BookAPI> data;

    public ResponseAPI(String message, Integer status, ArrayList<BookAPI> data) {
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

    public ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();

        for(BookAPI book: data) {
            books.add(new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getAvailableNumber()));
        }

        return books;
    }
}
