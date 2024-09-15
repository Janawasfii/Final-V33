package com.example.Thawaq.Service;


import com.example.Thawaq.Api.ApiException;
import com.example.Thawaq.Model.Category;
import com.example.Thawaq.Model.Menu;
import com.example.Thawaq.Repository.CategoryRepository;
import com.example.Thawaq.Repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;

    public List<Category> getAllCategories() {
        if (categoryRepository.findAll().isEmpty()) {
            throw new ApiException("Database is empty");
        }
        return categoryRepository.findAll();

    }
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
    public void updateCategory(Category category,Integer id) {
        Category updatedCategory = categoryRepository.findCategoryById(id);
        if (updatedCategory == null) {
            throw new ApiException("Category not found");
        }
        updatedCategory.setName(category.getName());
        updatedCategory.setDescription(category.getDescription());
        categoryRepository.save(updatedCategory);
    }
    public void deleteCategory(Integer id) {
        Category deletedCategory = categoryRepository.findCategoryById(id);
        if (deletedCategory == null) {
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(deletedCategory);
    }

    //Discount by category name (Jana) v2
    public void applyDiscountToCategoryByName(String categoryName,Integer branchId, double discountPercentage) {
        Category category = categoryRepository.findCategoryByName(categoryName);
        Menu m = menuRepository.findMenuByCategoryAndBranch(categoryName,branchId);
        if (category == null) {
            throw new ApiException("Category with name: " + categoryName + " not found");}
        if (discountPercentage <= 0 || discountPercentage > 100) {
            throw new ApiException("Invalid discount percentage !");}
        if(m==null){
            throw new ApiException("Menu not found");}
        for (Menu menu : category.getMenus()) {
            double discountedPrice = menu.getPrice() - (menu.getPrice() * discountPercentage / 100);
            menu.setPrice(discountedPrice);}
        categoryRepository.save(category);}


}