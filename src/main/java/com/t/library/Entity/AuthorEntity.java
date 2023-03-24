package com.t.library.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class AuthorEntity {
    private int id;
    private String name;
    private String lastname;
    private String surname;
}
