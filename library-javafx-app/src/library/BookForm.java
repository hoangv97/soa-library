package library;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;

public class BookForm {

    private LibraryController libraryController = new LibraryController();

    @FXML
    private Button submitBtn;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private Spinner<Integer> availableNumberField;

    public BookForm() {
        availableNumberField = new Spinner<>();

        availableNumberField.setValueFactory(new SpinnerValueFactory<Integer>() {
            @Override
            public void decrement(int i) {
                this.setValue(this.getValue() - i);
            }

            @Override
            public void increment(int i) {
                this.setValue(this.getValue() + i);
            }
        });
    }

    public void submitNewBook() {
        Window owner = submitBtn.getScene().getWindow();

        if(titleField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!","Please enter book's title!");
            return;
        }

        if(authorField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!","Please enter book's author!");
            return;
        }

        if(descriptionField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!","Please enter book's description!");
            return;
        }

        libraryController.createNewBook(titleField.getText(), authorField.getText(), descriptionField.getText(), availableNumberField.getValue());

        AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Success!", "Created New Book.");
    }

}
