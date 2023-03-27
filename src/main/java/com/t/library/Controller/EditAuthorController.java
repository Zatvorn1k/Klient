package com.t.library.Controller;

import com.t.library.Entity.AuthorEntity;
import com.t.library.Entity.BookEntity;
import com.t.library.Model.AuthorModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.t.library.Controller.MainController.authorData;
import static com.t.library.Controller.MainController.modelAuthor;

public class EditAuthorController {
    ////////////////////////////Переменные/////////////////////////////////////
    @FXML
    private TextField FieldAuthorLastname;

    @FXML
    private TextField FieldAuthorName;
    @FXML
    private Stage editAuthorStage;

    @FXML
    private TextField FieldAuthorSurname;
    @FXML
    private AuthorEntity author;
    @FXML
    private int authorId;
    @FXML
    private boolean EditOrAdd;
    private boolean okClicked = false;
    public void setDialogStage(Stage dialogStage) {
        this.editAuthorStage = dialogStage;
    }
    public boolean isOkClicked() {
        return okClicked;
    }
    ////////////////////////////////////Методы//////////////////////////////////////////
    public void setInit (AuthorEntity authorIn, int Id, boolean Add) {
        this.EditOrAdd = Add;
        this.author = authorIn;
        this.authorId = Id;
    }
    public void setLabels(){
            modelAuthor.add(new AuthorModel(
                    author.getId(),
                    author.getLastname(),
                    author.getName(),
                    author.getSurname(),
                    author));
        }
        public void EditLabels(){
        AuthorModel model = new AuthorModel(
                author.getId(),
                author.getLastname(),
                author.getName(),
                author.getSurname(),
                author);
            modelAuthor.set(authorId, model);
        }

    @FXML
    private void SaveAuthor() throws IOException {
        if (isInputValid()) {
            author.setName(FieldAuthorName.getText());
            author.setLastname(FieldAuthorLastname.getText());
            author.setSurname(FieldAuthorSurname.getText());
            okClicked = true;
            editAuthorStage.close();
            if (EditOrAdd) {
                setLabels();
            }
                EditLabels();
            authorData.set(authorId, author);
        }
    }
    public void setEdit(){
        FieldAuthorName.setText(author.getName());
        FieldAuthorLastname.setText(author.getLastname());
        FieldAuthorSurname.setText(author.getSurname());
    }
    @FXML
    void close(ActionEvent event) {
        editAuthorStage.close();
    }
    private boolean isInputValid() {
        String errorMessage = "";
        try {
            if (!FieldAuthorName.getText().matches("[\\sA-ZА-Яa-za-я]{2,10}"))
                errorMessage += "Имя, введено некорректно \n";
            if (!FieldAuthorLastname.getText().matches("[\\sA-ZА-Яa-za-я]{3,10}"))
                errorMessage += "Фамилия, введена некорректно \n";
            if (!FieldAuthorSurname.getText().matches("[\\sA-ZА-Яa-za-я]{3,20}"))
                errorMessage += "Отчество, введено некорректно \n";
        }catch (Exception e){
            errorMessage += "Пустое поле!";
        }


        if (errorMessage.length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editAuthorStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
