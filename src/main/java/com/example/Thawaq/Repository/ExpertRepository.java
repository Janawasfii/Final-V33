package com.example.Thawaq.Repository;

import com.example.Thawaq.Model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Integer> {
    Expert findExpertById(Integer id);
}