package com.registration.service;

import com.registration.entity.Registration;
import com.registration.payload.RegistrationDto;
import com.registration.repository.RegistrationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    private RegistrationRepository registrationRepository;


    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }



    @Override
    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        Registration registration = mapToEntity(registrationDto);
        Registration savedEntity = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(savedEntity);
        return dto;
    }

    @Override
    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public RegistrationDto updateRegistration(long id, RegistrationDto registrationDto) {
        Optional<Registration> opReg = registrationRepository.findById(id);
        Registration registration = opReg.get();

        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setMobile(registrationDto.getMobile());

        Registration savedEntity = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(registration);
        return dto;
    }

    @Override
    public List<RegistrationDto> getAllRegistration(int pageNo, int pageSize, String sortBy) {
        //List<Registration> registrations = registrationRepository.findAll();
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Registration> all = registrationRepository.findAll(pageable);
        List<Registration> registrations = all.getContent();
        List<RegistrationDto> registrationDtos = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return registrationDtos;
    }

    @Override
    public RegistrationDto getRegistrationById(long id) {
        registrationRepository.findById(id).
                orElseThrow(
                        () -> new ResolutionException("Registration not found for id:" + id)
                );
        return null;
    }

    Registration mapToEntity(RegistrationDto dto){

        Registration entity = new Registration();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        return entity;
    }

    RegistrationDto mapToDto(Registration registration){

        RegistrationDto dto = new RegistrationDto();
        dto.setId(registration.getId());
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());
        return dto;
    }



}
