package com.spring.rest.xmlproj.bservisi;

import java.util.List;

public interface IGenerickiServis<T> {

    void upis(T entitet);

    T dobaviPoId(String id);

    List<T> dobaviSve();

}
