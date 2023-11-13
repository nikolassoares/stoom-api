package br.com.stoom.store.controller;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Price;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.ProductDTO;
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
public class ProductControllerTest {
    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductBO service;


    @Test
    public void mustReturnAllProduct() {
        List<Product> productList = createProductList();
        ResponseEntity<List<Product>> p = new ResponseEntity<>(productList, HttpStatus.OK);
        Mockito.when(service.findAll()).thenReturn(p);
        ResponseEntity<List<Product>> result = controller.findAll();
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void mustReturnAllProductByBrand() {
        List<Product> productList = createProductList();
        ResponseEntity<List<Product>> p = new ResponseEntity<>(productList, HttpStatus.OK);
        Mockito.when(service.findAllByBrandId(1L)).thenReturn(p);
        ResponseEntity<List<Product>> result = controller.findAllByBrandId(1L);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void mustReturnAllProductByCategory() {
        List<Product> productList = createProductList();
        ResponseEntity<List<Product>> p = new ResponseEntity<>(productList, HttpStatus.OK);
        Mockito.when(service.findAllByCategoryId(1L)).thenReturn(p);
        ResponseEntity<List<Product>> result = controller.findAllByCategoryId(1L);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void mustReturnProductById() {
        Product product = createProductList().get(0);
        ResponseEntity<Product> p = new ResponseEntity<>(product, HttpStatus.OK);
        Mockito.when(service.findId(1L)).thenReturn(p);
        ResponseEntity<Product> result = controller.findId(1L);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    public void mustSaveProduct() {
        ProductDTO product = ProductDTO.builder()
                .productName("Product Sample 1")
                .active(true)
                .build();
        Product prod = convertDTOToEntity(product);
        ResponseEntity<Product> p = new ResponseEntity<>(prod, HttpStatus.CREATED);
        Mockito.when(service.save(product)).thenReturn(p);
        ResponseEntity<Product> result = controller.save(product);
        Assert.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }


    @Test
    public void mustUpdateProduct() {
        ProductDTO product = ProductDTO.builder()
                .id(1L)
                .productName("Product Sample 1")
                .active(true)
                .build();
        Product prod = convertDTOToEntity(product);
        ResponseEntity<Product> p = new ResponseEntity<>(prod, HttpStatus.OK);
        Mockito.when(service.update(product)).thenReturn(p);
        ResponseEntity<Product> result = controller.update(product);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void mustDeleteProduct() {
        ResponseEntity<Product> p = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(service.delete(1L)).thenReturn(p);
        ResponseEntity<Product> result = controller.delete(1L);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    private List<Product> createProductList() {
        List<Product> productList = new ArrayList<Product>();
        productList.add(Product.builder()
                .id(1L)
                .productName("Product Sample 1")
                .build());
        return productList;
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
