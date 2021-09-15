package com.example.quizapp.repo;

import com.example.quizapp.model.Poll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollRepository extends JpaRepository<Poll,Long> {
    Page<Poll> findByCreatedBy(Long id, Pageable pageable);

    List<Poll> findByIdIn(List<Long> pollIds, Sort sort);

    long countByCreatedBy(Long id);
}
