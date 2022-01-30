package com.api.javaspring.controllers;

import com.api.javaspring.exeptions.NotFoundExeption;
import com.api.javaspring.models.BookModel;
import com.api.javaspring.services.JavaApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class JavaApiController {

  @Autowired
  private final JavaApiService javaApiService;

  public JavaApiController(JavaApiService javaApiService) {
    this.javaApiService = javaApiService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookModel createBook(@RequestBody BookModel bookModel) {
    return javaApiService.create(bookModel);
  }

  @GetMapping("/{id}")
  public BookModel getBook(@PathVariable Integer id) throws NotFoundExeption {
    return javaApiService.get(id);
  }
  @PutMapping("/{id}")
  public BookModel putBook(@PathVariable Integer id, @RequestBody BookModel bookModel){
    return javaApiService.update(id,bookModel);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBook(@PathVariable Integer id){
    javaApiService.delete(id);
  }

  @GetMapping
  public List<BookModel> listAllBooks(){
    return javaApiService.listAll();
  }
}
