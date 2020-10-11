package com.training.salon.service;

import com.training.salon.entity.Master;
import com.training.salon.entity.Role;
import com.training.salon.repository.MasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MasterService {

    private final MasterRepository masterRepository;

    public MasterService(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    public Optional<Master> findAll() {
        return masterRepository.findByUserRole(Role.MASTER);
    }

    public Page<Master> findAll(Pageable pageable) {
        return masterRepository.findAll(pageable);
    }

    public Optional<Master> findById(Long masterId) {
        return masterRepository.findById(masterId);
    }

    public Page<Master> getMastersByCategory(Long categoryId, Pageable pageable) {
        return masterRepository.findAllMastersByCategoryId(categoryId, pageable);
    }

    public Optional<Master> findByUserId(Long userId) {
        return masterRepository.findByUserId(userId);
    }

    public Master addMaster(Master master) {
        Master savedMaster = masterRepository.saveAndFlush(master);
        return savedMaster;
    }
}
