package com.tw.jingxi.repository;

import com.tw.jingxi.entity.ProductSnap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductSnapRepository extends JpaRepository<ProductSnap, Long> {
    @Transactional
    ProductSnap save(ProductSnap productSnap);

    List<ProductSnap> findProductSnapByOrderId(Long orderId);
}