package com.learn.springTest.service;

import com.learn.springTest.entity.Book;
import com.learn.springTest.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void shouldCreateBook() {
        Book book =
                new Book(null,
                        "Spring Boot",
                        "Ashik",
                        500.0);

        Book savedBook =
                new Book(1L,
                        "Spring Boot",
                        "Ashik",
                        500.0);

        when(bookRepository.save(book)).thenReturn(savedBook);
        Book result = bookService.createBook(book);
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Spring Boot");
        assertThat(result.getAuthor()).isEqualTo("Ashik");
        assertThat(result.getPrice()).isEqualTo(500.0);
    }


    @Test
    void shouldReturnBookWhenIdExists() {

        Book book =
                new Book(
                        1L,
                        "Spring Boot",
                        "Ashik",
                        500.0
                );

        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(book));

        Book result =
                bookService.getBook(1L);

        assertThat(result.getTitle())
                .isEqualTo("Spring Boot");

        verify(bookRepository)
                .findById(1L);
    }
}
