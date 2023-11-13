package br.com.stoom.store.bo;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Price;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.dto.ProductDTO;
import br.com.stoom.store.repository.ProductRepository;
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
public class ProductBOTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductBO service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mustReturnListSuccessfully() {
        List<Product> productList = createProductList();
        when(repository.findAll()).thenReturn(productList);

        ResponseEntity<List<Product>> result = service.findAll();
        assertEquals(1, Objects.requireNonNull(result.getBody()).size());
    }

    @Test
    public void mustReturnListEmpty() {
        List<Product> productList = new ArrayList<>();
        when(repository.findAll()).thenReturn(productList);

        ResponseEntity<List<Product>> result = service.findAll();
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void mustReturnListByBrandSuccessfully() {
        List<Product> productList = createProductList();
        when(repository.findAllByBrandIdId(1L)).thenReturn(productList);

        ResponseEntity<List<Product>> result = service.findAllByBrandId(1L);
        assertEquals(1, Objects.requireNonNull(result.getBody()).size());
    }

    @Test
    public void mustReturnListByBrandEmpty() {
        List<Product> productList = new ArrayList<>();
        when(repository.findAllByBrandIdId(1L)).thenReturn(productList);

        ResponseEntity<List<Product>> result = service.findAllByBrandId(1L);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }


    @Test
    public void mustReturnListByCategorySuccessfully() {
        List<Product> productList = createProductList();
        when(repository.findAllByCategoryIdId(1L)).thenReturn(productList);

        ResponseEntity<List<Product>> result = service.findAllByCategoryId(1L);
        assertEquals(1, Objects.requireNonNull(result.getBody()).size());
    }

    @Test
    public void mustReturnListByCategoryEmpty() {
        List<Product> productList = new ArrayList<>();
        when(repository.findAllByCategoryIdId(1L)).thenReturn(productList);

        ResponseEntity<List<Product>> result = service.findAllByCategoryId(1L);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void mustReturnProductByIdEmpty() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Product> result = service.findId(1L);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void mustReturnProductById() {
        Product product = Product.builder()
                .id(1L)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(product));
        ResponseEntity<Product> result = service.findId(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    public void mustSaveProduct() {
        ProductDTO product = ProductDTO.builder()
                .productName("Product Sample 1")
                .active(true)
                .build();
        Product p = convertDTOToEntity(product);
        when(repository.save(p)).thenReturn(p);
        ResponseEntity<Product> result = service.save(product);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void mustUpdateProduct() {
        ProductDTO product = ProductDTO.builder()
                .id(1L)
                .productName("Product Sample 1")
                .active(true)
                .build();
        Product p = convertDTOToEntity(product);
        when(repository.findById(1L)).thenReturn(Optional.of(p));
        when(repository.save(p)).thenReturn(p);
        ResponseEntity<Product> result = service.update(product);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void removeProduct() {
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
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