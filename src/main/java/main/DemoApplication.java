package main;

import main.bean.Ship;
import main.bean.ShipPosition;
import main.dao.ShipRepository;
import main.service.ShipPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private ShipPositionService shipPositionService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // 获取所有船舶列表
        List<Ship> ships = shipRepository.findAll();

        // 检查列表是否为空，并输出第一艘船的信息
        if (!ships.isEmpty()) {
            Ship firstShip = ships.get(0);
            System.out.println("第一艘船的信息:");
            System.out.println("ID: " + firstShip.getId());
            System.out.println("MMSI: " + firstShip.getMmsi());
            System.out.println("船名: " + firstShip.getShipName());

            // 使用第一艘船的 MMSI 查询其位置记录
            List<ShipPosition> positions = shipPositionService.findPositionsByShipMmsi(firstShip.getMmsi());
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
        } else {
            System.out.println("没有找到任何船舶信息。");
        }
    }
}
