package br.com.stoom.store.controller;

import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/api/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryBO categoryService;


    @GetMapping(value = "/")
    public ResponseEntity<List<Category>> findAll() {
        return categoryService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findId(@PathVariable("id") Long id) {
        return categoryService.findId(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Category> save(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Category> update(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.update(categoryDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") Long id) {
        return categoryService.delete(id);
    }

}
