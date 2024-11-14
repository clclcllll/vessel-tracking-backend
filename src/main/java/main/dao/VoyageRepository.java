package main.dao;

import main.bean.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Long> {
    // 根据 shipId 查询航程信息
    Voyage findByShipId(Long shipId);

    // 根据 arrivalPortId 查询航程信息
    List<Voyage> findByArrivalPortId(Integer arrivalPortId);

    // 根据 departurePortId 查询航程信息
    List<Voyage> findByDeparturePortId(Integer departurePortId);
}
