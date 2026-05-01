package com.phc.movieflix.service;

import com.phc.movieflix.dtos.request.CategoryRequest;
import com.phc.movieflix.dtos.response.CategoryResponse;
import com.phc.movieflix.entity.Category;
import com.phc.movieflix.exceptions.ResourceNotFoundException;
import com.phc.movieflix.mapper.CategoryMapper;
import com.phc.movieflix.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Transactional
    public CategoryResponse createCategory(CategoryRequest dto) {
         Category category = categoryRepository.save(categoryMapper.toEntity(dto));
         return categoryMapper.toResponse(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> categoryMapper.toResponse(category))
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryResponse findCategoryById(Long id) {
        Category category= categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        return categoryMapper.toResponse(category);
    }

    @Transactional
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
