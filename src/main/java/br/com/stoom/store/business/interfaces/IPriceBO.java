package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Price;
import br.com.stoom.store.model.dto.PriceDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPriceBO {

    ResponseEntity<List<Price>> findAll();

    ResponseEntity<Price> findId(Long id);

    ResponseEntity<Price> save(PriceDTO entity);

    ResponseEntity<Price> update(PriceDTO entity);

    ResponseEntity<Price> delete(Long id);
}
