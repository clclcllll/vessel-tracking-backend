package main.service;

import main.bean.UserRole;
import main.dao.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    // 查询全部记录
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    // 根据用户 ID 查询单条记录
    public UserRole findByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    // 根据角色 ID 查询记录
    public List<UserRole> findByRoleId(Integer roleId) {
        return userRoleRepository.findByRoleId(roleId);
    }

    // 保存或更新记录
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    // 根据用户 ID 删除记录
    public void deleteByUserId(Long userId) {
        userRoleRepository.deleteByUserId(userId);
    }
}
