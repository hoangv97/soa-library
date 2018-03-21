package library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //call the setTable method
        setTable();
    }

    private void setTable() {
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

                ((Book)event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setTitle(event.getNewValue());

            }
        });

//      make author column editable with a textfield
        author.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setAuthor method
        author.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                updateBookBtn.setVisible(true);

                ((Book)event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setAuthor(event.getNewValue());
            }
        });

//      make description column editable with a textfield
        description.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setDescription method
        description.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                updateBookBtn.setVisible(true);

                ((Book)event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setDescription(event.getNewValue());
            }
        });

//      make author column editable with a textfield
        availableNumber.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

//      gets the new value and calls the setAvailableNumber method
        availableNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, Integer> event) {
                updateBookBtn.setVisible(true);

                ((Book)event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setAvailableNumber(event.getNewValue());
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
        primaryStage.setTitle("Library Management System - New Book");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void editBookList() {
        Book selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            libraryController.updateBook(selectedItem);

//          call the setTable method
            setTable();
        }
    }

    public void deleteBook() {
        Book selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            libraryController.deleteBook(selectedItem);

//          call the setTable method
            setTable();
        }
    }
}
