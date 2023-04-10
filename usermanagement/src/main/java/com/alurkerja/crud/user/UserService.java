package com.alurkerja.crud.user;

import com.alurkerja.core.repository.CrudRepository;
import com.alurkerja.core.service.CrudService;
import com.alurkerja.spec.entity.BaseDto;
import com.alurkerja.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService extends CrudService<User, UserDto, UserRepository> {

    @Autowired
    PasswordUtil passwordUtil;
    protected UserService(UserRepository simpleJpaRepository) {
        super(simpleJpaRepository);
    }

    @Override
    public String getCurrentUser() {
        return null;
    }

    @Override
    public String getCurrentGroup() {
        return null;
    }

    @Override
    public String getCurrentOrganization() {
        return null;
    }

    @Override
    public User createFromDto(UserDto dto) {
        BaseDto<User, UserDto> dtoable = (BaseDto)dto;
        User entity = dtoable.fromDto();
        entity.setCreatedDate(new Date());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordUtil.encodePassword(dto.getPassword()));
        entity.setRole(dto.getRole());;
        this.jpaRepository.save(entity);
        return entity;
    }
}
