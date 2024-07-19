package com.registration.service;

import com.registration.payload.RegistrationDto;

import java.util.List;

public interface RegistrationService {


    RegistrationDto createRegistration(RegistrationDto registrationDto);

    void deleteRegistration(long id);

    RegistrationDto updateRegistration(long id, RegistrationDto registrationDto);

    List<RegistrationDto> getAllRegistration(int pageNo, int pageSize, String sortBy);

    RegistrationDto getRegistrationById(long id);
}
