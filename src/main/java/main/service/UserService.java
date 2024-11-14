package main.service;

import main.bean.User;
import main.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 查询所有用户
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // 根据 ID 查询用户
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // 根据用户名查询用户
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 保存或更新用户信息
    public User save(User user) {
        return userRepository.save(user);
    }

    // 根据 ID 删除用户
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
