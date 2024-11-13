package main.dao;

import main.bean.ShipType ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipTypeRepository extends JpaRepository<ShipType, Integer> {
    Optional<ShipType> findByTypeCode(Integer typeCode);
}
