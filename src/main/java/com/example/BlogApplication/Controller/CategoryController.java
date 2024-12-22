package com.example.BlogApplication.Controller;

import com.example.BlogApplication.Entity.Category;
import com.example.BlogApplication.Payload.CategoryDto;
import com.example.BlogApplication.Responce.ApiResponse;
import com.example.BlogApplication.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }
    @PostMapping("/save")
    public ResponseEntity<CategoryDto> CreateCategory(@Valid @RequestBody CategoryDto category)
    {
        CategoryDto categoryDto = categoryService.CreateCategory(category);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<CategoryDto> UpdateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable long categoryId)
    {
        CategoryDto categoryDto1 = categoryService.UpdateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }
    @GetMapping("/GetById")
    public ResponseEntity<CategoryDto> GetByCategoryId(@PathVariable long categoryId)
    {
        CategoryDto categoryDto = categoryService.GetCategoryById(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.FOUND);
    }
    @GetMapping("/All")
    public ResponseEntity<List<CategoryDto>> GetAllCategory()
    {
        List<CategoryDto> categoryDtos = categoryService.GetALL();
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> DeleteCategory(long categoryId)
    {
        categoryService.DeleteCategory(categoryId);
        return ResponseEntity.ok("DELETED");
    }

}
