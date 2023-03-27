package com.t.library.Model;

import com.t.library.Entity.PublisherEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PublisherModel {
    private int id;
    private String PublisherCity;
    private String PublisherName;
    private PublisherEntity ModelEntityPublisher;
}
