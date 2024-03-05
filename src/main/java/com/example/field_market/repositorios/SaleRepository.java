package com.example.field_market.repositorios;

import com.example.field_market.entidades.Sale;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, String> {
    Optional<Sale> findById(String id_sale);
}
