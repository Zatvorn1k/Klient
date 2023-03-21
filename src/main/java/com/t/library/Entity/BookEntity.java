package com.t.library.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BookEntity {
    public Long id;
    public String title;
    public AuthorEntity author;
    public PublisherEntity publisher;
    public String year;
    public String kind;

}
