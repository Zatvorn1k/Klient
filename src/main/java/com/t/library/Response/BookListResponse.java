package com.t.library.Response;

import com.t.library.Entity.BookEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BookListResponse extends BaseResponse {
    public List<BookEntity> data;

    public BookListResponse(List<BookEntity> data) {
        super(true, "Книги");
        this.data = data;
    }


}