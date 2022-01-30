package com.api.javaspring.service;

import com.api.javaspring.enums.BookType;
import com.api.javaspring.models.AuthorModel;
import com.api.javaspring.models.BookModel;
import com.api.javaspring.services.impl.JavaApiServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JavaApiServiceTest {

  @InjectMocks
  private JavaApiServiceImpl javaApiService;

  @BeforeEach
  public void init(){
    MockitoAnnotations.initMocks(this);
    MockMvcBuilders.standaloneSetup(javaApiService).build();
  }

  final Integer id = 0;
  final Gson gson = new Gson();
  final AuthorModel author = new AuthorModel("pedro", "almeida", 20);
  final BookModel book = new BookModel("o nome do livro é titlejj", 2000,"um livro legal", author, BookType.NATIONAL);
  final String bookToJson = gson.toJson(book);
  JavaApiServiceImpl mock = org.mockito.Mockito.mock(JavaApiServiceImpl.class);

 @Test
  void whenMethodCreateBookIsCalledThenShuldReturnABook(){
   when(mock.create(book)).thenReturn(book);

   BookModel result = mock.create(book);

   Assertions.assertNotNull(result);
   Assertions.assertEquals(book,result);
 }
  @Test
  void whenMethodGetBookIsCalledThenShuldReturnABook(){
    when(mock.get(id)).thenReturn(book);

    BookModel result = mock.get(id);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(book,result);
  }

  @Test
  void whenMethodDeleteIsCalledThenShuldNotReturnABook(){
    mock.delete(id);
    verify(mock, times(1)).delete(id);
  }

  @Test
  void whenMethodUpdateIsCalledThenShuldReturnABook(){
    when(mock.update(id,book)).thenReturn(book);

    BookModel result = mock.update(id,book);

    Assertions.assertNotNull(result);
  }

}
