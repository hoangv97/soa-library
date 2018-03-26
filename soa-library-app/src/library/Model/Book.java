package library.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty title;
    private final SimpleStringProperty author;
    private final SimpleStringProperty description;
    private final SimpleIntegerProperty availableNumber;

    public Book(Integer id, String title, String author, String description, Integer availableNumber) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.description = new SimpleStringProperty(description);
        this.availableNumber = new SimpleIntegerProperty(availableNumber);
    }

    public Book(String title, String author, String description, Integer availableNumber) {
        this.id = new SimpleIntegerProperty(0);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.description = new SimpleStringProperty(description);
        this.availableNumber = new SimpleIntegerProperty(availableNumber);
    }

    public int getId() {
        return id.get();
    }

    public String getTitle() {
        return title.get();
    }

    public String getAuthor() {
        return author.get();
    }

    public String getDescription() {
        return description.get();
    }

    public int getAvailableNumber() {
        return availableNumber.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setAvailableNumber(Integer availableNumber) {
        this.availableNumber.set(availableNumber);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id.get() +
                ", title=" + title.get() +
                ", author=" + author.get() +
                ", description=" + description.get() +
                ", availableNumber=" + availableNumber.get() +
                '}';
    }

    public BookAPI getBookAPI() {
        return new BookAPI(getId(), getTitle(), getAuthor(), getDescription(), getAvailableNumber());
    }
}
