package com.t.library.Response;

import com.t.library.Entity.AuthorEntity;
import com.t.library.Entity.PublisherEntity;

import java.util.List;

public class AuthorListResponse extends BaseResponse{
    public AuthorListResponse(List<AuthorEntity> data){
        super(true,"Авторы");
        this.data = data;
    }
    private List<AuthorEntity> data;
}
