package main;

import main.bean.Ship;
import main.bean.ShipPosition;
import main.bean.ShipType;
import main.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private ShipService shipService;

    @Autowired
    private ShipPositionService shipPositionService;

    @Autowired
    private ShipTypeService shipTypeService;

    @Autowired
    private PortService portService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private VoyageService voyageService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // 获取所有船舶列表
        List<Ship> ships = shipService.getAllShips();

        // 检查列表是否为空，并输出第一艘船的信息
        if (!ships.isEmpty()) {
            Ship firstShip = ships.get(0);
            System.out.println("第一艘船的信息:");
            System.out.println("ID: " + firstShip.getId());
            System.out.println("MMSI: " + firstShip.getMmsi());
            System.out.println("船名: " + firstShip.getShipName());

            // 查询第一艘船位置记录
            List<ShipPosition> positions = shipPositionService.findByShipId(firstShip.getId());
            if (!positions.isEmpty()) {
                System.out.println("该船的位置信息记录:");
                for (ShipPosition position : positions) {
                    System.out.println("位置ID: " + position.getId() +
                            ", 速度: " + position.getSpeed() +
                            ", 航向: " + position.getCourse() +
                            ", 状态: " + position.getStatus() +
                            ", 记录时间: " + position.getPositionTime());
                }
            } else {
                System.out.println("没有找到该船的任何位置信息记录。");
            }

            //查询第一艘船的船舶类型
            ShipType shipType = shipTypeService.findByTypeCode(firstShip.getShipTypeId());
            if (shipType != null) {
                System.out.println("船舶类型ID: " + shipType.getId());
                System.out.println("船舶类型名称: " + shipType.getTypeName());
                System.out.println("船舶类型代码: " + shipType.getTypeCode());
                System.out.println("船舶类型描述: " + shipType.getDescription());
                System.out.println("船舶类型更新时间: " + shipType.getUpdateTime());
                System.out.println("船舶类型创建时间: " + shipType.getCreateTime());

            } else {
                System.out.println("未找到船舶类型");
            }

            System.out.println("港口名称:" + portService.findByPortCode("PLGDN").getPortName());

            System.out.println("国家名称:" + countryService.findById(7).getCountryName());

            try {
                System.out.println("第一艘船预计到港时间:" + voyageService.findByShipId(firstShip.getId()).getEta());
            }
            catch (Exception e){
                System.out.println("没有找到任何航程信息");
            }

        } else {
            System.out.println("没有找到任何船舶信息。");
        }
    }
}
