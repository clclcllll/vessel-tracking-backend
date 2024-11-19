package main.controller;

import main.bean.ShipPosition;
import main.service.ShipPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/ships")
public class ShipPositionController {

    @Autowired
    private ShipPositionService shipPositionService;

    // 获取指定船舶的轨迹数据
    @GetMapping("/{shipId}/positions")
    public List<ShipPosition> getShipPositions(
            @PathVariable Long shipId,
            @RequestParam(value = "start_time", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startTime,
            @RequestParam(value = "end_time", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endTime) {

        if (startTime != null && endTime != null) {
            // 根据船舶ID和时间范围查询轨迹数据
            return shipPositionService.findByShipIdAndTimeRange(shipId, startTime, endTime);
        } else {
            // 仅根据船舶ID查询所有轨迹数据
            return shipPositionService.findByShipId(shipId);
        }
    }
}
