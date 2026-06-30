package com.aftermidnight.aftermidnight.services;

import com.aftermidnight.aftermidnight.dtos.BarmakerCreateRequest;
import com.aftermidnight.aftermidnight.dtos.BarmakerLoginRequest;
import com.aftermidnight.aftermidnight.entities.Barmaker;
import com.aftermidnight.aftermidnight.repositories.BarmakerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BarmakerService {

    private final BarmakerRepository barmakerRepository;
    private final PasswordEncoder passwordEncoder;

    public Barmaker register(BarmakerCreateRequest request) {
        Barmaker barmaker = new Barmaker();
        barmaker.setName(request.name());
        barmaker.setEmail(request.email());
        barmaker.setPasswordHash(passwordEncoder.encode(request.password()));

        return barmakerRepository.save(barmaker);
    }

    @Transactional(readOnly = true)
    public Barmaker login(BarmakerLoginRequest request) {
        Barmaker barmaker = barmakerRepository.findByEmail(request.email()).get();

        return barmaker;
    }

    @Transactional(readOnly = true)
    public Barmaker getBarmaker(UUID id) {
        return barmakerRepository.findById(id).get();
    }
}