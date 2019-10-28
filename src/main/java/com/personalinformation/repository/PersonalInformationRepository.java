package com.personalinformation.repository;
import com.personalinformation.model.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Long>{

}
