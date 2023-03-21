package com.t.library.Controller;

import com.google.gson.JsonParser;
import com.t.library.Entity.AuthorEntity;
import com.t.library.Entity.PublisherEntity;
import com.t.library.MainApp;
import com.t.library.Entity.BookEntity;
import com.t.library.Model.BookModel;
import com.t.library.Utils.HTTPUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class MainController {

    public static String api = "http://localhost:2825/api/v1/book/";
    public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
    public static ObservableList<AuthorEntity> authorData = FXCollections.observableArrayList();
    public static ObservableList<PublisherEntity> publisherData = FXCollections.observableArrayList();
    public static ObservableList<BookModel> model = FXCollections.observableArrayList();

    static HTTPUtils http = new HTTPUtils();
    static Gson gson = new Gson();
    @FXML
    private TableColumn<BookModel, String> AuthorLastname;

    @FXML
    private TableColumn<BookModel, String> AuthorName;

    @FXML
    private TableColumn<BookModel, String> AuthorSurname;

    @FXML
    private TableColumn<BookModel, String> PublisherCity;

    @FXML
    private TableColumn<BookModel, String> PublisherName;

    @FXML
    public TableView<BookModel> tableBooks;
    @FXML
    private TableView<BookModel> tableAuthor;
    @FXML
    private TableView<BookModel> tablePublisher;

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


    @FXML
    private void initialize() throws Exception {
        getData();
        AddModel();
        updateTable();
    }

    private void updateTable() throws Exception {
        bookName.setCellValueFactory(new PropertyValueFactory<BookModel, String>("title"));
        bookPublisher.setCellValueFactory(new PropertyValueFactory<BookModel, String>("publisher"));
        bookYear.setCellValueFactory(new PropertyValueFactory<BookModel, String>("year"));
        bookChapter.setCellValueFactory(new PropertyValueFactory<BookModel, String>("kind"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<BookModel, String>("fio"));
        tableBooks.setItems(model);
        AuthorLastname.setCellValueFactory(new PropertyValueFactory<BookModel, String>("AuthorLastname"));
        AuthorName.setCellValueFactory(new PropertyValueFactory<BookModel, String>("AuthorName"));
        AuthorSurname.setCellValueFactory(new PropertyValueFactory<BookModel, String>("AuthorSurname"));
        tableAuthor.setItems(model);
        PublisherCity.setCellValueFactory(new PropertyValueFactory<BookModel, String>("PublisherCity"));
        PublisherName.setCellValueFactory(new PropertyValueFactory<BookModel, String>("PublisherName"));
        tablePublisher.setItems(model);
    }

    private void AddModel() {
        for (int i = 0; i < booksData.size(); i++) {
            model.add(new BookModel(
                    booksData.get(i).getTitle(),
                    getFio(i),
                    publisherData.get(i).getPublisher(),
                    booksData.get(i).getYear(),
                    booksData.get(i).getKind(),
                    authorData.get(i).getLastname(),
                    authorData.get(i).getName(),
                    authorData.get(i).getSurname(),
                    publisherData.get(i).getCity(),
                    publisherData.get(i).getPublisher()));
        }
    }

    private String getFio(int i) {
        return authorData.get(i).getLastname() + " " + authorData.get(i).getName().charAt(0) + ". " + authorData.get(i).getSurname().charAt(0);
    }

    @FXML
    private void addWin() throws IOException {
        BookEntity tempBook = new BookEntity();
        booksData.add(tempBook);
        MainApp.showPersonEditDialog(tempBook, booksData.size() - 1);
        Long id = addB(tempBook);
        tempBook.setId(id);
    }
    public static Long addB(BookEntity book) throws IOException {
        book.setId(null);
        String res = http.post(api + "add", gson.toJson(book).toString());
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        Long tempId = jsonObject.getAsJsonObject("data").get("id").getAsLong();
        return tempId;
    }
//    public static Long addAuthor(BookEntity book) throws IOException {
//        book.setId(null);
//        String res = http.post(api + "add", gson.toJson(book).toString());
//        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
//        Long tempId = jsonObject.getAsJsonObject("data").get("id").getAsLong();
//        return tempId;
//    }
//    public static Long add(BookEntity book) throws IOException {
//        book.setId(null);
//        String res = http.post(api + "add", gson.toJson(book).toString());
//        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
//        Long tempId = jsonObject.getAsJsonObject("data").get("id").getAsLong();
//        return tempId;
//    }

    public static void getData() throws Exception {
        String res = http.get(api, "all");
        System.out.println(res);
        JsonObject base = gson.fromJson(res, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");
        for (int i = 0; i < dataArr.size(); i++) {
            BookEntity newBook = gson.fromJson(dataArr.get(i).toString(), BookEntity.class);
            authorData.add(newBook.getAuthor());
            publisherData.add(newBook.getPublisher());
            booksData.add(newBook);
        }
    }

    public static void updateBook(BookEntity book) throws IOException {
        http.put(api + "update", gson.toJson(book).toString());
    }
}

