package library;

import java.util.ArrayList;
import java.util.Random;

public class LibraryController {

    public ArrayList getBooks() {
        String _title;
        String _author;
        String _description;
        Integer _availableNumber;

        Integer pos;

        ArrayList<Book> books = new ArrayList<>();

        for(int i = 0; i < 100; i++) {
            pos = i + 1;

            Random rand = new Random();
            _availableNumber = rand.nextInt( 5);

            _title = "Title " + pos;
            _author = "Author " + pos;
            _description = "Description " + pos;

            books.add(new Book(pos, _title, _author, _description, _availableNumber));
        }

        return books;
    }

    public void createNewBook(String title, String author, String description, Integer availableNumber) {
        System.out.println(title);
        System.out.println(author);
        System.out.println(description);
        System.out.println(availableNumber);
    }

    public void updateBook(Book book) {
        System.out.println(book.toString());
    }

    public void deleteBook(Book book) {
        System.out.println(book.toString());
    }

}
