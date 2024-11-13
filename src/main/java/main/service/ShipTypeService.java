package main.service;

import main.bean.ShipType;
import main.dao.ShipTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipTypeService {

    @Autowired
    private ShipTypeRepository shipTypeRepository;

    // 查询所有记录
    public List<ShipType> findAll() {
        return shipTypeRepository.findAll();
    }

    // 根据 ID 查询记录
    public ShipType findById(Integer id) {
        return shipTypeRepository.findById(id).orElse(null);
    }

    // 根据 typeCode 查询记录
    public ShipType findByTypeCode(Integer typeCode) {
        return shipTypeRepository.findByTypeCode(typeCode).orElse(null);
    }

    // 保存或更新记录
    public ShipType save(ShipType shipType) {
        return shipTypeRepository.save(shipType);
    }

    // 根据 ID 删除记录
    public void deleteById(Integer id) {
        shipTypeRepository.deleteById(id);
    }
}
