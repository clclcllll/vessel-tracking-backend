package main.dao;

import main.bean.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    // 根据用户 ID 查询单条记录
    UserRole findByUserId(Long userId);

    // 根据角色 ID 查询记录
    List<UserRole> findByRoleId(Integer roleId);

    // 根据用户 ID 删除记录
    void deleteByUserId(Long userId);
}
