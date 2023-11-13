package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductBO {

    ResponseEntity<List<Product>> findAll();

    ResponseEntity<Product> findId(Long id);

    ResponseEntity<Product> save(ProductDTO entity);

    ResponseEntity<Product> update(ProductDTO entity);

    ResponseEntity<Product> delete(Long id);

    ResponseEntity<List<Product>> findAllByBrandId(Long brandId);

    ResponseEntity<List<Product>> findAllByCategoryId(Long categoryId);
}
