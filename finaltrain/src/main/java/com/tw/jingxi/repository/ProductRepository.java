package com.tw.jingxi.repository;

import com.tw.jingxi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional
    Product save(Product product);

    @Modifying
    @Transactional
    @Query("update Product p set p.name = ?2, p.description = ?3, p.price = ?4 where p.id = ?1")
    int updateById(Long id, String name, String description, Integer price);

    Product findProductById(Long id);

    List<Product> findAll();

    List<Product> findByName(String name);

    List<Product> findByNameAndDescriptionContaining(String name, String description);

}