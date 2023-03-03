package com.sparepartmanagement.service.impl;

import com.google.gson.Gson;
import com.sparepartmanagement.config.MQConfig;
import com.sparepartmanagement.entity.History;
import com.sparepartmanagement.entity.Sparepart;
import com.sparepartmanagement.entity.User;
import com.sparepartmanagement.repository.HistoryRepository;
import com.sparepartmanagement.repository.SparepartRepository;
import com.sparepartmanagement.repository.UserRepository;
import com.sparepartmanagement.service.SparepartService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SparepartServiceImpl implements SparepartService {
    private static final String ROLE_ADMIN_SPAREPART = "ADMIN-SPAREPART";
    @Autowired
    private SparepartRepository sparepartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private MQService mqService;

    public SparepartServiceImpl() {
    }

    public Sparepart save(Sparepart sparepart) {
        String detail = sparepart.getId() == null ? "INSERT SPAREPART" : "UPDATE SPAREPART";
        sparepart.setCreatedAt(sparepart.getCreatedAt() == null ? LocalDateTime.now() : sparepart.getCreatedAt());
        sparepart = sparepartRepository.save(sparepart);
        
        mqService.sendMessage(MQConfig.queueSparepart().getName(), (new Gson()).toJson(sparepart));
        
        List<User> userList = userRepository.findAllByRole(ROLE_ADMIN_SPAREPART);
        History history = new History();
        history.setSparepart(sparepart);
        history.setCreatedAt(LocalDateTime.now());
        history.setDetails(detail);
        history.setUser(!userList.isEmpty() ? (User)userList.get(0) : null);
        history = historyRepository.save(history);
        mqService.sendMessage(MQConfig.queueHistory().getName(), (new Gson()).toJson(history));
        return sparepart;
    }

    public List<Sparepart> findAll() {
        return sparepartRepository.findAll();
    }

    public Sparepart findById(Integer id) {
        Optional<Sparepart> sparepart = sparepartRepository.findById(id);
        return sparepart.isPresent() ? sparepart.get() : null;
    }
}