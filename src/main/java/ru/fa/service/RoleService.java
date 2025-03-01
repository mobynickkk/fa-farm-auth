package ru.fa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fa.dto.RoleDto;
import ru.fa.mapper.RoleMapper;
import ru.fa.persistence.entity.RoleEntity;
import ru.fa.persistence.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;

    public RoleEntity findRoleByCode(String code) {
        return repository.findById(code)
                .orElseThrow(() -> new RuntimeException());
    }

}
