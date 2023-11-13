package br.com.stoom.store.controller;

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.dto.BrandDTO;
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
public class BrandControllerTest {
    @InjectMocks
    private BrandController controller;

    @Mock
    private BrandBO service;


    @Test
    public void mustReturnAllBrand() {
        List<Brand> brandList = createBrandList();
        ResponseEntity<List<Brand>> p = new ResponseEntity<>(brandList, HttpStatus.OK);
        Mockito.when(service.findAll()).thenReturn(p);
        ResponseEntity<List<Brand>> result = controller.findAll();
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void mustReturnBrandById() {
        Brand brand = createBrandList().get(0);
        ResponseEntity<Brand> p = new ResponseEntity<>(brand, HttpStatus.OK);
        Mockito.when(service.findId(1L)).thenReturn(p);
        ResponseEntity<Brand> result = controller.findId(1L);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    public void mustSaveBrand() {
        BrandDTO brand = BrandDTO.builder()
                .brandName("Brand Sample 1")
                .active(true)
                .build();
        Brand prod = convertDTOToEntity(brand);
        ResponseEntity<Brand> p = new ResponseEntity<>(prod, HttpStatus.CREATED);
        Mockito.when(service.save(brand)).thenReturn(p);
        ResponseEntity<Brand> result = controller.save(brand);
        Assert.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }


    @Test
    public void mustUpdateBrand() {
        BrandDTO brand = BrandDTO.builder()
                .id(1L)
                .brandName("Brand Sample 1")
                .active(true)
                .build();
        Brand prod = convertDTOToEntity(brand);
        ResponseEntity<Brand> p = new ResponseEntity<>(prod, HttpStatus.OK);
        Mockito.when(service.update(brand)).thenReturn(p);
        ResponseEntity<Brand> result = controller.update(brand);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void mustDeleteBrand() {
        ResponseEntity<Brand> p = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(service.delete(1L)).thenReturn(p);
        ResponseEntity<Brand> result = controller.delete(1L);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
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
