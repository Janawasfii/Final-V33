package com.example.Thawaq.Repository;

import com.example.Thawaq.Model.Branch;
import com.example.Thawaq.Model.Category;
import com.example.Thawaq.Model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    Menu findMenuById(Integer id);

    //Find dish by price range min and max (Jana) v2
    List<Menu> findDishesByPriceBetween(double minPrice, double maxPrice);
    List<Menu> findMenuByCategory(Category category);

    Menu findMenuByCategoryAndBranch(String categoryName, Integer branchId);
}
