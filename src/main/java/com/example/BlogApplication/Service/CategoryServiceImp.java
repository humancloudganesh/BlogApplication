package com.example.BlogApplication.Service;

import com.example.BlogApplication.Entity.Category;
import com.example.BlogApplication.Exception.ResourceNotFoundException;
import com.example.BlogApplication.Payload.CategoryDto;
import com.example.BlogApplication.Repositor.CategoryRepository;
import com.example.BlogApplication.Responce.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class CategoryServiceImp implements CategoryService {


   private CategoryRepository categoryRepository;
   private ModelMapper modelMapper;

   @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
       this.modelMapper = modelMapper;
   }

    @Override
   public CategoryDto CreateCategory(CategoryDto categoryDto) {

       Category category = modelMapper.map(categoryDto, Category.class);
       categoryRepository.save(category);
      return modelMapper.map(category,CategoryDto.class);
   }
   @CachePut(value ="Category",key ="#categoryId")
   @Override
   public CategoryDto UpdateCategory(CategoryDto categoryDto,long categoryId) {
      Category updatedCategory= modelMapper.map(categoryDto, Category.class);

     Category category= categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
     category.setCategoryTitle(updatedCategory.getCategoryTitle());
     category.setCategoryDescription(updatedCategory.getCategoryDescription());
      Category updated = categoryRepository.save(category);
      return modelMapper.map(updated,CategoryDto.class) ;
   }
   @Cacheable(value ="Category",key ="#categoryId")
   @Override
   public CategoryDto GetCategoryById(long categoryId) {

       Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
       return modelMapper.map(category,CategoryDto.class);
   }
   @Cacheable(value ="Categorys")
   public List<CategoryDto> GetALL() {

       CategoryRepository categoryRepository1 = categoryRepository;
       List<Category> all = categoryRepository1.findAll();

       List<CategoryDto> collect = all.stream().map((category) -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());

       return collect;
   }
   @CacheEvict(value ="Category",key ="#categoryId")
   @Override
   public void DeleteCategory(long categoryId) {

     Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
            categoryRepository.delete(category);
   }
}
