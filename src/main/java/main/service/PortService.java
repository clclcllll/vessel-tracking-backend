package main.service;

import main.bean.Port ;
import main.dao.PortRepository ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortService {

    @Autowired
    private PortRepository portRepository;

    // 查询所有港口
    public List<Port> findAll() {
        return portRepository.findAll();
    }

    // 根据 ID 查询港口，返回 null 或 Port
    public Port findById(Integer id) {
        return portRepository.findById(id).orElse(null);
    }

    // 根据 portCode 查询港口，返回 null 或 Port
    public Port findByPortCode(String portCode) {
        return portRepository.findByPortCode(portCode);
    }

    // 保存或更新港口信息
    public Port save(Port port) {
        return portRepository.save(port);
    }

    // 根据 ID 删除港口
    public void deleteById(Integer id) {
        portRepository.deleteById(id);
    }
}
