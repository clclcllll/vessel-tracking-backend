package main.service;

import main.bean.Ship;
import main.bean.ShipPosition;
import main.dao.ShipPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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

    //确保 latitude 和 longitude 是小数点格式，避免 JSON 序列化时自动使用科学计数法。
    public List<ShipPosition> findByShipIdWithCoordinates(Long shipId) {
        DecimalFormat df = new DecimalFormat("#.#######"); // 保留 7 位小数
        List<Object[]> rawResults = shipPositionRepository.findByShipIdWithCoordinatesNative(shipId);
        return rawResults.stream().map(result -> {
            ShipPosition sp = new ShipPosition();
            sp.setId(((Number) result[0]).longValue());
            sp.setShipId(((Number) result[1]).longValue());
            sp.setLongitude(Double.valueOf(df.format(((Number) result[2]).doubleValue())));
            sp.setLatitude(Double.valueOf(df.format(((Number) result[3]).doubleValue())));
            sp.setSpeed(result[4] != null ? ((Number) result[4]).intValue() : null);
            sp.setCourse(result[5] != null ? ((Number) result[5]).intValue() : null);
            sp.setHeading(result[6] != null ? ((Number) result[6]).intValue() : null);
            sp.setStatus((String) result[7]);
            sp.setPositionTime(((java.sql.Timestamp) result[8]).toLocalDateTime());
            sp.setCreateTime(((java.sql.Timestamp) result[9]).toLocalDateTime());
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
