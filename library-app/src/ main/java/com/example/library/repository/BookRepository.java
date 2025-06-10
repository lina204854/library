package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCaseOrPublisherContainingIgnoreCaseOrStudentNameContainingIgnoreCase(String title, String publisher, String studentName);

    @Query("SELECT b.issueDate, COUNT(b) FROM Book b WHERE b.issueDate IS NOT NULL GROUP BY b.issueDate ORDER BY b.issueDate ASC")
    List<Object[]> countBooksByIssueDate();
}
