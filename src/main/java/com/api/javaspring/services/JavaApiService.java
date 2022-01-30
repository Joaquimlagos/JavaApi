package com.api.javaspring.services;

import com.api.javaspring.models.BookModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public interface JavaApiService {
  public BookModel create(BookModel bookModel);

  public BookModel get(Integer id);

  public BookModel update(Integer id, BookModel bookModel);

  public void delete(Integer id);

  public List<BookModel> listAll();
}
