package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.dto.CategoryDTO;
import br.com.stoom.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryBO implements ICategoryBO {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<List<Category>> findAll() {
        try {
            List<Category> c = categoryRepository.findAll();

            if (c.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(c, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Category> findId(Long id) {
        try {
            Optional<Category> c = categoryRepository.findById(id);
            return c.map(category -> new ResponseEntity<>(category, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Category> save(CategoryDTO category) {
        try {

            Category c = convertDTOToEntity(category);
            c.setDtCreate(LocalDateTime.now());
            c.setDtUpdate(LocalDateTime.now());
            c.setActive(true);
            c = categoryRepository.save(c);

            return new ResponseEntity<>(c, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Category> update(CategoryDTO category) {
        try {

            Optional<Category> c = categoryRepository.findById(category.getId());
            if (!c.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Category ctg = convertDTOToEntity(category);
            ctg.setDtUpdate(LocalDateTime.now());

            categoryRepository.save(ctg);
            return new ResponseEntity<>(ctg, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Category> delete(Long id) {
        try {
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Category convertDTOToEntity(CategoryDTO category) {
        Category b = new Category();
        b.setCategoryName(category.getCategoryName());
        b.setDescription(category.getDescription());
        b.setActive(category.getActive());
        return b;
    }

}