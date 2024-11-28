package main.controller;

import main.service.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ports")
public class PortController {

    @Autowired
    private PortService portService;

    /**
     * 根据港口 ID 获取港口详细信息（JSON 格式）
     *
     * @param portId 港口 ID
     * @return JSON 格式的港口详情
     */
    @GetMapping("/{portId}/details")
    public Map<String, Object> getPortDetailsById(@PathVariable Integer portId) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 调用 PortService 获取 JSON 格式的港口详情
            return portService.getPortDetailsJSONById(portId);
        } catch (RuntimeException e) {
            // 捕获并返回错误信息
            response.put("error", e.getMessage());
            return response;
        } catch (Exception ex) {
            // 捕获其他异常
            response.put("error", "An unexpected error occurred.");
            return response;
        }
    }

    /**
     * 查询所有港口信息
     *
     * @return 所有港口的 JSON 列表
     */
    @GetMapping("/all")
    public Map<String, Object> getAllPorts() {
        try {
            // 调用 PortService 获取所有港口的 JSON 列表
            return portService.getAllPortsJSON();
        } catch (RuntimeException e) {
            // 处理已知运行时异常
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (Exception ex) {
            // 处理未知异常
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.", ex);
        }
    }
}
