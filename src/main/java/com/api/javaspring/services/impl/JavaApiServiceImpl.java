package com.api.javaspring.services.impl;

import com.api.javaspring.exeptions.NotFoundExeption;
import com.api.javaspring.models.BookModel;
import com.api.javaspring.services.JavaApiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JavaApiServiceImpl implements JavaApiService {

    private List<BookModel> listBooks = new ArrayList<>();

  @Override
  public BookModel create(BookModel bookModel) {
    String titleFormated = titleTextFormated(bookModel.getTitle());
   bookModel.setTitle(titleFormated);

   listBooks.add(bookModel);
   return bookModel;
  }

  @Override
  public BookModel get(Integer id) {
    try {
      return listBooks.get(id);
    } catch (Exception IndexOutOfBoundsException){
      throw new NotFoundExeption(id);
    }
  }

  @Override
  public BookModel update(Integer id, BookModel bookModel) {
    String titleFormated = titleTextFormated(bookModel.getTitle());

    BookModel book = this.listBooks.get(id.intValue());
    book.setTitle(titleFormated);
    book.setYear(bookModel.getYear());
    book.setDescription(bookModel.getDescription());
    book.setBookType(bookModel.getBookType());
    book.setAuthor(bookModel.getAuthor());

    delete(id.intValue());
    this.listBooks.add(id.intValue(),book);
    return listBooks.get(id.intValue());
  }

  @Override
  public void delete(Integer id) {
   this.listBooks.remove(id.intValue());
  }

  @Override
  public List<BookModel> listAll() {
    return listBooks;
  }

  private String titleTextFormated(String title)
  {
    return String.format("o nome do livro é %s",title);
  }
}
