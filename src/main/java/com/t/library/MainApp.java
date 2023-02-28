package com.t.library;

import com.t.library.Controller.EditBookController;
import com.t.library.Controller.MainController;
import com.t.library.Entity.BookEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

        mainApp = (AnchorPane) loader.load();

        Scene scene = new Scene(mainApp);
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
   public static boolean showPersonEditDialog(BookEntity bookObj, int id){
       try {
          FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/editBook.fxml"));
             AnchorPane main = (AnchorPane) loader.load();
             Stage dialogStage = new Stage();
             dialogStage.setTitle("Редактирование книги");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(main);
             EditBookController controller = loader.getController();
             controller.setDialogStage(dialogStage);
             controller.setLabels(bookObj, id);
           dialogStage.setScene(scene);
            dialogStage.showAndWait();
             return controller.isOkClicked();
         } catch (IOException e){
            e.printStackTrace();
            return false;
       }
    }
    public static void secretka() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/secret.fxml"));
        AnchorPane main = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("qt_cat.exe");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(main);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
    public static void main(String[] args) {launch();}
}