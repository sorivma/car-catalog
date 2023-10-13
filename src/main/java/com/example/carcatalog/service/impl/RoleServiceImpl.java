package com.example.carcatalog.service.impl;

import com.example.carcatalog.dto.RoleDTO;
import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.Role;
import com.example.carcatalog.entity.User;
import com.example.carcatalog.mapper.impl.RoleMapper;
import com.example.carcatalog.repository.RoleRepository;
import com.example.carcatalog.repository.UserRepository;
import com.example.carcatalog.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RoleMapper roleMapper;
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
        if (userDTO.getRole() == null){
            throw new IllegalArgumentException("No role provided");
        }

        Role role = roleRepository.findById(
                userDTO.getRole().getId())
                .orElseThrow(() -> new EntityNotFoundException("No such role"));

        User user = userRepository.findByUsername
                (userDTO.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("No user with such username"));

        user.setRole(role);

        return roleMapper.toDTO(role);
    };
}
