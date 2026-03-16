package com.luv2code.spring_boot_library.dao;

import com.luv2code.spring_boot_library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface BookRepository extends JpaRepository<Book, Long> {
    //@RequestParam allows backend read query url values. Ex: /api/books?page=0&size=10
    //Pageable implements pagination. Page and Pageable are provided by Spring Boot

    //The Json object returned has a "_links:" which has all endpoints related to the current response
    //findByTitleContaining returns a new link:
    //"search": {
    //            "href": "http://localhost:8080/api/books/search"
    //        }
    //which has its own link:
    // "findByTitleContaining": {
    //            "href": "http://localhost:8080/api/books/search/findByTitleContaining{?title,page,size,sort}",
    //            "templated": true
    //        }
    //Can do something like http://localhost:8080/api/books/search/findByTitleContaining?title=guru
    //to return "_embedded" JSON that holds array of all books with title containing "guru"
    //as well as the "page" data with size, totalElements, totalPages, and number
    Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);

    //now http://localhost:8080/api/books/search has a
    //"findByCategory": {
    //            "href": "http://localhost:8080/api/books/search/findByCategory{?category,page,size,sort}",
    //            "templated": true
    //        }
    //Can now do something like: http://localhost:8080/api/books/search/findByCategory?category=fe
    Page<Book> findByCategory(@RequestParam("category") String category, Pageable pageable);
}
