package ru.practicum.categories.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.categories.dto.CategoryDto;
import ru.practicum.categories.dto.NewCategoryDto;
import ru.practicum.categories.mapper.CategoryMapper;
import ru.practicum.categories.model.Category;
import ru.practicum.categories.repository.CategoryRepository;
import ru.practicum.errors.exceptions.DataConflictException;
import ru.practicum.errors.exceptions.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto getCategory(Long catId) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException("Not found category with id =" + catId));
        return categoryMapper.mapToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getCategories(int from, int size) {
        List<Category> categories = categoryRepository.findAllOrderById(size, from);
        return categoryMapper.mapToListCategoryDto(categories);
    }

    @Override
    public void deleteCategory(Long catId) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException("Not found category with id =" + catId));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto createCategory(NewCategoryDto newCategoryDto) {
        Category category = categoryMapper.mapToCategory(newCategoryDto);
        checkDuplicate(category.getName());
        return categoryMapper.mapToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto updateCategory(NewCategoryDto newCategoryDto, Long catId) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException("Not found category with id =" + catId));
        if (!category.getName().equals(newCategoryDto.getName())) {
            checkDuplicate(newCategoryDto.getName());
        }
        category.setName(newCategoryDto.getName());
        categoryRepository.save(category);
        return categoryMapper.mapToCategoryDto(category);
    }

    private void checkDuplicate(String name) {
        Boolean isDuplicate = categoryRepository.existsByNameIgnoreCase(name);
        if (isDuplicate) {
            throw new DataConflictException("Category already exists");
        }
    }
}
