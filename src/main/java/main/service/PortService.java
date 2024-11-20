package main.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import main.bean.Port ;
import main.dao.PortRepository ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // 获取港口详细信息并返回 JSON 格式字符串
    public Map<String, Object> getPortDetailsJSONById(Integer portId) {
        Map<String, Object> response = new HashMap<>();

        // 查询港口信息
        Port port = portRepository.findById(portId).orElseThrow(() ->
                new RuntimeException("未找到港口 ID 为 " + portId + " 的信息！"));
        response.put("port", port);

        return response;
    }
}
