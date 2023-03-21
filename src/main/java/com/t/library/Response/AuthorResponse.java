package com.t.library.Response;

import com.t.library.Entity.AuthorEntity;

public class AuthorResponse extends BaseResponse{
    public AuthorResponse(boolean success, String message, AuthorEntity author){
        super(success, message);
        this.author = author;
    }
    public AuthorResponse(AuthorEntity author){
        super(true, "Author data");
    }
        private AuthorEntity author;
    }
