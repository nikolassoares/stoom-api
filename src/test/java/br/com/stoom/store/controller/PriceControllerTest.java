package br.com.stoom.store.controller;

import br.com.stoom.store.business.PriceBO;
import br.com.stoom.store.model.Price;
import br.com.stoom.store.model.dto.PriceDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class PriceControllerTest {
    @InjectMocks
    private PriceController controller;

    @Mock
    private PriceBO service;


    @Test
    public void mustReturnAllPrice() {
        List<Price> priceList = createPriceList();
        ResponseEntity<List<Price>> p = new ResponseEntity<>(priceList, HttpStatus.OK);
        Mockito.when(service.findAll()).thenReturn(p);
        ResponseEntity<List<Price>> result = controller.findAll();
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void mustReturnPriceById() {
        Price price = createPriceList().get(0);
        ResponseEntity<Price> p = new ResponseEntity<>(price, HttpStatus.OK);
        Mockito.when(service.findId(1L)).thenReturn(p);
        ResponseEntity<Price> result = controller.findId(1L);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    public void mustSavePrice() {
        PriceDTO price = PriceDTO.builder()
                .priceValue(10.0)
                .active(true)
                .build();
        Price prod = convertDTOToEntity(price);
        ResponseEntity<Price> p = new ResponseEntity<>(prod, HttpStatus.CREATED);
        Mockito.when(service.save(price)).thenReturn(p);
        ResponseEntity<Price> result = controller.save(price);
        Assert.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }


    @Test
    public void mustUpdatePrice() {
        PriceDTO price = PriceDTO.builder()
                .id(1L)
                .priceValue(10.0)
                .active(true)
                .build();
        Price prod = convertDTOToEntity(price);
        ResponseEntity<Price> p = new ResponseEntity<>(prod, HttpStatus.OK);
        Mockito.when(service.update(price)).thenReturn(p);
        ResponseEntity<Price> result = controller.update(price);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void mustDeletePrice() {
        ResponseEntity<Price> p = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(service.delete(1L)).thenReturn(p);
        ResponseEntity<Price> result = controller.delete(1L);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    private List<Price> createPriceList() {
        List<Price> priceList = new ArrayList<Price>();
        priceList.add(Price.builder()
                .id(1L)
                .priceValue(10.0)
                .build());
        return priceList;
    }

    private Price convertDTOToEntity(PriceDTO price) {
        Price p = new Price();
        p.setPriceValue(price.getPriceValue());
        p.setActive(price.getActive());
        return p;
    }

}
