package main.dao;

import main.bean.Port ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepository extends JpaRepository<Port, Integer> {
    // 根据 portCode 查询记录
    Port findByPortCode(String portCode);
}
