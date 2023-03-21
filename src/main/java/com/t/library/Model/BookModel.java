package com.t.library.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BookModel {
    private String title;
    private String fio;
    private String publisher;
    private String year;
    private String kind;
    private String AuthorLastname;
    private String AuthorName;
    private String AuthorSurname;
    private String PublisherCity;
    private String PublisherName;
}
