package br.com.stoom.store.bo;

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.dto.BrandDTO;
import br.com.stoom.store.repository.BrandRepository;
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
public class BrandBOTest {

    @Mock
    private BrandRepository repository;

    @InjectMocks
    private BrandBO service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mustReturnListSuccessfully() {
        List<Brand> brandList = createBrandList();
        when(repository.findAll()).thenReturn(brandList);

        ResponseEntity<List<Brand>> result = service.findAll();
        assertEquals(1, Objects.requireNonNull(result.getBody()).size());
    }

    @Test
    public void mustReturnListEmpty() {
        List<Brand> brandList = new ArrayList<>();
        when(repository.findAll()).thenReturn(brandList);

        ResponseEntity<List<Brand>> result = service.findAll();
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void mustReturnBrandByIdEmpty() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Brand> result = service.findId(1L);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void mustReturnBrandById() {
        Brand brand = Brand.builder()
                .id(1L)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(brand));
        ResponseEntity<Brand> result = service.findId(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    public void mustSaveBrand() {
        BrandDTO brand = BrandDTO.builder()
                .brandName("Brand Sample 1")
                .active(true)
                .build();
        Brand p = convertDTOToEntity(brand);
        when(repository.save(p)).thenReturn(p);
        ResponseEntity<Brand> result = service.save(brand);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void mustUpdateBrand() {
        BrandDTO brand = BrandDTO.builder()
                .id(1L)
                .brandName("Brand Sample 1")
                .active(true)
                .build();
        Brand p = convertDTOToEntity(brand);
        when(repository.findById(1L)).thenReturn(Optional.of(p));
        when(repository.save(p)).thenReturn(p);
        ResponseEntity<Brand> result = service.update(brand);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void removeBrand() {
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }


    private List<Brand> createBrandList() {
        List<Brand> brandList = new ArrayList<Brand>();
        brandList.add(Brand.builder()
                .id(1L)
                .brandName("Brand Sample 1")
                .build());
        return brandList;
    }

    private Brand convertDTOToEntity(BrandDTO brand) {
        Brand b = new Brand();
        b.setBrandName(brand.getBrandName());
        b.setActive(brand.getActive());
        return b;
    }

}