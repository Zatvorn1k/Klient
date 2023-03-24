package com.t.library.Controller;
import com.t.library.Entity.AuthorEntity;
import com.t.library.Entity.BookEntity;
import com.t.library.Entity.PublisherEntity;
import com.t.library.Model.AuthorModel;
import com.t.library.Model.BookModel;
import com.t.library.Model.ComboModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.t.library.Controller.MainController.*;

public class EditBookController {

    @FXML
    private ComboBox<ComboModel> FieldBookAuthor;

    @FXML
    private TextField FieldBookKind;
    @FXML
    private MainController main = new MainController();

    @FXML
    private ComboBox<ComboModel> FieldBookPublisher;

    @FXML
    private TextField FieldBookTitle;

    @FXML
    private TextField FieldBookYear;
    private ObservableList<ComboModel> authorMap = FXCollections.observableArrayList();
    private ObservableList<ComboModel> publisherMap = FXCollections.observableArrayList();
    private Stage editBookStage;
    private BookEntity book;
    private int bookID;
    private boolean okClicked = false;
    @FXML
    private void initialize() throws Exception {
        updateMap();
        updateComboBox();
    }
    private void updateComboBox() throws Exception {
        FieldBookAuthor.setItems(authorMap);
        FieldBookPublisher.setItems(publisherMap);
    }
    private void updateMap(){
        for(int i = 0;i < authorData.size();i++){
            authorMap.add(new ComboModel(authorData.get(i).getId(), main.getFio(authorData.get(i))));
        }
        for(int i = 0;i < publisherData.size();i++){
            publisherMap.add(new ComboModel(publisherData.get(i).getId(), publisherData.get(i).getPublisher()));

        }
    }
    public void setDialogStage(Stage dialogStage) {
        this.editBookStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }
    public void setInit (BookEntity bookIn, int Id){
        this.book = bookIn;
        this.bookID = Id;
    }
    @FXML
    private void SaveBook() throws IOException {
        ComboModel itemAuthor = FieldBookAuthor.getSelectionModel().getSelectedItem();
        ComboModel itemPublisher = FieldBookPublisher.getSelectionModel().getSelectedItem();
        if (isInputValid()) {
            book.setTitle(FieldBookTitle.getText());
            book.setYear(FieldBookYear.getText());
            book.setAuthor(hashAuthor.get(itemAuthor.getId()));
            book.setPublisher(hashPublisher.get(itemPublisher.getId()));
            book.setKind(FieldBookKind.getText());
            okClicked = true;
            editBookStage.close();
            setLabels(itemAuthor, itemPublisher);
            booksData.set(bookID, book);
        }
    }
    public void setLabels (ComboModel itemAuthor, ComboModel itemPublisher) {
        modelBook.add(new BookModel(
                book.getId(),
                book.getTitle(),
                main.getFio(hashAuthor.get(itemAuthor.getId())),
                itemPublisher.getMeaning(),
                book.getYear(),
                book.getKind()));
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


