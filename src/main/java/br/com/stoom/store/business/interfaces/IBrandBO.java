package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.dto.BrandDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBrandBO {

    ResponseEntity<List<Brand>> findAll();

    ResponseEntity<Brand> findId(Long id);

    ResponseEntity<Brand> save(BrandDTO entity);

    ResponseEntity<Brand> update(BrandDTO entity);

    ResponseEntity<Brand> delete(Long id);
}
