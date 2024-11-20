package main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import main.bean.*;
import main.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ships")
public class ShipController {

    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    // 获取所有船舶列表
    @GetMapping
    public List<Ship> getAllShips() {
        return shipService.getAllShips();
    }

    // 获取指定船舶的详细信息
    @GetMapping("/{shipId}/details")
    public Map<String, Object> getShipDetails(@PathVariable Long shipId) {
        return shipService.getShipDetailsById(shipId);
    }

}
