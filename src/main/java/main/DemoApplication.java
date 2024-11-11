package main;

import main.bean.Ship;
import main.dao.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private ShipRepository shipRepository;

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
        } else {
            System.out.println("没有找到任何船舶信息。");
        }
    }
}
