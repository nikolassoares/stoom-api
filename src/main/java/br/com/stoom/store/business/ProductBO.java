package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Price;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.ProductDTO;
import br.com.stoom.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductBO implements IProductBO {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public ResponseEntity<List<Product>> findAll() {
        try {
            List<Product> p = productRepository.findAll();

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
    public ResponseEntity<Product> findId(Long id) {
        try {
            Optional<Product> p = productRepository.findById(id);
            return p.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Product> save(ProductDTO product) {
        try {

            Product p = convertDTOToEntity(product);
            p.setDtCreate(LocalDateTime.now());
            p.setDtUpdate(LocalDateTime.now());
            p.setActive(true);
            p = productRepository.save(p);

            return new ResponseEntity<>(p, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Product> update(ProductDTO product) {
        try {

            Optional<Product> p = productRepository.findById(product.getId());
            if (!p.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Product prod = convertDTOToEntity(product);
            prod.setDtUpdate(LocalDateTime.now());

            productRepository.save(prod);
            return new ResponseEntity<>(prod, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Product> delete(Long id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Product>> findAllByBrandId(Long brandId) {
        try {
            List<Product> p = productRepository.findAllByBrandIdId(brandId);

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
    public ResponseEntity<List<Product>> findAllByCategoryId(Long categoryId) {
        try {
            List<Product> p = productRepository.findAllByCategoryIdId(categoryId);

            if (p.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(p, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private Product convertDTOToEntity(ProductDTO product) {
        Product p = new Product();
        p.setBrandId(Brand.builder().id(product.getBrandId()).build());
        p.setCategoryId(Category.builder().id(product.getCategoryId()).build());
        p.setPriceId(Price.builder().id(product.getPriceId()).build());
        p.setProductName(product.getProductName());
        p.setSku(product.getSku());
        p.setStock(product.getStock());
        p.setActive(product.getActive());
        return p;
    }

}