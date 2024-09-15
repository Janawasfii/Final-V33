package com.example.Thawaq.Repository;

import com.example.Thawaq.Model.Branch;
import com.example.Thawaq.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Integer> {

    Branch findBranchById(Integer id);
    List<Branch> findBranchByStore(Store store);
}