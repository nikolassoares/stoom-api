package br.com.stoom.store.repository;

import br.com.stoom.store.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}