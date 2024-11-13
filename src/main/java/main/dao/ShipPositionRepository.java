package main.dao;

import main.bean.Ship;
import main.bean.ShipPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShipPositionRepository extends JpaRepository<ShipPosition, Long> {
    // 根据 shipId 查询所有记录
    List<ShipPosition> findByShipId(Long shipId);
}
