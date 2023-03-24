package com.t.library.Controller;

import com.t.library.Entity.AuthorEntity;
import com.t.library.Entity.PublisherEntity;
import com.t.library.Model.PublisherModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.t.library.Controller.MainController.modelPublisher;
import static com.t.library.Controller.MainController.publisherData;

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
    private boolean okClicked = false;
    private Stage editPublisherStage;
    public void setDialogStage(Stage dialogStage) {
        this.editPublisherStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

/////////////////////////////////////////////////Методы////////////////////////////////////////
public void setInit (PublisherEntity publisherIn, int Id) {
    this.publisher = publisherIn;
    this.publisherId = Id;
}

    public void setLabels() {
        modelPublisher.add(new PublisherModel(
                publisher.getId(),
                publisher.getPublisher(),
                publisher.getCity()));
    }
    @FXML
    private void SavePublisher() throws IOException {
                publisher.setPublisher(FieldPublisherName.getText());
                publisher.setCity(FieldPublisherCity.getText());
            setLabels();
            okClicked = true;
            editPublisherStage.close();
            publisherData.set(publisherId, publisher);
    }
    @FXML
    void close(ActionEvent event) {
        editPublisherStage.close();
    }
}
