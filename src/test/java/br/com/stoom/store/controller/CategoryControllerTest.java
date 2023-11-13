package br.com.stoom.store.controller;

import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.dto.CategoryDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class CategoryControllerTest {
    @InjectMocks
    private CategoryController controller;

    @Mock
    private CategoryBO service;


    @Test
    public void mustReturnAllCategory() {
        List<Category> categoryList = createCategoryList();
        ResponseEntity<List<Category>> p = new ResponseEntity<>(categoryList, HttpStatus.OK);
        Mockito.when(service.findAll()).thenReturn(p);
        ResponseEntity<List<Category>> result = controller.findAll();
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void mustReturnCategoryById() {
        Category category = createCategoryList().get(0);
        ResponseEntity<Category> p = new ResponseEntity<>(category, HttpStatus.OK);
        Mockito.when(service.findId(1L)).thenReturn(p);
        ResponseEntity<Category> result = controller.findId(1L);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    public void mustSaveCategory() {
        CategoryDTO category = CategoryDTO.builder()
                .categoryName("Category Sample 1")
                .active(true)
                .build();
        Category prod = convertDTOToEntity(category);
        ResponseEntity<Category> p = new ResponseEntity<>(prod, HttpStatus.CREATED);
        Mockito.when(service.save(category)).thenReturn(p);
        ResponseEntity<Category> result = controller.save(category);
        Assert.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }


    @Test
    public void mustUpdateCategory() {
        CategoryDTO category = CategoryDTO.builder()
                .id(1L)
                .categoryName("Category Sample 1")
                .active(true)
                .build();
        Category prod = convertDTOToEntity(category);
        ResponseEntity<Category> p = new ResponseEntity<>(prod, HttpStatus.OK);
        Mockito.when(service.update(category)).thenReturn(p);
        ResponseEntity<Category> result = controller.update(category);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void mustDeleteCategory() {
        ResponseEntity<Category> p = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(service.delete(1L)).thenReturn(p);
        ResponseEntity<Category> result = controller.delete(1L);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    private List<Category> createCategoryList() {
        List<Category> categoryList = new ArrayList<Category>();
        categoryList.add(Category.builder()
                .id(1L)
                .categoryName("Category Sample 1")
                .build());
        return categoryList;
    }

    private Category convertDTOToEntity(CategoryDTO category) {
        Category b = new Category();
        b.setCategoryName(category.getCategoryName());
        b.setDescription(category.getDescription());
        b.setActive(category.getActive());
        return b;
    }

}
