package com.example.Thawaq.Repository;
import com.example.Thawaq.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    Request findRequestById(Integer id);
}
