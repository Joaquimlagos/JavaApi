package com.api.javaspring.controller;


import com.api.javaspring.controllers.JavaApiController;
import com.api.javaspring.enums.BookType;
import com.api.javaspring.models.AuthorModel;
import com.api.javaspring.models.BookModel;
import com.api.javaspring.services.JavaApiService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class JavaApiControllerTest {

  private static final String BOOK_API_URL_PATH = "/api/book/";

  @Autowired
  private MockMvc mockMvc;

  @InjectMocks
  private JavaApiController javaApiController;

  @Mock
  private JavaApiService javaApiService;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(javaApiController)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
            .build();
  }

  private static final Integer id = 0;
  private static final Gson gson = new Gson();
  private static final AuthorModel author = new AuthorModel("pedro", "almeida", 20);
  private static final BookModel book = new BookModel("o nome do livro é titlejj", 2000,"um livro legal", author, BookType.NATIONAL);
  private static final String bookToJson = gson.toJson(book);

  @Test
  void whenPOSTIsCalledThenABookIsCreated() throws Exception {
    lenient().when(javaApiService.create(book)).thenReturn(book);
    mockMvc.perform(post(BOOK_API_URL_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8")
                    .accept(MediaType.APPLICATION_JSON)
                    .content(bookToJson))
            .andExpect(status().isCreated())
            .andDo(print())
            .andExpect(jsonPath("$.title", is(book.getTitle())))
            .andExpect(jsonPath("$.year", is(book.getYear())))
            .andExpect(jsonPath("$.description", is(book.getDescription())))
            .andExpect(jsonPath("$.bookType", is(book.getBookType().toString())))
            .andExpect(jsonPath("$.author.name", is(book.getAuthor().getName())))
            .andExpect(jsonPath("$.author.lastName", is(book.getAuthor().getLastName())))
            .andExpect(jsonPath("$.author.age", is(book.getAuthor().getAge())));
  }

  @Test
  void whenGetIsCalledThenABookIsReturned() throws Exception {
    lenient().when(javaApiService.get(id)).thenReturn(book);
    mockMvc.perform(get(BOOK_API_URL_PATH + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.title", is(book.getTitle())))
                    .andExpect(jsonPath("$.year", is(book.getYear())))
                    .andExpect(jsonPath("$.description", is(book.getDescription())))
                    .andExpect(jsonPath("$.bookType", is(book.getBookType().toString())))
                    .andExpect(jsonPath("$.author.name", is(book.getAuthor().getName())))
                    .andExpect(jsonPath("$.author.lastName", is(book.getAuthor().getLastName())))
                    .andExpect(jsonPath("$.author.age", is(book.getAuthor().getAge())));
  }

  @Test
  void whenDeleteIsCalledThenAStatusNoContentIsReturned() throws Exception {
    lenient().doNothing().when(javaApiService).delete(id);
    mockMvc.perform(delete(BOOK_API_URL_PATH+ id)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
  }

  @Test
  void whenPUTIsCalledThenABookIsCreated() throws Exception {
    lenient().when(javaApiService.update(id,book)).thenReturn(book);
    mockMvc.perform(put(BOOK_API_URL_PATH + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8")
                    .accept(MediaType.APPLICATION_JSON)
                    .content(bookToJson))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.title", is(book.getTitle())))
            .andExpect(jsonPath("$.year", is(book.getYear())))
            .andExpect(jsonPath("$.description", is(book.getDescription())))
            .andExpect(jsonPath("$.bookType", is(book.getBookType().toString())))
            .andExpect(jsonPath("$.author.name", is(book.getAuthor().getName())))
            .andExpect(jsonPath("$.author.lastName", is(book.getAuthor().getLastName())))
            .andExpect(jsonPath("$.author.age", is(book.getAuthor().getAge())));
  }
}