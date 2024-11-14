package main.dao;

import main.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // JpaRepository 提供了基础的增删改查方法，无需额外定义
}
