package com.t.library.Controller;

import com.t.library.Entity.AuthorEntity;
import com.t.library.Entity.PublisherEntity;
import com.t.library.MainApp;
import com.t.library.Entity.BookEntity;
import com.t.library.Model.AuthorModel;
import com.t.library.Model.BookModel;
import com.t.library.Model.PublisherModel;
import com.t.library.Utils.HTTPUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainController {

    public static String apiBook = "http://localhost:1000/api/v1/book/";
    public static String apiAuthor = "http://localhost:1000/api/v1/author/";
    public static String apiPublisher = "http://localhost:1000/api/v1/publish/";
    public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
    public static ObservableList<AuthorEntity> authorData = FXCollections.observableArrayList();
    public static ObservableList<PublisherEntity> publisherData = FXCollections.observableArrayList();
    public static ObservableList<BookModel> modelBook = FXCollections.observableArrayList();
    public static ObservableList<AuthorModel> modelAuthor = FXCollections.observableArrayList();
    public static ObservableList<PublisherModel> modelPublisher = FXCollections.observableArrayList();
    public static Map<Integer, AuthorEntity> hashAuthor = new HashMap<>();
    public static Map<Integer, PublisherEntity> hashPublisher = new HashMap<>();
    static HTTPUtils http = new HTTPUtils();
    static Gson gson = new Gson();
    @FXML
    private TableColumn<AuthorModel, String> AuthorLastname;

    @FXML
    private TableColumn<AuthorModel, String> AuthorName;

    @FXML
    private TableColumn<AuthorModel, String> AuthorSurname;

    @FXML
    private TableColumn<PublisherModel, String> PublisherCity;

    @FXML
    private TableColumn<PublisherModel, String> PublisherName;

    @FXML
    public TableView<BookModel> tableBooks;
    @FXML
    private TableView<AuthorModel> tableAuthor;
    @FXML
    private TableView<PublisherModel> tablePublisher;

    @FXML
    private TableColumn<BookModel, String> bookName;

    @FXML
    private TableColumn<BookModel, String> bookAuthor;

    @FXML
    private TableColumn<BookModel, String> bookPublisher;

    @FXML
    private TableColumn<BookModel, String> bookYear;

    @FXML
    private TableColumn<BookModel, String> bookChapter;

    @FXML
    public int AddId = 0;

/////////////////////Инициализацияю////////////////////////////
    @FXML
    private void initialize() throws Exception {
        getData();
        Hash();
        AddModel();
        updateTable();
    }
    @FXML
    private void Hash(){
        for(int i = 0; i < authorData.size(); i++){
            hashAuthor.put(authorData.get(i).getId(), authorData.get(i));
        }
        for(int i = 0; i < hashPublisher.size(); i++){
            hashPublisher.put(publisherData.get(i).getId(), publisherData.get(i));
        }
    }
    private void updateTable() throws Exception {
        bookName.setCellValueFactory(new PropertyValueFactory<BookModel, String>("title"));
        bookPublisher.setCellValueFactory(new PropertyValueFactory<BookModel, String>("publisher"));
        bookYear.setCellValueFactory(new PropertyValueFactory<BookModel, String>("year"));
        bookChapter.setCellValueFactory(new PropertyValueFactory<BookModel, String>("kind"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<BookModel, String>("fio"));
        tableBooks.setItems(modelBook);
        AuthorLastname.setCellValueFactory(new PropertyValueFactory<AuthorModel, String>("AuthorLastname"));
        AuthorName.setCellValueFactory(new PropertyValueFactory<AuthorModel, String>("AuthorName"));
        AuthorSurname.setCellValueFactory(new PropertyValueFactory<AuthorModel, String>("AuthorSurname"));
        tableAuthor.setItems(modelAuthor);
        PublisherCity.setCellValueFactory(new PropertyValueFactory<PublisherModel, String>("PublisherCity"));
        PublisherName.setCellValueFactory(new PropertyValueFactory<PublisherModel, String>("PublisherName"));
        tablePublisher.setItems(modelPublisher);
    }
    private void AddModel() {
        for (int i = 0; i < booksData.size(); i++) {
            modelBook.add(new BookModel(
                    booksData.get(i).getId(),
                    booksData.get(i).getTitle(),
                    getFio(booksData.get(i).getAuthor()),
                    hashPublisher.get(booksData.get(i).getPublisher().getId()).getPublisher(),
                    booksData.get(i).getYear(),
                    booksData.get(i).getKind()));
        }
            for (int i = 0; i < authorData.size(); i++) {
                modelAuthor.add(new AuthorModel(
                        authorData.get(i).getId(),
                        authorData.get(i).getLastname(),
                        authorData.get(i).getName(),
                        authorData.get(i).getSurname()));
            }
        for (int i = 0; i < publisherData.size(); i++) {
            modelPublisher.add(new PublisherModel(
                    publisherData.get(i).getId(),
                    publisherData.get(i).getCity(),
                    publisherData.get(i).getPublisher()));
        }

    }
    public String getFio(AuthorEntity author) {
        return author.getLastname() + " " + author.getName().charAt(0) + ". " + author.getSurname().charAt(0);
    }
    public static void getData() throws Exception {
        String resBook = http.get(apiBook, "all");
        System.out.println(resBook);
        JsonObject base = gson.fromJson(resBook, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");
        for (int i = 0; i < dataArr.size(); i++) {
            BookEntity newBook = gson.fromJson(dataArr.get(i).toString(), BookEntity.class);
            booksData.add(newBook);
        }
        String resAuthor = http.get(apiAuthor, "all");
        System.out.println(resAuthor);
        base = gson.fromJson(resAuthor, JsonObject.class);
        dataArr = base.getAsJsonArray("data");
        for (int i = 0; i < dataArr.size(); i++) {
            AuthorEntity newAuthor = gson.fromJson(dataArr.get(i).toString(), AuthorEntity.class);
            authorData.add(newAuthor);
        }
        String resPublisher = http.get(apiPublisher, "all");
        System.out.println(resPublisher);
        base = gson.fromJson(resPublisher, JsonObject.class);
        dataArr = base.getAsJsonArray("data");
        for (int i = 0; i < dataArr.size(); i++) {
            PublisherEntity newPublisher = gson.fromJson(dataArr.get(i).toString(), PublisherEntity.class);
            publisherData.add(newPublisher);
        }

    }

    //////////////////////////Добавление//////////////////////////////////
    @FXML
    private void addWinBook() throws IOException {
        BookEntity tempBook = new BookEntity();
        booksData.add(tempBook);
        MainApp.showPersonEditDialog(tempBook, booksData.size() - 1);
        addBook(tempBook);
    }
    public static void addBook(BookEntity book) throws IOException {
        book.setId(0);
        System.out.println(http.post(apiBook + "add", gson.toJson(book).toString()));
    }
    @FXML
    private void addWinAuthor() throws IOException {
        AuthorEntity tempAuthor = new AuthorEntity();
        authorData.add(tempAuthor);
        MainApp.showAuthorEditDialog(tempAuthor, authorData.size() - 1);
        addAuthor(tempAuthor);
    }
    public static void addAuthor(AuthorEntity author) throws IOException {
        author.setId(0);
        http.post(apiAuthor + "add", gson.toJson(author).toString());
    }
    @FXML
    private void addWinPublisher() throws IOException {
        PublisherEntity tempPublisher = new PublisherEntity();
        publisherData.add(tempPublisher);
        MainApp.showPublisherEditDialog(tempPublisher, publisherData.size() - 1);
        addPublisher(tempPublisher);
    }
    public static void addPublisher(PublisherEntity publisher) throws IOException {
        publisher.setId(0);
        http.post(apiPublisher + "add", gson.toJson(publisher).toString());
    }
    public static void updateBook(BookEntity book) throws IOException {
        http.put(apiBook + "update", gson.toJson(book).toString());
    }
    //////////////////////////////////Удаление////////////////////////////////
        @FXML
    private void DeleteBook() throws IOException {
        BookModel selectedPerson = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            System.out.println(selectedPerson.getId());
            http.delete(apiBook + "delete/", selectedPerson.getId());
            modelBook.remove(selectedPerson);
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбраный пользователь");
            alert.setContentText("Пожалуйста, выберите пользователя в таблице");
            alert.showAndWait();
        }
    }
    @FXML
    private void DeleteAuthor() throws IOException {
        AuthorModel selectedPerson = tableAuthor.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            System.out.println(selectedPerson.getId());
            http.delete(apiAuthor + "delete/", selectedPerson.getId());
            modelAuthor.remove(selectedPerson);
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбраный пользователь");
            alert.setContentText("Пожалуйста, выберите пользователя в таблице");
            alert.showAndWait();
        }
    }
    @FXML
    private void DeletePublisher() throws IOException {
        PublisherModel selectedPerson = tablePublisher.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            System.out.println(selectedPerson.getId());
            http.delete(apiPublisher + "delete/", selectedPerson.getId());
            modelPublisher.remove(selectedPerson);
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбраный пользователь");
            alert.setContentText("Пожалуйста, выберите пользователя в таблице");
            alert.showAndWait();
        }
    }
}

