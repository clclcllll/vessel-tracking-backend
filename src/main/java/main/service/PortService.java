package main.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import main.bean.Port ;
import main.dao.PortRepository ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        List<Object[]> portDetails = portRepository.findPortByIdWithCoordinatesNative(portId);

        if (portDetails.isEmpty()) {
            throw new RuntimeException("未找到港口 ID 为 " + portId + " 的信息！");
        }

        // 将查询结果转换为 Map
        Map<String, Object> portInfo = new HashMap<>();
        Object[] result = portDetails.get(0);
        portInfo.put("id", result[0]);
        portInfo.put("portCode", result[1]);
        portInfo.put("portName", result[2]);
        portInfo.put("country", result[3]);
        portInfo.put("portType", result[4]);
        portInfo.put("portSize", result[5]);

        // 确保经度和纬度是 Double 类型
        if (result[6] instanceof Double) {
            portInfo.put("longitude", (Double) result[6]);
        } else if (result[6] instanceof BigDecimal) {
            portInfo.put("longitude", ((BigDecimal) result[6]).doubleValue());
        } else {
            throw new RuntimeException("未知的经度类型: " + result[6].getClass().getName());
        }

        if (result[7] instanceof Double) {
            portInfo.put("latitude", (Double) result[7]);
        } else if (result[7] instanceof BigDecimal) {
            portInfo.put("latitude", ((BigDecimal) result[7]).doubleValue());
        } else {
            throw new RuntimeException("未知的纬度类型: " + result[7].getClass().getName());
        }

        portInfo.put("timeZone", result[8]);
        portInfo.put("anchorage", result[9]);
        portInfo.put("berthDraft", result[10]);
        portInfo.put("chartNumber", result[11]);
        portInfo.put("portAuthority", result[12]);
        portInfo.put("address", result[13]);
        portInfo.put("phone", result[14]);
        portInfo.put("fax", result[15]);
        portInfo.put("website", result[16]);
        portInfo.put("introduction", result[17]);
        portInfo.put("createTime", result[18]);
        portInfo.put("updateTime", result[19]);

        response.put("port", portInfo);

        return response;
    }
}
