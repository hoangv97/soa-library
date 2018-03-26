package library.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.IntegerStringConverter;
import library.AppConfig.Config;
import library.Helper.AlertHelper;
import library.Model.Book;
import library.Model.PostResponseAPI;
import library.Model.ResponseAPI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookList implements Initializable {

    private LibraryController libraryController = new LibraryController();

    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, Integer> id;
    @FXML
    private TableColumn<Book, String> title;
    @FXML
    private TableColumn<Book, String> author;
    @FXML
    private TableColumn<Book, String> description;
    @FXML
    private TableColumn<Book, Integer> availableNumber;
    @FXML
    private Button addBookBtn;
    @FXML
    private Button updateBookBtn;
    @FXML
    private Button deleteBookBtn;

    //  create a primary stage object
    private Stage primaryStage = new Stage();

    //  create an observable list to hold the Book object in the Book class
    private ObservableList<Book> list;

    //edited books list
    private ArrayList<Book> editedBooks = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //call the setTable method
        setTable();
    }

    public void setTable() {
        list = FXCollections.observableArrayList(libraryController.getBooks());

//      this makes the table editable
        table.setEditable(true);

        updateBookBtn.setVisible(false);
        deleteBookBtn.setVisible(false);

        //show delete button when a row is clicked
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Book selectedItem = table.getSelectionModel().getSelectedItem();
                if (selectedItem != null){
                    deleteBookBtn.setVisible(true);
                }
            }
        });

//      make title column editable with a textfield
        title.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setTitle method
        title.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                updateBookBtn.setVisible(true);

                Book editedBook = event.getTableView().getItems().get(event.getTablePosition().getRow());
                editedBook.setTitle(event.getNewValue());

                saveEditedBook(editedBook);
            }
        });

//      make author column editable with a textfield
        author.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setAuthor method
        author.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                updateBookBtn.setVisible(true);

                Book editedBook = event.getTableView().getItems().get(event.getTablePosition().getRow());
                editedBook.setAuthor(event.getNewValue());

                saveEditedBook(editedBook);
            }
        });

//      make description column editable with a textfield
        description.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setDescription method
        description.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                updateBookBtn.setVisible(true);

                Book editedBook = event.getTableView().getItems().get(event.getTablePosition().getRow());
                editedBook.setDescription(event.getNewValue());

                saveEditedBook(editedBook);
            }
        });

//      make author column editable with a textfield
        availableNumber.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

//      gets the new value and calls the setAvailableNumber method
        availableNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, Integer> event) {
                updateBookBtn.setVisible(true);

                Book editedBook = event.getTableView().getItems().get(event.getTablePosition().getRow());
                editedBook.setAvailableNumber(event.getNewValue());

                saveEditedBook(editedBook);
            }
        });

//      set the values of each columns to display on the table
        id.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        description.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
        availableNumber.setCellValueFactory(new PropertyValueFactory<Book, Integer>("availableNumber"));

        table.setItems(list);
    }

    public void addBook() throws Exception {
//      load the attendance list window
        Parent root = FXMLLoader.load(getClass().getResource("BookForm.fxml"));
        primaryStage.setTitle(Config.NEW_BOOK_WINDOW_TITLE);
        primaryStage.setScene(new Scene(root, Config.NEW_BOOK_WINDOW_WIDTH, Config.NEW_BOOK_WINDOW_HEIGHT));
        primaryStage.show();
    }

    /**
     * update all edited books
     */
    public void editBookList() {
        if(editedBooks.size() == 0)
            return;

        Window owner = updateBookBtn.getScene().getWindow();
        Integer success = 0;

        for(Book book : editedBooks) {
            PostResponseAPI response = libraryController.updateBook(book);

            if(response != null && response.getStatus() == 200) {
                success++;
            }
        }

        String message = "Success: " + success + ". Error: " + (editedBooks.size() - success);
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Updated Books!", message);

        setTable();
    }

    /**
     * Delete the selected book
     */
    public void deleteBook() {
        Window owner = deleteBookBtn.getScene().getWindow();

        Book selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            ResponseAPI response = libraryController.deleteBook(selectedItem);

            if(response != null && response.getStatus() == 200) {
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Success!", response.getMessage());

                setTable();
            } else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!", "An error occurred!");
            }
        }
    }

    /**
     * Save edited row to edited books list
     * @param book
     */
    private void saveEditedBook(Book book) {
        int index = 0;
        boolean isDuplicated = false;

        for(Book editedBook: editedBooks) {
            if(editedBook.getId() == book.getId()) {
                editedBooks.set(index, book);
                isDuplicated = true;
            }
            index++;
        }
        if(!isDuplicated)
            editedBooks.add(book);
    }
}
