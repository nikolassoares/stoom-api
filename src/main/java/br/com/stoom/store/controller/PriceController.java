package br.com.stoom.store.controller;

import br.com.stoom.store.business.PriceBO;
import br.com.stoom.store.model.Price;
import br.com.stoom.store.model.dto.PriceDTO;
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
@RequestMapping("/api/price")
@RestController
public class PriceController {

    @Autowired
    private PriceBO priceService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Price>> findAll() {
        return priceService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Price> findId(@PathVariable("id") Long id) {
        return priceService.findId(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Price> save(@RequestBody PriceDTO priceDTO) {
        return priceService.save(priceDTO);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Price> update(@RequestBody PriceDTO priceDTO) {
        return priceService.update(priceDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Price> delete(@PathVariable("id") Long id) {
        return priceService.delete(id);
    }

}
