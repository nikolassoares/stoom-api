package br.com.stoom.store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandDTO {
    private Long id;
    private String brandName;

    @Transient
    private LocalDateTime dtCreate;

    @Transient
    private LocalDateTime dtUpdate;

    private Boolean active;
}