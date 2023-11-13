package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IPriceBO;
import br.com.stoom.store.model.Price;
import br.com.stoom.store.model.dto.PriceDTO;
import br.com.stoom.store.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceBO implements IPriceBO {

    @Autowired
    private PriceRepository priceRepository;


    @Override
    public ResponseEntity<List<Price>> findAll() {
        try {
            List<Price> p = priceRepository.findAll();

            if (p.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(p, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Price> findId(Long id) {
        try {
            Optional<Price> p = priceRepository.findById(id);
            return p.map(price -> new ResponseEntity<>(price, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Price> save(PriceDTO price) {
        try {

            Price p = convertDTOToEntity(price);
            p.setDtCreate(LocalDateTime.now());
            p.setDtUpdate(LocalDateTime.now());
            p.setActive(true);
            p = priceRepository.save(p);

            return new ResponseEntity<>(p, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Price> update(PriceDTO price) {
        try {

            Optional<Price> p = priceRepository.findById(price.getId());
            if (!p.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Price prc = convertDTOToEntity(price);
            prc.setDtUpdate(LocalDateTime.now());

            priceRepository.save(prc);
            return new ResponseEntity<>(prc, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Price> delete(Long id) {
        try {
            priceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private Price convertDTOToEntity(PriceDTO price) {
        Price p = new Price();
        p.setPriceValue(price.getPriceValue());
        p.setActive(price.getActive());
        return p;
    }

}