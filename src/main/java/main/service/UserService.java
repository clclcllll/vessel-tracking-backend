package main.service;

import main.bean.User;
import main.dao.UserRepository;
import main.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

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

    // 用户注册
    public User register(User user) {
        // 检查用户名是否已存在
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("用户名已存在！");
        }
        // 对密码进行加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 保存用户
        return userRepository.save(user);
    }

    // 用户登录
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("用户名或密码错误！");
        }
        // 生成 JWT
        return jwtTokenUtil.generateToken(username);
    }

    // 实现 UserDetailsService 的 loadUserByUsername 方法
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList()) // 此处可以添加角色和权限
                .build();
    }
}
