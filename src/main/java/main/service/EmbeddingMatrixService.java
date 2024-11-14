package main.service;

import main.bean.EmbeddingMatrix;
import main.dao.EmbeddingMatrixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmbeddingMatrixService {

    @Autowired
    private EmbeddingMatrixRepository embeddingMatrixRepository;

    // 查询全部记录
    public List<EmbeddingMatrix> findAll() {
        return embeddingMatrixRepository.findAll();
    }

    // 根据 ID 查询记录
    public EmbeddingMatrix findById(Long id) {
        return embeddingMatrixRepository.findById(id).orElse(null);
    }

    // 根据 shipId 查询记录
    public List<EmbeddingMatrix> findByShipId(Long shipId) {
        return embeddingMatrixRepository.findByShipId(shipId);
    }

    // 保存或更新记录
    public EmbeddingMatrix save(EmbeddingMatrix embeddingMatrix) {
        return embeddingMatrixRepository.save(embeddingMatrix);
    }

    // 根据 ID 删除记录
    public void deleteById(Long id) {
        embeddingMatrixRepository.deleteById(id);
    }
}
