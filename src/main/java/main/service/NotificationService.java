package main.service;

import main.bean.Notification;
import main.dao.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // 查询所有记录
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    // 根据 ID 查询记录
    public Notification findById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    // 根据 userId 查询记录
    public List<Notification> findByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    // 保存或更新记录
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    // 根据 ID 删除记录
    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }
}
