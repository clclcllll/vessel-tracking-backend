package main.service;

import main.bean.Ship;
import main.dao.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {

    private final ShipRepository shipRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    // 获取所有船舶列表
    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }

    // 通过船舶 ID 查找船舶
    public Ship getShipById(Long id) {
        return shipRepository.findById(id).orElse(null);
    }

    // 通过船舶 MMSI 查找船舶
    public Ship getShipByMmsi(String mmsi) {
        return shipRepository.findByMmsi(mmsi).orElse(null);
    }

    // 通过 MMSI 查找船舶的 ID
    public Long getShipIdByMmsi(String mmsi) {
        Ship ship = shipRepository.findByMmsi(mmsi).orElse(null);
        return ship != null ? ship.getId() : null;
    }

    // 添加新船舶
    public Ship addShip(Ship ship) {
        return shipRepository.save(ship);
    }

    // 更新船舶信息
    public Ship updateShip(Ship ship) {
        return shipRepository.save(ship);
    }

    // 删除船舶
    public void deleteShip(Long id) {
        shipRepository.deleteById(id);
    }
}
