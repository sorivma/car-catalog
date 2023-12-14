package com.example.carcatalog.service.impl;

import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.Role;
import com.example.carcatalog.entity.User;
import com.example.carcatalog.except.ClientErrorException;
import com.example.carcatalog.mapper.Mapper;
import com.example.carcatalog.repos.UserRepository;
import com.example.carcatalog.service.RoleService;
import com.example.carcatalog.service.UserService;
import com.example.carcatalog.utils.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleService roleService;
    private final Mapper<User, UserDTO> userMapper;
    private final ValidationUtil validator;

    public UserServiceImpl(Mapper<User, UserDTO> userMapper, ValidationUtil validator) {
        this.userMapper = userMapper;
        this.validator = validator;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleService roleService) {
        this.roleService = roleService;
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
                .findById(uuid).orElseThrow(() ->
                        new ClientErrorException.EntityNotFoundException("User", "id", uuid.toString())));
    }

    @Override
    public UserDTO add(UserDTO dto) {
        User user = userMapper.toModel(dto);
        user.setRole(roleService.findByName(Role.RoleName.USER));
        user.setIsActive(true);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public List<UserDTO> addAll(List<UserDTO> dtoList) {
        List<User> users = dtoList.stream().map(userMapper::toModel).toList();
        return userRepository.saveAll(users).stream().map(userMapper::toDTO).toList();
    }

    @Override
    public void deactivate(String username) {
        User user = findEntityByUserName(username);
        user.setIsActive(!user.getIsActive());
        userRepository.save(user);
    }

    @Override
    public User findEntityByUserName(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new ClientErrorException.EntityNotFoundException("User", "username", username));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User user = findEntityByUserName(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    @Cacheable(value = "users", key = "#username")
    public UserDTO findByUserName(String username) {
        return userMapper.toDTO(findEntityByUserName(username));
    }

    public Boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }
}
