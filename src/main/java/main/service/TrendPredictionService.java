package main.service;

import main.bean.TrendPrediction;
import main.dao.TrendPredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrendPredictionService {

    @Autowired
    private TrendPredictionRepository trendPredictionRepository;

    // 查询所有记录
    public List<TrendPrediction> findAll() {
        return trendPredictionRepository.findAll();
    }

    // 根据 ID 查询记录
    public TrendPrediction findById(Long id) {
        return trendPredictionRepository.findById(id).orElse(null);
    }

    // 根据 shipId 查询记录
    public List<TrendPrediction> findByShipId(Long shipId) {
        return trendPredictionRepository.findByShipId(shipId);
    }

    // 保存或更新记录
    public TrendPrediction save(TrendPrediction trendPrediction) {
        return trendPredictionRepository.save(trendPrediction);
    }

    // 根据 ID 删除记录
    public void deleteById(Long id) {
        trendPredictionRepository.deleteById(id);
    }
}
