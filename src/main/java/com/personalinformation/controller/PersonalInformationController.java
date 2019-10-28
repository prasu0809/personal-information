package com.personalinformation.controller;

import com.personalinformation.dao.PersonalInformationDAO;
import com.personalinformation.model.PersonalInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/personalInfo")
public class PersonalInformationController {

    @Autowired
    PersonalInformationDAO personalInfoDao;

    // save personal information to Database
    @PostMapping("/createInfo")
    public PersonalInformation createPersonalInfo(@Valid @RequestBody PersonalInformation personalInfo) {
        return personalInfoDao.save(personalInfo);

    }

    //to retrieve All personal informations
    @GetMapping("/allInformation")
    public List<PersonalInformation> getAllInformation() {
        return personalInfoDao.findAll();
    }

    // Retrieve personal information by ID
    @GetMapping("/getPersonalInfoByID/{id}")
    public ResponseEntity<PersonalInformation> getPersonalInformationByID(@PathVariable(value = "id") long id) {
        PersonalInformation personalInfo = personalInfoDao.findone(id);

        if (personalInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(personalInfo);

    }

    //update personal information by ID
    @PutMapping("/updateInfo/{id}")
    public ResponseEntity<PersonalInformation> updatePersonalInformation(@PathVariable(value = "id") long id, @Valid @RequestBody PersonalInformation infoDetails) {
        PersonalInformation personalInfo = personalInfoDao.findone(id);
        if (personalInfo == null) {
            return ResponseEntity.notFound().build();
        } else {
            personalInfo.setName(infoDetails.getName());
            personalInfo.setLastName(infoDetails.getLastName());
            personalInfo.setAddress(infoDetails.getAddress());
            personalInfo.setHairColor(infoDetails.getHairColor());
            personalInfo.setPhoneNum(infoDetails.getPhoneNum());


            PersonalInformation updatePersonalInfo = personalInfoDao.save(personalInfo);
            return ResponseEntity.ok().body(updatePersonalInfo);
        }

    }


    //Delete personal information

    @DeleteMapping("/deletePersonalInfo/{id}")
    public ResponseEntity<PersonalInformation> deletePersonalInfo(@PathVariable(value = "id") long id) {
        PersonalInformation personalInfo = personalInfoDao.findone(id);
        if (personalInfo == null) {
            return ResponseEntity.notFound().build();
        }
        personalInfoDao.delete(personalInfo);
        return ResponseEntity.ok().build();
    }
}
