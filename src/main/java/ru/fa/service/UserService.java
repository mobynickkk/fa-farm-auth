package ru.fa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fa.dto.UserDto;
import ru.fa.mapper.UserMapper;
import ru.fa.persistence.entity.RoleEntity;
import ru.fa.persistence.repository.UserRepository;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(UserDto userDto) {
        var user = userMapper.toEntity(userDto, passwordEncoder);
        var role = roleService.findRoleByCode("ROLE_USER");
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
    }

    public UserDto getUserByUsername(String username) {
        return userRepository.findById(username)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь %s не найден", username)));
    }

    public void deleteUser(UserDto userDto) {
        userRepository.deleteById(userDto.username());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь %s не найден", username)));
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(RoleEntity::getRoleCode)
                        .map(SimpleGrantedAuthority::new)
                        .toList());
    }
}
