package com.example.carcatalog.service.impl;

import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.Role;
import com.example.carcatalog.entity.User;
import com.example.carcatalog.except.NoUsernameException;
import com.example.carcatalog.except.UserNotFoundException;
import com.example.carcatalog.mapper.impl.UserMapper;
import com.example.carcatalog.repository.RoleRepository;
import com.example.carcatalog.repository.UserRepository;
import com.example.carcatalog.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(UUID uuid) {
        return userMapper.toDTO(userRepository
                .findById(uuid).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDTO add(UserDTO dto) {
        User user = userMapper.toModel(dto);

        if (user.getUsername() == null) {
            throw new NoUsernameException();
        }
        if (user.getId() == null) {
            user.setCreated(LocalDateTime.now());
        }

        user.setModified(LocalDateTime.now());
        user.setIsActive(true);
        user.setRole(roleRepository
                .findByName(Role.RoleName.USER)
                .orElseThrow(() -> new EntityNotFoundException("No role with provided name")));

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public void deactivate(UserDTO userDTO) {
        if (userDTO.getUsername() == null) {
            throw new NoUsernameException();
        }

        User user = userRepository
                .findByUsername(userDTO.getUsername())
                .orElseThrow(UserNotFoundException::new);

        user.setIsActive(!user.getIsActive());
        userRepository.save(user);
    }

    @Override
    public UserDTO findByUserName(UserDTO userDTO) {
        if (userDTO.getUsername() == null) {
            throw new NoUsernameException();
        }

        return userMapper.toDTO(userRepository
                .findByUsername(userDTO.getUsername())
                .orElseThrow(UserNotFoundException::new));
    }
}
