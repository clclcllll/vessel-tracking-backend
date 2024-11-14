package main.service;

import main.bean.Role;
import main.dao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // 获取全部角色
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    // 根据 ID 查询角色
    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    // 保存或更新角色信息
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    // 根据 ID 删除角色
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }
}
