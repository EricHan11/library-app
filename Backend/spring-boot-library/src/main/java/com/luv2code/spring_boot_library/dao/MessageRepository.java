package com.luv2code.spring_boot_library.dao;

import com.luv2code.spring_boot_library.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface MessageRepository extends JpaRepository<Message, Long> {
    //used by normal users. Displays all open and closed questions for a user
    Page<Message> findByUserEmail(@RequestParam("user_email") String userEmail, Pageable pageable);

    //for admins. Only want to see open questions from all users
    Page<Message> findByClosed(@RequestParam("closed") boolean closed, Pageable pageable);
}
