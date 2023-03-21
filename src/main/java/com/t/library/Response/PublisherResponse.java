package com.t.library.Response;

import com.t.library.Entity.AuthorEntity;
import com.t.library.Entity.PublisherEntity;

public class PublisherResponse extends BaseResponse{
    public PublisherResponse(boolean success, String message, PublisherEntity author){
        super(success, message);
        this.publisher = publisher;
    }
    public PublisherResponse(PublisherEntity publisher){
        super(true, "Author data");
    }
    private PublisherEntity publisher;
}

