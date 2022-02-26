package com.spring.rest.xmlproj.repo;

import com.spring.rest.xmlproj.util.AuthenticationUtilities;

import java.io.IOException;
import java.util.List;

public interface Repo<T> {

    T upis(T entitet) throws Exception;

    T dobaviPoId(String id) throws Exception;

    List<T> dobaviSve() throws Exception;

    void generisiXML(T entitet);
}
