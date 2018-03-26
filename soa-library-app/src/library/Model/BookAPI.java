package library.Model;

public class BookAPI {

    private Integer id;
    private String title;
    private String author;
    private String description;
    private Integer availableNumber;

    public BookAPI(Integer id, String title, String author, String description, Integer availableNumber) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.availableNumber = availableNumber;
    }

    public BookAPI(String title, String author, String description, Integer availableNumber) {
        this.id = null;
        this.title = title;
        this.author = author;
        this.description = description;
        this.availableNumber = availableNumber;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAvailableNumber() {
        return availableNumber;
    }

    @Override
    public String toString() {
        return "BookAPI{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", availableNumber=" + availableNumber +
                '}';
    }
}
