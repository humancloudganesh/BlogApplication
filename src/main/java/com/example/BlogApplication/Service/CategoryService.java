package com.example.BlogApplication.Service;

import com.example.BlogApplication.Payload.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CategoryService {

    CategoryDto CreateCategory(CategoryDto categoryDto);

    CategoryDto UpdateCategory(CategoryDto categoryDto,long categoryId);

    CategoryDto GetCategoryById(long categoryId);

   List<CategoryDto> GetALL();

    void DeleteCategory(long categoryId);
}
