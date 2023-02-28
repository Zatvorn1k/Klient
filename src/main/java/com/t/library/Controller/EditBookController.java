package com.t.library.Controller;
import com.t.library.Entity.BookEntity;
import com.t.library.MainApp;
import com.t.library.Utils.HTTPUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.NonNull;

import java.io.IOException;

import static com.t.library.Controller.MainController.booksData;
import static com.t.library.Controller.MainController.updateBook;

public class EditBookController {

    static HTTPUtils http = new HTTPUtils();

    @FXML
    private TextField Field_bookName;

    @FXML
    private TextField Field_bookAuthor;

    @FXML
    private TextField Field_bookPublishing;

    @FXML
    private TextField Field_bookYear;

    @FXML
    private TextField Field_bookChapter;


    private Stage editBookStage;
    private BookEntity book;
    private int bookID;
    private boolean okClicked = false;

    public void setDialogStage (Stage dialogStage) { this.editBookStage = dialogStage; }

    public boolean isOkClicked() { return okClicked; }


    public void setLabels (BookEntity bookIn, int Id) {
        this.book = bookIn;
        this.bookID = Id;

        Field_bookName.setText(book.getTitle());
        Field_bookAuthor.setText(book.getAuthor());
        Field_bookPublishing.setText(book.getPublisher());
        Field_bookYear.setText(book.getYear());
        Field_bookChapter.setText(book.getKind());
    }
    @FXML
    private void SaveBook() throws IOException {
        if (isInputValid()) {
            book.setTitle(Field_bookName.getText());
            book.setAuthor(Field_bookAuthor.getText());
            book.setPublisher(Field_bookPublishing.getText());
            book.setYear(Field_bookYear.getText());
            book.setKind(Field_bookChapter.getText());

            okClicked = true;
            editBookStage.close();
            booksData.set(bookID, book);
        }
    }
    @FXML
    private void CloseBook() {
        editBookStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        try {
            if (!Field_bookName.getText().matches("[\\sA-ZА-Яa-za-я]{1,10}"))
                errorMessage += "Название книги, введено некорректно \n";
            if (!Field_bookAuthor.getText().matches("[\\sA-ZА-Яa-za-я]{1,10}") || Field_bookAuthor.getText() == null)
                errorMessage += "Имя Автора, введено некорректно \n";
            if (!Field_bookPublishing.getText().matches("[\\sA-ZА-Яa-za-я]{1,10}") || Field_bookPublishing.getText() == null)
                errorMessage += "Место публикации, введено некорректно\n";
            if (!Field_bookChapter.getText().matches("[\\sA-ZА-Яa-za-я]{1,10}") || Field_bookChapter.getText() == null)
                errorMessage += "Раздел содержания, введен некорректно!\n";
            else {
                try {
                    Integer.parseInt(Field_bookYear.getText());
                } catch (NumberFormatException e) {
                    errorMessage += "Не корректное значение года выпуска книги (должно быть целочисленным) !\n";
                }
            }
            if (!Field_bookYear.getText().matches("[\\d0-9]{3,4}") || Field_bookYear.getText() == null)
                errorMessage += "Год выпуска введен некорректно! \n";
        }catch (Exception e){
            errorMessage += "Пустое поле!";
        }


        if (errorMessage.length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editBookStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
