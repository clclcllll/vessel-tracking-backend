package main.dao;

import main.bean.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    // 根据 userId 查询记录
    List<Log> findByUserId(Long userId);
}
