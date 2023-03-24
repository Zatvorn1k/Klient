package com.t.library.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PublisherEntity {
    private int id;
    private String publisher;
    private String city;
}
