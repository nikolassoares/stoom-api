package br.com.stoom.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "sku")
    private String sku;

    @Column(name = "productName")
    private String productName;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "brandId", referencedColumnName = "id")
    private Brand brandId;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "priceId", referencedColumnName = "id")
    private Price priceId;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category categoryId;

    @Column(name = "stock")
    private Long stock;

    @Column(name = "dtCreate")
    private LocalDateTime dtCreate;

    @Column(name = "dtUpdate")
    private LocalDateTime dtUpdate;

    @Column(name = "active")
    private Boolean active;
}