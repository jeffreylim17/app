package com.upwork.app.repository;

import com.upwork.app.model.dao.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

  @Query(value = "SELECT * FROM ORDERS A, USER B WHERE A.USER_ID = B.ID AND B.ID=:userId", nativeQuery = true)
  List<OrderEntity> findAllByUserId(@Param("userId") Long userId);

}
