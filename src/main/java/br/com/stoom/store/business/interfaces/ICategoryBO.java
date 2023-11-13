package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICategoryBO {

    ResponseEntity<List<Category>> findAll();

    ResponseEntity<Category> findId(Long id);

    ResponseEntity<Category> save(CategoryDTO entity);

    ResponseEntity<Category> update(CategoryDTO entity);

    ResponseEntity<Category> delete(Long id);
}
