package com.t.library.Controller;

import com.t.library.Entity.AuthorEntity;
import com.t.library.Entity.BookEntity;
import com.t.library.Model.AuthorModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private TextField FieldAuthorSurname;
    @FXML
    private AuthorEntity author;
    @FXML
    private int authorId;
    private boolean okClicked = false;
    private Stage editAuthorStage;
    public void setDialogStage(Stage dialogStage) {
        this.editAuthorStage = dialogStage;
    }
    public boolean isOkClicked() {
        return okClicked;
    }
    ////////////////////////////////////Методы//////////////////////////////////////////
    public void setInit (AuthorEntity authorIn, int Id) {
        this.author = authorIn;
        this.authorId = Id;
    }
    public void setLabels(){
            modelAuthor.add(new AuthorModel(
                    author.getId(),
                    author.getLastname(),
                    author.getName(),
                    author.getSurname()));
        }

    @FXML
    private void SaveAuthor() throws IOException {
                author.setName(FieldAuthorName.getText());
            author.setLastname(FieldAuthorLastname.getText());
            author.setSurname(FieldAuthorSurname.getText());
            okClicked = true;
            editAuthorStage.close();
            setLabels();
            authorData.set(authorId, author);
    }
    @FXML
    void close(ActionEvent event) {
        editAuthorStage.close();
    }
}
