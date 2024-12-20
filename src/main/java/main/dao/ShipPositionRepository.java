package main.dao;

import main.bean.Ship;
import main.bean.ShipPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShipPositionRepository extends JpaRepository<ShipPosition, Long> {
    // 根据 shipId 查询所有记录
    List<ShipPosition> findByShipId(Long shipId);

    // 根据 shipId 查询所有记录
    @Query(value = "SELECT sp.id, sp.ship_id, CAST(ST_X(sp.location) AS DECIMAL(15, 7)) AS longitude, " +
            "CAST(ST_Y(sp.location) AS DECIMAL(15, 7)) AS latitude, sp.speed, sp.course, sp.heading, sp.status, " +
            "sp.position_time, sp.create_time " +
            "FROM ship_positions sp WHERE sp.ship_id = :shipId",
            nativeQuery = true)
    List<Object[]> findByShipIdWithCoordinatesNative(@Param("shipId") Long shipId);




    // 根据 shipId 和时间范围查询记录
    List<ShipPosition> findByShipIdAndPositionTimeBetween(Long shipId, Date startTime, Date endTime);

}
