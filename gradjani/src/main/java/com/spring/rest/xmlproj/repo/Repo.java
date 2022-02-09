package com.spring.rest.xmlproj.repo;

import com.spring.rest.xmlproj.util.AuthenticationUtilities;

import java.io.IOException;
import java.util.List;

public interface Repo<T> {

    final String JAXBKontekst = "com.spring.rest.xmlproj.obj";

    T upis(T entitet) throws Exception;

    T dobaviPoId(String id) throws Exception;

    List<T> dobaviSve() throws Exception;
}
