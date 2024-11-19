package main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import main.bean.Country;
import main.bean.Ship;
import main.bean.ShipType;
import main.bean.Voyage;
import main.service.CountryService;
import main.service.ShipService;
import main.service.ShipTypeService;
import main.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ships")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @Autowired
    private ShipTypeService shipTypeService;

    @Autowired
    private VoyageService voyageService;

    @Autowired
    private CountryService countryService;

    // 获取所有船舶列表
    @GetMapping
    public List<Ship> getAllShips() {
        return shipService.getAllShips();
    }

    // 获取指定船舶的详细信息
    @GetMapping("/{shipId}/details")
    public Map<String, Object> getShipDetails(@PathVariable Long shipId) {
        Map<String, Object> response = new HashMap<>();

        try {
            // 1. 通过 shipId 查询船舶信息
            Ship ship = shipService.getShipById(shipId);
            if (ship == null) {
                throw new RuntimeException("未找到船舶 ID 为 " + shipId + " 的信息！");
            }
            response.put("ship", ship);

            // 2. 通过 shipTypeId 查询船舶类型信息
            ShipType shipType = shipTypeService.findByTypeCode(ship.getShipTypeId());
            if (shipType != null) {
                response.put("shipType", shipType);
            } else {
                response.put("shipType", "未找到对应的船舶类型信息");
            }

            // 3. 通过 countryId 查询国家信息
            Country country = countryService.findById(ship.getCountryId());
            if (country != null) {
                response.put("country", country);
            } else {
                response.put("country", "未找到对应的国家信息");
            }

            // 4. 根据 shipId 查询航程信息
            Voyage voyage = voyageService.findByShipId(shipId);
            if (voyage != null) {
                response.put("voyage", voyage);
            } else {
                response.put("voyage", "未找到对应的航程信息");
            }

            // 打印 JSON 格式的响应
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule()); // 支持日期格式
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // 禁用时间戳
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
            System.out.println("船舶详情 (JSON 格式): " + json);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取船舶详情时发生错误: " + e.getMessage());
        }

        return response;
    }


}
