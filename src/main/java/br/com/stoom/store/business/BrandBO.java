package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.dto.BrandDTO;
import br.com.stoom.store.model.dto.ProductDTO;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BrandBO implements IBrandBO {

    @Autowired
    private BrandRepository brandRepository;


    @Override
    public ResponseEntity<List<Brand>> findAll() {
        try {
            List<Brand> b = brandRepository.findAll();

            if (b.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(b, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Brand> findId(Long id) {
        try {
            Optional<Brand> b = brandRepository.findById(id);
            return b.map(brand -> new ResponseEntity<>(brand, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Brand> save(BrandDTO brand) {
        try {

            Brand b = convertDTOToEntity(brand);
            b.setDtCreate(LocalDateTime.now());
            b.setDtUpdate(LocalDateTime.now());
            b.setActive(true);
            b = brandRepository.save(b);

            return new ResponseEntity<>(b, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Brand> update(BrandDTO brand) {
        try {

            Optional<Brand> b = brandRepository.findById(brand.getId());
            if (!b.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Brand bnd = convertDTOToEntity(brand);
            bnd.setDtUpdate(LocalDateTime.now());

            brandRepository.save(bnd);
            return new ResponseEntity<>(bnd, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Brand> delete(Long id) {
        try {
            brandRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private Brand convertDTOToEntity(BrandDTO brand) {
        Brand b = new Brand();
        b.setBrandName(brand.getBrandName());
        b.setActive(brand.getActive());
        return b;
    }

}