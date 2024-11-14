package main.dao;

import main.bean.EmbeddingMatrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmbeddingMatrixRepository extends JpaRepository<EmbeddingMatrix, Long> {
    // 根据 shipId 查询记录
    List<EmbeddingMatrix> findByShipId(Long shipId);
}
