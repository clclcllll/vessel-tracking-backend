package main.service;

import main.bean.Log;
import main.dao.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    // 查询所有记录
    public List<Log> findAll() {
        return logRepository.findAll();
    }

    // 根据 ID 查询记录
    public Log findById(Long id) {
        return logRepository.findById(id).orElse(null);
    }

    // 根据 userId 查询记录
    public List<Log> findByUserId(Long userId) {
        return logRepository.findByUserId(userId);
    }

    // 保存或更新记录
    public Log save(Log log) {
        return logRepository.save(log);
    }

    // 根据 ID 删除记录
    public void deleteById(Long id) {
        logRepository.deleteById(id);
    }
}
