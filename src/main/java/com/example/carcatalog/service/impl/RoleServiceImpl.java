package com.example.carcatalog.service.impl;

import com.example.carcatalog.dto.RoleDTO;
import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.Role;
import com.example.carcatalog.entity.User;
import com.example.carcatalog.except.NoRoleException;
import com.example.carcatalog.except.NoUsernameException;
import com.example.carcatalog.except.RoleNotFoundException;
import com.example.carcatalog.except.UserNotFoundException;
import com.example.carcatalog.mapper.Mapper;
import com.example.carcatalog.mapper.impl.RoleMapper;
import com.example.carcatalog.repos.RoleRepository;
import com.example.carcatalog.repos.UserRepository;
import com.example.carcatalog.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final Mapper<Role, RoleDTO> roleMapper;
    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.roleMapper = roleMapper;

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
    public RoleDTO assign(UserDTO userDTO) {
        if (userDTO.getRoleName() == null){
            throw new NoRoleException();
        }

        if (userDTO.getUsername() == null){
            throw new NoUsernameException();
        }

        Role role = roleRepository.findByName(
                userDTO.getRoleName())
                .orElseThrow(RoleNotFoundException::new);

        User user = userRepository.findByUsername
                (userDTO.getUsername())
                .orElseThrow(UserNotFoundException::new);

        user.setRole(role);
        user.setModified(LocalDateTime.now());

        userRepository.save(user);

        return roleMapper.toDTO(role);
    };
}
