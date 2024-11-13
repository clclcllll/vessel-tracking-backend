package main.service;

import main.bean.Ship;
import main.bean.ShipPosition;
import main.dao.ShipPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ShipPositionService {

    @Autowired
    private ShipPositionRepository shipPositionRepository;

    @Autowired
    private ShipService shipService;

    // 查询所有记录
    public List<ShipPosition> findAll() {
        return shipPositionRepository.findAll();
    }

    // 根据 ID 查询记录
    public ShipPosition findById(Long id) {
        return shipPositionRepository.findById(id).orElse(null);
    }

    // 根据 shipId 查询所有记录
    public List<ShipPosition> findByShipId(Long shipId) {
        return shipPositionRepository.findByShipId(shipId);
    }

    // 保存或更新记录
    public ShipPosition save(ShipPosition shipPosition) {
        return shipPositionRepository.save(shipPosition);
    }

    // 根据 ID 删除记录
    public void deleteById(Long id) {
        shipPositionRepository.deleteById(id);
    }

    // 根据船的 MMSI 查询该船的所有位置记录
    public List<ShipPosition> findPositionsByShipMmsi(String mmsi) {
        Long shipId = shipService.getShipIdByMmsi(mmsi);
        if (shipId != null) {
            return shipPositionRepository.findByShipId(shipId);
        } else {
            return Collections.emptyList();
        }
    }
}
