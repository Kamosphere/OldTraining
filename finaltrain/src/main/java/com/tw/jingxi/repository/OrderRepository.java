package com.tw.jingxi.repository;

import com.tw.jingxi.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {
    @Transactional
    UserOrder save(UserOrder order);

    UserOrder findUserOrderById(Long id);

    @Modifying
    @Transactional
    @Query("update UserOrder u set u.status = ?2, u.paidTime = ?3 where u.id = ?1")
    int updateOrderStatusToPaid(Long id, String status, String paidTime);

    @Modifying
    @Transactional
    @Query("update UserOrder u set u.status = ?2, u.withdrawnTime = ?3 where u.id = ?1")
    int updateOrderStatusToWithdrawn(Long id, String status, String withdrawnTime);

    @Modifying
    @Transactional
    @Query("update UserOrder u set u.status = ?2, u.finishTime = ?3 where u.id = ?1")
    int updateOrderStatusToFinished(Long id, String status, String finishTime);

    List<UserOrder> findByUserId(Long userId);

}