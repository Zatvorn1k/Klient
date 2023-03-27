package com.t.library.Controller;

import com.t.library.Entity.AuthorEntity;
import com.t.library.Entity.PublisherEntity;
import com.t.library.Model.AuthorModel;
import com.t.library.Model.PublisherModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.t.library.Controller.MainController.*;

public class EditPublisherController {
    ///////////////////////////////////Переменные//////////////////////////////////////////
    @FXML
    private TextField FieldPublisherCity;

    @FXML
    private TextField FieldPublisherName;
    @FXML
    private PublisherEntity publisher;

    @FXML
    private int publisherId;
    @FXML
    private boolean EditOrAdd;
    private boolean okClicked = false;
    private Stage editPublisherStage;
    public void setDialogStage(Stage dialogStage) {
        this.editPublisherStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

/////////////////////////////////////////////////Методы////////////////////////////////////////
public void setInit (PublisherEntity publisherIn, int Id, boolean Add) {
    this.publisher = publisherIn;
    this.publisherId = Id;
    this.EditOrAdd = Add;
}

    public void setLabels() {
        modelPublisher.add(new PublisherModel(
                publisher.getId(),
                publisher.getPublisher(),
                publisher.getCity(),
                publisher));
    }
    public void EditLabels(){
        PublisherModel model = new PublisherModel(
                publisher.getId(),
                publisher.getPublisher(),
                publisher.getCity(),
                publisher);
        modelPublisher.set(publisherId, model);
    }
    @FXML
    private void SavePublisher() throws IOException {
        if (isInputValid()) {
            publisher.setPublisher(FieldPublisherName.getText());
            publisher.setCity(FieldPublisherCity.getText());
            if (EditOrAdd){
            setLabels();
                }
            else
                EditLabels();
            okClicked = true;
            editPublisherStage.close();
            publisherData.set(publisherId, publisher);
        }
    }
    public void setEdit(){
        FieldPublisherName.setText(publisher.getPublisher());
        FieldPublisherCity.setText(publisher.getCity());
    }
    @FXML
    void close(ActionEvent event) {
        editPublisherStage.close();
    }
    private boolean isInputValid() {
        String errorMessage = "";
        try {
            if (!FieldPublisherName.getText().matches("[\\sA-ZА-Яa-za-я]{2,20}"))
                errorMessage += "Назание, введено некорректно \n";
            if (!FieldPublisherCity.getText().matches("[\\sA-ZА-Яa-za-я]{3,20}"))
                errorMessage += "Город, введен некорректно \n";
        }catch (Exception e){
            errorMessage += "Пустое поле!";
        }


        if (errorMessage.length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editPublisherStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
