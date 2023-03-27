package com.t.library.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ComboModel {
    private int id;
    private String meaning;



    @Override
    public String toString(){return meaning;}
}
