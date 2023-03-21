package com.t.library.Response;

import com.t.library.Entity.PublisherEntity;

import java.util.List;

public class PublisherListResponse extends BaseResponse{
    public PublisherListResponse(List<PublisherEntity> data){
        super(true,"Авторы");
        this.data = data;
    }
    private List<PublisherEntity> data;
}
