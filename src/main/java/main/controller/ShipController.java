package main.controller;

import main.bean.Ship;
import main.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ships")
public class ShipController {

    @Autowired
    private ShipService shipService;

    // 获取所有船舶列表
    @GetMapping
    public List<Ship> getAllShips() {
        return shipService.getAllShips();
    }

    // 根据ID获取船舶信息（可选）
    @GetMapping("/{shipId}")
    public Ship getShipById(@PathVariable Long shipId) {
        return shipService.getShipById(shipId);
    }
}
