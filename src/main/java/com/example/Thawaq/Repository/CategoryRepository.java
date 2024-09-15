package com.example.Thawaq.Repository;

import com.example.Thawaq.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryById(Integer id);
    //Discount by category name (Jana) v2
    Category findCategoryByName(String name);


}