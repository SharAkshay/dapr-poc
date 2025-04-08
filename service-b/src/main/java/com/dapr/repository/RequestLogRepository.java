package com.dapr.repository;

import com.dapr.entity.RequestLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogRepository  extends JpaRepository<RequestLogEntity, Long> {
}
