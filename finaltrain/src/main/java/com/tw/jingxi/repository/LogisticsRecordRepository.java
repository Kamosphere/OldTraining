package com.tw.jingxi.repository;

import com.tw.jingxi.entity.LogisticsRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LogisticsRecordRepository extends JpaRepository<LogisticsRecord, Long> {
    @Transactional
    LogisticsRecord save(LogisticsRecord logisticsRecord);

    LogisticsRecord findLogisticsRecordByIdAndOrderId(Long id, Long orderId);

    @Modifying
    @Transactional
    @Query("update LogisticsRecord l set l.logisticsStatus = 'shipping', l.outboundTime = ?3 where l.id = ?1 and l.orderId = ?2")
    int updateLogisticsStatusWithShipping(Long id, Long orderId, String outboundTime);

    @Modifying
    @Transactional
    @Query("update LogisticsRecord l set l.logisticsStatus = 'signed', l.signedTime = ?3 where l.id = ?1 and l.orderId = ?2")
    int updateLogisticsStatusWithSigned(Long id, Long orderId, String signedTime);
}