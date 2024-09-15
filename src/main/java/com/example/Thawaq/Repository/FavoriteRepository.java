package com.example.Thawaq.Repository;

import com.example.Thawaq.Model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    Favorite findFavoriteById(Integer id);
}