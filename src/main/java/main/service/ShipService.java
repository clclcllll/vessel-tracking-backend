package main.service;

import main.bean.*;
import main.dao.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShipService {

    private final ShipRepository shipRepository;

    private final ShipTypeService shipTypeService;
    private final VoyageService voyageService;
    private final CountryService countryService;
    private final PortService portService;


    @Autowired
    public ShipService(
            ShipRepository shipRepository,
            ShipTypeService shipTypeService,
            VoyageService voyageService,
            CountryService countryService,
            PortService portService
    ) {
        this.shipRepository = shipRepository;
        this.shipTypeService = shipTypeService;
        this.voyageService = voyageService;
        this.countryService = countryService;
        this.portService = portService;
    }

    // 获取所有船舶列表
    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }

    // 获取船舶详细信息
    public Map<String, Object> getShipDetailsById(Long shipId) {
        Map<String, Object> response = new HashMap<>();

        Ship ship = shipRepository.findById(shipId).orElseThrow(() ->
                new RuntimeException("未找到船舶 ID 为 " + shipId + " 的信息！"));
        response.put("ship", ship);

        ShipType shipType = shipTypeService.findByTypeCode(ship.getShipTypeId());
        response.put("shipType", shipType != null ? shipType : "未找到对应的船舶类型信息");

        Country country = countryService.findById(ship.getCountryId());
        response.put("country", country != null ? country : "未找到对应的国家信息");

        Voyage voyage = voyageService.findByShipId(shipId);
        if (voyage != null) {
            response.put("voyage", voyage);

            Port arrivalPort = (voyage.getArrivalPortId() != null) ?
                    portService.findById(voyage.getArrivalPortId()) : null;
            response.put("arrivalPort", arrivalPort != null ? arrivalPort : "未找到对应的到达港口信息");

            Port departurePort = (voyage.getDeparturePortId() != null) ?
                    portService.findById(voyage.getDeparturePortId()) : null;
            response.put("departurePort", departurePort != null ? departurePort : "未找到对应的出发港口信息");
        } else {
            response.put("voyage", "未找到对应的航程信息");
        }
        return response;
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
