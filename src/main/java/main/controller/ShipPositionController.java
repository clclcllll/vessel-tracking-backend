package main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

        List<ShipPosition> positions;
        if (startTime != null && endTime != null) {
            positions = shipPositionService.findByShipIdAndTimeRange(shipId, startTime, endTime);
        } else {
            positions = shipPositionService.findByShipIdWithCoordinates(shipId);
        }

        // 格式化打印 JSON 数据
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule()); // 注册 JavaTimeModule
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // 禁用时间戳格式
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(positions);
            System.out.println("轨迹数据 (JSON 格式): " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return positions;
    }

}
