package br.com.stoom.store.bo;

import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.dto.CategoryDTO;
import br.com.stoom.store.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryBOTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryBO service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mustReturnListSuccessfully() {
        List<Category> categoryList = createCategoryList();
        when(repository.findAll()).thenReturn(categoryList);

        ResponseEntity<List<Category>> result = service.findAll();
        assertEquals(1, Objects.requireNonNull(result.getBody()).size());
    }

    @Test
    public void mustReturnListEmpty() {
        List<Category> categoryList = new ArrayList<>();
        when(repository.findAll()).thenReturn(categoryList);

        ResponseEntity<List<Category>> result = service.findAll();
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void mustReturnCategoryByIdEmpty() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Category> result = service.findId(1L);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void mustReturnCategoryById() {
        Category category = Category.builder()
                .id(1L)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(category));
        ResponseEntity<Category> result = service.findId(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    public void mustSaveCategory() {
        CategoryDTO category = CategoryDTO.builder()
                .categoryName("Category Sample 1")
                .active(true)
                .build();
        Category p = convertDTOToEntity(category);
        when(repository.save(p)).thenReturn(p);
        ResponseEntity<Category> result = service.save(category);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void mustUpdateCategory() {
        CategoryDTO category = CategoryDTO.builder()
                .id(1L)
                .categoryName("Category Sample 1")
                .active(true)
                .build();
        Category p = convertDTOToEntity(category);
        when(repository.findById(1L)).thenReturn(Optional.of(p));
        when(repository.save(p)).thenReturn(p);
        ResponseEntity<Category> result = service.update(category);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void removeCategory() {
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
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