package com.registration.controller;


import com.registration.entity.Registration;
import com.registration.payload.RegistrationDto;
import com.registration.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;


    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //http://localhost:8080/api/v1/registration
    @PostMapping
    public ResponseEntity<RegistrationDto> createRegistration(@RequestBody RegistrationDto registrationDto){
        RegistrationDto regDto = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(regDto, HttpStatus.CREATED);
    }


    //http://localhost:8080/api/v1/registration?id=
    @DeleteMapping
    public  ResponseEntity<String> deleteRegistration(@RequestParam long id){
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Record deleted successfully", HttpStatus.OK);
    }


    //http://localhost:8080/api/v1/registration?id=
    @PutMapping
    public ResponseEntity<RegistrationDto> updateRegistration(
            @RequestParam long id,
            @RequestBody RegistrationDto registrationDto
            ){
        RegistrationDto registration = registrationService.updateRegistration(id, registrationDto);
        return new ResponseEntity<>(registration, HttpStatus.OK);
    }


    //http://localhost:8080/api/v1/registration?pageNo=0&pageSize=5&sortBy=
    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistration(
            @RequestParam(name = "pageNo", required = false,defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = "name") String sortBy
    ){
        List<RegistrationDto> dtos = registrationService.getAllRegistration(pageNo,pageSize,sortBy);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }



    @GetMapping("/byid")
    public ResponseEntity<RegistrationDto> getRegistrationById(@RequestParam long id){
        registrationService.getRegistrationById(id);
    }

}
