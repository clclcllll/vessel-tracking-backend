package main.dao;

import main.bean.TrendPrediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrendPredictionRepository extends JpaRepository<TrendPrediction, Long> {
    // 根据 shipId 查询记录
    List<TrendPrediction> findByShipId(Long shipId);
}
