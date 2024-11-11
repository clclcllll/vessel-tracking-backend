package main.dao;

import main.bean.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    // 通过 MMSI 查找船舶
    Optional<Ship> findByMmsi(String mmsi);

    // 通过 MMSI 查找船舶 ID
    @Query("SELECT s.id FROM Ship s WHERE s.mmsi = :mmsi")
    Optional<Long> findIdByMmsi(String mmsi);
}
