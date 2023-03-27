package com.t.library;

import com.t.library.Controller.EditAuthorController;
import com.t.library.Controller.EditBookController;
import com.t.library.Controller.EditPublisherController;
import com.t.library.Controller.MainController;
import com.t.library.Entity.AuthorEntity;
import com.t.library.Entity.BookEntity;
import com.t.library.Entity.PublisherEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private AnchorPane mainApp;

    @Override

    public void start(Stage primaryStage) throws IOException {
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/main.fxml"));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("view/ico.png")));
        primaryStage.setTitle("Zs's Library");
            primaryStage.setMaxWidth(735);
            primaryStage.setMaxHeight(500);

        mainApp = (AnchorPane) loader.load();

        Scene scene = new Scene(mainApp);
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
   public static boolean showPersonEditDialog(BookEntity bookObj, int id, boolean Add){
       try {
          FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/editWinBook.fxml"));
             AnchorPane main = (AnchorPane) loader.load();
             Stage dialogStage = new Stage();
             dialogStage.setTitle("Редактирование книги");
           dialogStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("view/ico.png")));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(main);
           dialogStage.setMaxWidth(469);
           dialogStage.setMaxHeight(330);
             EditBookController controller = loader.getController();
             controller.setDialogStage(dialogStage);
           controller.setInit(bookObj, id, Add);
           controller.setEdit();
           dialogStage.setScene(scene);
            dialogStage.showAndWait();
             return controller.isOkClicked();
         } catch (IOException e){
            e.printStackTrace();
            return false;
       }
    }
    public static boolean showAuthorEditDialog(AuthorEntity authorObj, int id, boolean Add){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/editWinAuthor.fxml"));
            AnchorPane main = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование Автора");
            dialogStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("view/ico.png")));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setMaxWidth(468);
            dialogStage.setMaxHeight(285);
            Scene scene = new Scene(main);
            EditAuthorController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setInit(authorObj, id, Add);
            controller.setEdit();
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean showPublisherEditDialog(PublisherEntity publisherObj, int id, boolean Add){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/editWinPublisher.fxml"));
            AnchorPane main = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование Издания");
            dialogStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("view/ico.png")));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setMaxWidth(469);
            dialogStage.setMaxHeight(310);
            Scene scene = new Scene(main);
            EditPublisherController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setInit(publisherObj, id, Add);
            controller.setEdit();
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {launch();}
}