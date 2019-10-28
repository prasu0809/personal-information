package com.personalinformation.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.personalinformation.model.PersonalInformation;
import com.personalinformation.repository.PersonalInformationRepository;


@Service
public class PersonalInformationDAO {
	
	@Autowired
	PersonalInformationRepository personalInfoRepo;

	// saves the data to database
	public PersonalInformation save(PersonalInformation personalInfo) {
		return personalInfoRepo.save(personalInfo);
		
	}
	
	// it retrieves all the Information from the database
	public List<PersonalInformation> findAll(){
		return personalInfoRepo.findAll();
		
	}
	
	// retrieves data based on id
	public PersonalInformation findone(long id) {
		return personalInfoRepo.findOne(id);
		
	}
	
	// deletes the data from the database
	public void delete(PersonalInformation personalInfo) {
		personalInfoRepo.delete(personalInfo);
	}
	
	

}
