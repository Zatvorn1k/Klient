package com.t.library.Model;

import com.t.library.Entity.AuthorEntity;
import com.t.library.Entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthorModel {
    private int id;
    private String AuthorLastname;
    private String AuthorName;
    private String AuthorSurname;
    private AuthorEntity ModelEntityAuthor;
}
