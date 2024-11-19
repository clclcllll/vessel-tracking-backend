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

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private static final SecureRandom RANDOM = new SecureRandom(); // 用于生成 salt

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * 用户注册时生成随机 salt，并使用 salt 加密密码
     */
    public User register(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("用户名已存在！");
        }
        // 设置默认状态
        user.setStatus(Byte.valueOf("ACTIVE"));

        // 设置随机 salt 并加密密码
        String salt = generateSalt();
        user.setSalt(salt);
        user.setPassword(passwordEncoder.encode(user.getPassword() + salt));

        // 保存用户
        return userRepository.save(user);
    }

    /**
     * 用户登录时验证密码（需要使用用户的 salt）
     */
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户名或密码错误！");
        }

        // 验证密码（加上用户的 salt 进行校验）
        String encodedPassword = passwordEncoder.encode(password + user.getSalt());
        if (!encodedPassword.equals(user.getPassword())) {
            throw new RuntimeException("用户名或密码错误！");
        }

        // 生成 JWT
        return jwtTokenUtil.generateToken(username);
    }

    /**
     * 生成随机 salt
     */
    private String generateSalt() {
        byte[] saltBytes = new byte[16]; // 16 字节长度的 salt
        RANDOM.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes); // 返回 Base64 编码的 salt
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
