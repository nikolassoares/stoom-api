package br.com.stoom.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="brand")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_sequence")
    @SequenceGenerator(name = "brand_sequence", sequenceName = "brand_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "brandName")
    private String brandName;

    @Column(name = "dtCreate")
    private LocalDateTime dtCreate;

    @Column(name = "dtUpdate")
    private LocalDateTime dtUpdate;

    @Column(name = "active")
    private Boolean active;
}