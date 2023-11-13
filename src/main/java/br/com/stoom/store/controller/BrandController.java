package br.com.stoom.store.controller;

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.dto.BrandDTO;
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
@RequestMapping("/api/brand")
@RestController
public class BrandController {

    @Autowired
    private BrandBO brandService;


    @GetMapping(value = "/")
    public ResponseEntity<List<Brand>> findAll() {
        return brandService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Brand> findId(@PathVariable("id") Long id) {
        return brandService.findId(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Brand> save(@RequestBody BrandDTO brandDTO) {
        return brandService.save(brandDTO);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Brand> update(@RequestBody BrandDTO brandDTO) {
        return brandService.update(brandDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Brand> delete(@PathVariable("id") Long id) {
        return brandService.delete(id);
    }

}
