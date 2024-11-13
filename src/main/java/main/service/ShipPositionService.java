package main.service;

import main.bean.Ship;
import main.bean.ShipPosition ;
import main.dao.ShipPositionRepository ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ShipPositionService {

    @Autowired
    private ShipPositionRepository shipPositionRepository;

    @Autowired
    private ShipService shipService;

    // 根据 mmsi 查询所有位置记录
    public List<ShipPosition> findPositionsByShipMmsi(String mmsi) {
        Optional<Ship> ship = shipService.getShipByMmsi(mmsi);
        if (ship.isPresent()) {
            Ship ship1 = ship.get();
            Long shipId = ship1.getId();
            return shipPositionRepository.findByShipId(shipId);
        } else {
            return Collections.emptyList();
        }
    }

    // 查询所有记录
    public List<ShipPosition> findAll() {
        return shipPositionRepository.findAll();
    }

    // 根据 ID 查询记录
    public Optional<ShipPosition> findById(Long id) {
        return shipPositionRepository.findById(id);
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
}
