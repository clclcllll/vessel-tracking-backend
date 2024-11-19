package main.service;

import main.bean.Ship;
import main.bean.ShipPosition;
import main.dao.ShipPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;

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

    //
    public List<ShipPosition> findByShipIdWithCoordinates(Long shipId) {
        List<Object[]> rawResults = shipPositionRepository.findByShipIdWithCoordinatesNative(shipId);
        return rawResults.stream().map(result -> {
            ShipPosition sp = new ShipPosition();
            sp.setId(result[0] != null ? ((Number) result[0]).longValue() : null);
            sp.setShipId(result[1] != null ? ((Number) result[1]).longValue() : null);

            // 设置 location
            sp.setLocation(result[2] != null ? (String) result[2] : null);

            // 设置 latitude 和 longitude
            if (sp.getLocation() != null && sp.getLocation().startsWith("POINT(") && sp.getLocation().endsWith(")")) {
                String[] coordinates = sp.getLocation().substring(6, sp.getLocation().length() - 1).split(" ");
                sp.setLongitude(Double.parseDouble(coordinates[0]));
                sp.setLatitude(Double.parseDouble(coordinates[1]));
            }

            // 其他字段
            sp.setSpeed(result[3] != null ? ((Number) result[3]).intValue() : null);
            sp.setCourse(result[4] != null ? ((Number) result[4]).intValue() : null);
            sp.setHeading(result[5] != null ? ((Number) result[5]).intValue() : null);
            sp.setStatus(result[6] != null ? (String) result[6] : null);
            sp.setPositionTime(result[7] != null ? ((java.sql.Timestamp) result[7]).toLocalDateTime() : null);
            sp.setCreateTime(result[8] != null ? ((java.sql.Timestamp) result[8]).toLocalDateTime() : null);

            return sp;
        }).collect(Collectors.toList());
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

    // 根据shipId和时间范围查询轨迹数据
    public List<ShipPosition> findByShipIdAndTimeRange(Long shipId, Date startTime, Date endTime) {
        return shipPositionRepository.findByShipIdAndPositionTimeBetween(shipId, startTime, endTime);
    }


}
