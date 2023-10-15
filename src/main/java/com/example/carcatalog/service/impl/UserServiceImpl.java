package com.example.carcatalog.service.impl;

import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.Role;
import com.example.carcatalog.entity.User;
import com.example.carcatalog.except.NoUsernameException;
import com.example.carcatalog.except.UserDeactivatedException;
import com.example.carcatalog.except.UserNotFoundException;
import com.example.carcatalog.mapper.Mapper;
import com.example.carcatalog.repos.RoleRepository;
import com.example.carcatalog.repos.UserRepository;
import com.example.carcatalog.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Mapper<User, UserDTO> userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, Mapper<User, UserDTO> userMapper) {
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
        if (dto.getUsername() == null) {
            throw new NoUsernameException();
        }

        User user = userMapper.toModel(dto);
        Optional<User> dbUser = userRepository.findByUsername(dto.getUsername());

        if (dbUser.isPresent()) {
            if (!dbUser.get().getIsActive()) {
                throw new UserDeactivatedException();
            }

            user.setId(dbUser.get().getId());
            user.setModified(LocalDateTime.now());
            return userMapper.toDTO(userRepository.save(user));
        }

        user.setCreated(LocalDateTime.now());
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
