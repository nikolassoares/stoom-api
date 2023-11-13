package br.com.stoom.store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String sku;
    private String productName;
    private Long brandId;
    private Long priceId;
    private Long categoryId;
    private Long stock;

    @Transient
    private LocalDateTime dtCreate;

    @Transient
    private LocalDateTime dtUpdate;

    private Boolean active;
}