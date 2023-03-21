package com.t.library.Controller;
import com.t.library.Entity.AuthorEntity;
import com.t.library.Entity.BookEntity;
import com.t.library.Entity.PublisherEntity;
import com.t.library.MainApp;
import com.t.library.Utils.HTTPUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.NonNull;

import java.io.IOException;

import static com.t.library.Controller.MainController.*;

public class EditBookController {
    @FXML
    private TextField FieldAuthorLastname;

    @FXML
    private TextField FieldAuthorName;

    @FXML
    private TextField FieldAuthorSurname;

    @FXML
    private ComboBox<?> FieldBookAuthor;

    @FXML
    private TextField FieldBookKind;

    @FXML
    private ComboBox<?> FieldBookPublisher;

    @FXML
    private TextField FieldBookTitle;

    @FXML
    private TextField FieldBookYear;

    @FXML
    private TextField FieldPublisherCity;

    @FXML
    private TextField FieldPublisherName;
    @FXML
    public MainController mc = new MainController();

    private Stage editBookStage;
    private BookEntity book;
    private AuthorEntity author;
    private PublisherEntity publisher;
    private int bookID, authorId, PublisherId;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.editBookStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

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
            mc.AddId = 1;
            book.setTitle(FieldBookTitle.getText());
            book.setYear(FieldBookYear.getText());
            book.setKind(FieldBookKind.getText());

            okClicked = true;
            editBookStage.close();
            booksData.set(bookID, book);
        }
    }

    @FXML
    private void SaveAuthor() throws IOException {
        if (isInputValid()) {
            mc.AddId = 2;
            author.setName(FieldAuthorName.getText());
            author.setLastname(FieldAuthorLastname.getText());
            author.setSurname(FieldAuthorSurname.getText());

            okClicked = true;
            editBookStage.close();
            authorData.set(authorId, author);
        }
    }

    @FXML
    private void SavePublisher() throws IOException {
        if (isInputValid()) {
            mc.AddId = 3;
            publisher.setPublisher(FieldPublisherName.getText());
            publisher.setCity(FieldPublisherCity.getText());

            okClicked = true;
            editBookStage.close();
            publisherData.set(PublisherId, publisher);
        }
    }

    @FXML
    void close(ActionEvent event) {
        editBookStage.close();
    }

    private boolean isInputValid() {
    return true;}
}
//        String errorMessage = "";
//        try {
//            if (!Field_bookName.getText().matches("[\\sA-ZА-Яa-za-я]{1,10}"))
//                errorMessage += "Название книги, введено некорректно \n";
//            if (!Field_bookAuthor.getText().matches("[\\sA-ZА-Яa-za-я]{1,10}") || Field_bookAuthor.getText() == null)
//                errorMessage += "Имя Автора, введено некорректно \n";
//            if (!Field_bookPublishing.getText().matches("[\\sA-ZА-Яa-za-я]{1,10}") || Field_bookPublishing.getText() == null)
//                errorMessage += "Место публикации, введено некорректно\n";
//            if (!Field_bookChapter.getText().matches("[\\sA-ZА-Яa-za-я]{1,10}") || Field_bookChapter.getText() == null)
//                errorMessage += "Раздел содержания, введен некорректно!\n";
//            else {
//                try {
//                    Integer.parseInt(Field_bookYear.getText());
//                } catch (NumberFormatException e) {
//                    errorMessage += "Не корректное значение года выпуска книги (должно быть целочисленным) !\n";
//                }
//            }
//            if (!Field_bookYear.getText().matches("[\\d0-9]{3,4}") || Field_bookYear.getText() == null)
//                errorMessage += "Год выпуска введен некорректно! \n";
//        }catch (Exception e){
//            errorMessage += "Пустое поле!";
//        }
//
//
//        if (errorMessage.length() == 0) return true;
//        else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.initOwner(editBookStage);
//            alert.setTitle("Ошибка заполнения");
//            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
//            alert.setContentText(errorMessage);
//
//            alert.showAndWait();
//
//            return false;
//        }
//    }


