package com.t.library.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BookModel {
    private int id;
    private String title;
    private String fio;
    private String publisher;
    private String year;
    private String kind;

}
