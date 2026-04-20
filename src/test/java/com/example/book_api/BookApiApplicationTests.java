package com.example.book_api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.ui.ExtendedModelMap;

import com.example.book_api.contoller.BookController;
import com.example.book_api.model.Book;
import com.example.book_api.repository.BookRepository;
import com.example.book_api.service.BookService;

@SpringBootTest
@TestPropertySource(properties = {
		"spring.datasource.url=jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.jpa.hibernate.ddl-auto=create-drop"
})
class BookApiApplicationTests {

	@Autowired
	private BookService bookService;

	@Autowired
	private BookController bookController;

	@Autowired
	private BookRepository bookRepository;

	@BeforeEach
	void setUp() {
		bookRepository.deleteAll();
		bookRepository.save(new Book(1, "Spring Boot", "Kim", 25000));
	}

	@Test
	void getBookByIdReturnsBookFromDatabase() {
		Book book = bookService.findBookById(1);

		assertThat(book.getId()).isEqualTo(1);
		assertThat(book.getName()).isEqualTo("Spring Boot");
		assertThat(book.getAuthor()).isEqualTo("Kim");
		assertThat(book.getPrice()).isEqualTo(25000);
	}

	@Test
	void getBookPutsMatchedBookOnIndexModel() {
		ExtendedModelMap model = new ExtendedModelMap();

		String viewName = bookController.getBook(1, model);

		assertThat(viewName).isEqualTo("index");
		assertThat(model.getAttribute("books")).asList().hasSize(1);
	}

}
