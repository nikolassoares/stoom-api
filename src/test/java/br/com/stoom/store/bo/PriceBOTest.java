package br.com.stoom.store.bo;

import br.com.stoom.store.business.PriceBO;
import br.com.stoom.store.model.Price;
import br.com.stoom.store.model.dto.PriceDTO;
import br.com.stoom.store.repository.PriceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PriceBOTest {

    @Mock
    private PriceRepository repository;

    @InjectMocks
    private PriceBO service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mustReturnListSuccessfully() {
        List<Price> priceList = createPriceList();
        when(repository.findAll()).thenReturn(priceList);

        ResponseEntity<List<Price>> result = service.findAll();
        assertEquals(1, Objects.requireNonNull(result.getBody()).size());
    }

    @Test
    public void mustReturnListEmpty() {
        List<Price> priceList = new ArrayList<>();
        when(repository.findAll()).thenReturn(priceList);

        ResponseEntity<List<Price>> result = service.findAll();
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void mustReturnPriceByIdEmpty() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Price> result = service.findId(1L);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void mustReturnPriceById() {
        Price price = Price.builder()
                .id(1L)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(price));
        ResponseEntity<Price> result = service.findId(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    public void mustSavePrice() {
        PriceDTO price = PriceDTO.builder()
                .priceValue(10.0)
                .active(true)
                .build();
        Price p = convertDTOToEntity(price);
        when(repository.save(p)).thenReturn(p);
        ResponseEntity<Price> result = service.save(price);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void mustUpdatePrice() {
        PriceDTO price = PriceDTO.builder()
                .id(1L)
                .priceValue(10.0)
                .active(true)
                .build();
        Price p = convertDTOToEntity(price);
        when(repository.findById(1L)).thenReturn(Optional.of(p));
        when(repository.save(p)).thenReturn(p);
        ResponseEntity<Price> result = service.update(price);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void removePrice() {
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
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