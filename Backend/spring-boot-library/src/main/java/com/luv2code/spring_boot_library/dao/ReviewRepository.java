package com.luv2code.spring_boot_library.dao;

import com.luv2code.spring_boot_library.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

//Enables http://localhost:8080/api/reviews/
public interface ReviewRepository extends JpaRepository<Review, Long> {
    //Enables http://localhost:8080/api/reviews/search
    //Can do http://localhost:8080/api/reviews/search/findByBookId?bookId={#}
    Page<Review> findByBookId(@RequestParam("book_id") Long bookId, Pageable pageable);

    Review findByUserEmailAndBookId(String userEmail, Long bookId);

    @Modifying
    @Query("delete from Review where bookId in :book_id")
    void deleteAllByBookId(@Param("book_id") Long bookId);
}
