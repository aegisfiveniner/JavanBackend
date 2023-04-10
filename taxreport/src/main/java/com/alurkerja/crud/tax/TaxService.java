package com.alurkerja.crud.tax;

import com.alurkerja.core.entity.CrudEntity;
import com.alurkerja.core.repository.CrudRepository;
import com.alurkerja.core.service.CrudService;
import com.alurkerja.crud.user.User;
import com.alurkerja.crud.user.UserRepository;
import com.alurkerja.spec.entity.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TaxService extends CrudService<Tax, TaxDto, TaxRepository> {
    @Autowired
    UserRepository userRepository;

    protected TaxService(TaxRepository simpleJpaRepository) {
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
    public Tax createFromDto(TaxDto dto) {
        Optional<User> user = userRepository.findByEmail(dto.getUserEmail());

        BaseDto<Tax, TaxDto> dtoable = (BaseDto)dto;
        Tax entity = dtoable.fromDto();
        entity.setReceiptNo(dto.getReceiptNo());
        entity.setCreatedDate(new Date());

        if (user.isPresent()){
            entity.setUser(user.get());
        }
        this.jpaRepository.save(entity);
        return entity;
    }
}
