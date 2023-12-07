package com.example.carcatalog.service.impl;

import com.example.carcatalog.dto.RoleDTO;
import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.Role;
import com.example.carcatalog.entity.User;
import com.example.carcatalog.except.ClientErrorException;
import com.example.carcatalog.mapper.Mapper;
import com.example.carcatalog.mapper.impl.RoleMapper;
import com.example.carcatalog.repos.RoleRepository;
import com.example.carcatalog.repos.UserRepository;
import com.example.carcatalog.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private final Mapper<Role, RoleDTO> roleMapper;
    private final Mapper<User, UserDTO> userMapper;

    public RoleServiceImpl(RoleMapper roleMapper, Mapper<User, UserDTO> userMapper) {
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleRepository
                .findAll()
                .stream()
                .map(roleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(UUID uuid) {
        return roleMapper.toDTO(
                roleRepository.findById(uuid)
                        .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public RoleDTO add(RoleDTO dto) {
        return roleMapper.toDTO(
                roleRepository.save(
                        roleMapper.toModel(dto)));
    }

    @Override
    public List<RoleDTO> addAll(List<RoleDTO> dtoList) {
        List<Role> roles = dtoList.stream().map(roleMapper::toModel).toList();
        return roleRepository.saveAll(roles).stream().map(roleMapper::toDTO).toList();
    }

    @Override
    public UserDTO assign(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername()).orElseThrow(
                () -> new ClientErrorException.EntityNotFoundException("User", "username", userDTO.getUsername())
        );
        user.setRole(findByName(userDTO.getRoleName()));
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public Role findByName(Role.RoleName name) {
        return roleRepository.findByName(name).orElseThrow(
                () -> new ClientErrorException.EntityNotFoundException("Role", "name", name.toString()));
    }


}
