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
public class PriceDTO {
    private Long id;
    private Double priceValue;

    @Transient
    private LocalDateTime dtCreate;

    @Transient
    private LocalDateTime dtUpdate;

    private Boolean active;
}