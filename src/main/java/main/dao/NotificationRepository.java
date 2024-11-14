package main.dao;

import main.bean.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // 根据 userId 查询记录
    List<Notification> findByUserId(Long userId);
}
