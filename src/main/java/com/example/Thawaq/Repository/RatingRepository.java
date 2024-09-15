package com.example.Thawaq.Repository;

import com.example.Thawaq.Model.Expert;
import com.example.Thawaq.Model.Rating;
import com.example.Thawaq.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    Rating findRatingById(Integer id);
    ///v2
    List<Rating> findRatingByStore(Store store);
    ///v2
    List<Rating> findRatingByExpert(Expert expert);
}