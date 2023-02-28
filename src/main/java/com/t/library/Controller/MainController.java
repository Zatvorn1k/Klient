package com.t.library.Controller;

import com.google.gson.JsonParser;
import com.t.library.MainApp;
import com.t.library.Entity.BookEntity;
import com.t.library.Utils.HTTPUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

import static com.t.library.MainApp.secretka;

public class MainController {

    public static String api = "http://localhost:2825/api/v1/";
    public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
    static HTTPUtils http = new HTTPUtils();
    static Gson gson = new Gson();
    @FXML
    public TableView<BookEntity> tableBooks;

    @FXML
    private TableColumn<BookEntity, String> bookName;

    @FXML
    private TableColumn<BookEntity, String> bookAuthor;

    @FXML
    private TableColumn<BookEntity, String> bookPublisher;

    @FXML
    private TableColumn<BookEntity, String> bookYear;

    @FXML
    private TableColumn<BookEntity, String> bookChapter;

    @FXML
    private void initialize() throws Exception {
        getData();
        updateTable();
    }

    private void updateTable() throws Exception {
        bookName.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("title"));
        bookPublisher.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("publisher"));
        bookYear.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("year"));
        bookChapter.setCellValueFactory(new PropertyValueFactory<BookEntity, String>( "kind"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("author"));
        tableBooks.setItems(booksData);
    }

    @FXML
    private void addBook() throws IOException {
        BookEntity tempBook = new BookEntity();
        booksData.add(tempBook);
        MainApp.showPersonEditDialog(tempBook, booksData.size() - 1);
        Long id = addB(tempBook);
        tempBook.setId(id);
    }

    @FXML
    private void removeBook() throws IOException {
        BookEntity selectedPerson = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            System.out.println(selectedPerson.getId());
            http.delete(api + "delete/", selectedPerson.getId());
            booksData.remove(selectedPerson);
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбраный пользователь");
            alert.setContentText("Пожалуйста, выберите пользователя в таблице");
            alert.showAndWait();
        }
    }
    @FXML
    void secret(ActionEvent event) {
        try {
            secretka();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void duplicateBook() throws IOException {
        BookEntity selectedPerson = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            addB(selectedPerson);
            booksData.add(booksData.indexOf(selectedPerson) + 1, selectedPerson);
        } else {
            // Ничего не выбрано
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.initOwner(Application.getPrimaryStage());
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбраный польватель");
            alert.setContentText("Пожалуйста, выберите пользвоателя в таблице");
            alert.setContentText("Пожалуйста, выберите пользвоателя в таблицеа");
            alert.showAndWait();
        }
    }

    @FXML
    private void editBook() throws Exception{
        BookEntity selectedPerson = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            MainApp.showPersonEditDialog(selectedPerson, booksData.indexOf(selectedPerson));
            http.put(api + "update", gson.toJson(selectedPerson).toString());
        }
        else {
            // Ничего не выбрано
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.initOwner(Application.getPrimaryStage());
            alert.setTitle("Ничего не выбрно");
            alert.setHeaderText("Отсутствует выбраный польватель");
            alert.setContentText("Пожалуйста, выберите пользвоателя в таблице");
            alert.setContentText("Пожалуйста, выберите пользвоателя в таблицеа");
            alert.showAndWait();
        }
    }

    public static void getData() throws Exception {
        String res = http.get(api, "all");
        System.out.println(res);
        // Получение базового значения по каркасу BaseEntity
        JsonObject base = gson.fromJson(res, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");
        // Парсинг значений со вторго уровня вложенности (data) в массив книг в таблице ObservableList
        for (int i = 0; i < dataArr.size(); i++) {
            BookEntity newBook = gson.fromJson(dataArr.get(i).toString(), BookEntity.class);
            booksData.add(newBook);
        }
    }

    public static void updateBook (BookEntity book) throws IOException {
        http.put(api + "update", gson.toJson(book).toString());
    }
    public static Long addB(BookEntity book) throws IOException {
        book.setId(null);
        String res = http.post(api + "add", gson.toJson(book).toString());
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        Long tempId = jsonObject.getAsJsonObject("data").get("id").getAsLong();
        return tempId;
        }
    }

