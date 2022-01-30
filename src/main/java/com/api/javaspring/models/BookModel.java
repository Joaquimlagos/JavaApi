package com.api.javaspring.models;

import com.api.javaspring.enums.BookType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class BookModel {
@JsonProperty("title")
  private String title;
  @JsonProperty("year")
  private Integer year;
  @JsonProperty("description")
  private String description;
  @JsonProperty("author")
  private AuthorModel author;
  @JsonProperty("bookType")
  private BookType bookType;

  public BookModel() {
    super();
  }

  public BookModel(String title, Integer year, String description, AuthorModel author, BookType bookType) {
    this.title = title;
    this.year = year;
    this.description = description;
    this.author = author;
    this.bookType = bookType;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public AuthorModel getAuthor() {
    return author;
  }

  public void setAuthor(AuthorModel author) {
    this.author = author;
  }

  public BookType getBookType() {
    return bookType;
  }

  public void setBookType(BookType bookType) {
    this.bookType = bookType;
  }

  @Override
  public String toString() {
    return "BookModel{" +
            "title='" + title + '\'' +
            ", year=" + year +
            ", description='" + description + '\'' +
            ", author=" + author +
            ", bookType=" + bookType +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BookModel bookModel = (BookModel) o;
    return Objects.equals(title, bookModel.title) && Objects.equals(year, bookModel.year) && Objects.equals(description, bookModel.description) && Objects.equals(author, bookModel.author) && bookType == bookModel.bookType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, year, description, author, bookType);
  }
}
