package ru.fa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fa.dto.RoleDto;
import ru.fa.mapper.RoleMapper;
import ru.fa.persistence.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;
    private final RoleMapper roleMapper;

    public RoleDto findRoleByCode(String code) {
        return repository.findById(code)
                .map(roleMapper::toDto)
                .orElseThrow(() -> new RuntimeException());
    }

}
