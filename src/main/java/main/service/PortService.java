package main.service;

import main.bean.Port ;
import main.dao.PortRepository ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

        // 处理每个字段，确保 NULL 值被正确处理
        portInfo.put("id", result[0] != null ? ((Number) result[0]).intValue() : null);
        portInfo.put("portCode", result[1] != null ? (String) result[1] : null);
        portInfo.put("portName", result[2] != null ? (String) result[2] : null);
        portInfo.put("country", result[3] != null ? (String) result[3] : null);
        portInfo.put("portType", result[4] != null ? (String) result[4] : null);
        portInfo.put("portSize", result[5] != null ? (String) result[5] : null);

        // 经度和纬度处理，确保值为小数并避免科学计数法
        DecimalFormat df = new DecimalFormat("#.#######"); // 保留 7 位小数
        portInfo.put("longitude", result[6] != null ? Double.valueOf(df.format(((Number) result[6]).doubleValue())) : null);
        portInfo.put("latitude", result[7] != null ? Double.valueOf(df.format(((Number) result[7]).doubleValue())) : null);

        portInfo.put("timeZone", result[8] != null ? (String) result[8] : null);
        portInfo.put("anchorage", result[9] != null ? (String) result[9] : null);
        portInfo.put("berthDraft", result[10] != null ? ((String) result[10]) : null);
        portInfo.put("chartNumber", result[11] != null ? ((String) result[11]) : null);
        portInfo.put("portAuthority", result[12] != null ? (String) result[12] : null);
        portInfo.put("address", result[13] != null ? (String) result[13] : null);
        portInfo.put("phone", result[14] != null ? (String) result[14] : null);
        portInfo.put("fax", result[15] != null ? (String) result[15] : null);
        portInfo.put("website", result[16] != null ? (String) result[16] : null);
        portInfo.put("introduction", result[17] != null ? (String) result[17] : null);
        portInfo.put("createTime", result[18] != null ? ((java.sql.Timestamp) result[18]).toLocalDateTime() : null);
        portInfo.put("updateTime", result[19] != null ? ((java.sql.Timestamp) result[19]).toLocalDateTime() : null);

        response.put("port", portInfo);

        return response;
    }

    public Map<String, Object> getAllPortsJSON() {
        Map<String, Object> response = new HashMap<>();

        // 查询所有港口的 id、port_name 和经纬度
        List<Object[]> allPortsDetails = portRepository.findAllPortsWithCoordinatesNative();

        if (allPortsDetails.isEmpty()) {
            throw new RuntimeException("未找到港口信息！");
        }

        // 将查询结果转换为 List
        List<Map<String, Object>> portsInfoList = new ArrayList<>();

        for (Object[] result : allPortsDetails) {
            // 将每个港口的简要信息转换为 Map
            Map<String, Object> portInfo = new HashMap<>();

            // 处理每个字段，确保 NULL 值被正确处理
            portInfo.put("id", result[0] != null ? ((Number) result[0]).intValue() : null);
            portInfo.put("portName", result[1] != null ? (String) result[1] : null);

            // 经度和纬度处理，确保值为小数并避免科学计数法
            DecimalFormat df = new DecimalFormat("#.#######"); // 保留 7 位小数
            portInfo.put("longitude", result[2] != null ? Double.valueOf(df.format(((Number) result[2]).doubleValue())) : null);
            portInfo.put("latitude", result[3] != null ? Double.valueOf(df.format(((Number) result[3]).doubleValue())) : null);

            // 添加到港口信息列表
            portsInfoList.add(portInfo);
        }

        // 返回所有港口信息
        response.put("ports", portsInfoList);

        return response;
    }

}
