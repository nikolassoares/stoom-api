package br.com.stoom.store.controller;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.ProductDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/api/product")
@RestController
public class ProductController {

    @Autowired
    private ProductBO productService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Product>> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findId(@PathVariable("id") Long id) {
        return productService.findId(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Product> save(@RequestBody ProductDTO product) {
        return productService.save(product);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Product> update(@RequestBody ProductDTO product) {
        return productService.update(product);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Long id) {
        return productService.delete(id);
    }

    @GetMapping(value = "/brand")
    public ResponseEntity<List<Product>> findAllByBrandId(@RequestParam("brandId") Long brandId) {
        return productService.findAllByBrandId(brandId);
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<Product>> findAllByCategoryId(@RequestParam("categoryId") Long categoryId) {
        return productService.findAllByCategoryId(categoryId);
    }

}
