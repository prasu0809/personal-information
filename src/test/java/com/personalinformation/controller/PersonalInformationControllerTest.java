package com.personalinformation.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalinformation.dao.PersonalInformationDAO;
import com.personalinformation.model.PersonalInformation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PersonalInformationControllerTest {

    @InjectMocks
    PersonalInformationController personalInfoController;

    @Mock
    PersonalInformationDAO personalInfoDAO;

    ObjectMapper mapper = new ObjectMapper();

    MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(personalInfoController).build();
    }

    // test case - create personalInformation
    @Test
    public void testCreatePersonalInfo() throws Exception {
        when(personalInfoDAO.save(any(PersonalInformation.class))).thenReturn(getPersonalInfo());
        String personalInfo = mapper.writeValueAsString(getPersonalInfo());
        MockHttpServletResponse response = mockMvc.perform(post("/personalInfo/createInfo").contentType("application/json").content(personalInfo))
                .andExpect(status().isOk()).andReturn().getResponse();
        PersonalInformation resultPersonalInfo = getResultPersonalInfo(response);
        assertEquals(getPersonalInfo().getName(), resultPersonalInfo.getName());
        assertEquals(getPersonalInfo().getPhoneNum(), resultPersonalInfo.getPhoneNum());
    }

    // test case - retreive personal information by ID
    @Test
    public void getEmployeeByIdTest() throws Exception {
        when(personalInfoDAO.findone(1)).thenReturn(getPersonalInfo());
        MockHttpServletResponse response = mockMvc.perform(get("/personalInfo/getPersonalInfoByID/{id}", 1))
                        .andExpect(status().isOk())
                        .andReturn().getResponse();
        PersonalInformation resultPersonalInfo = getResultPersonalInfo(response);
        assertEquals(getPersonalInfo().getName(), resultPersonalInfo.getName());
        assertEquals(getPersonalInfo().getPhoneNum(), resultPersonalInfo.getPhoneNum());
    }

    @Test
    public void testFindAll() throws Exception {

        List<PersonalInformation> personalInformationList = new ArrayList<PersonalInformation>();
        personalInformationList.add(getPersonalInfo());

        when(personalInfoDAO.findAll()).thenReturn(personalInformationList);
        MockHttpServletResponse response = mockMvc.perform(get("/personalInfo/allInformation"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        List<PersonalInformation> allPersonalInfo = getAllPersonalInfo(response);
        assertEquals(1, allPersonalInfo.size());
    }

    @Test
    public void updatePersonalInformation() throws Exception {
        PersonalInformation personalInformation = getPersonalInfo();
        String updatedPersonalInformation = mapper.writeValueAsString(getUpdatedPersonalInfo());
        when(personalInfoDAO.findone(1)).thenReturn(personalInformation);
        when(personalInfoDAO.save(any(PersonalInformation.class))).thenReturn(getUpdatedPersonalInfo());
        MockHttpServletResponse response = mockMvc.perform(put("/personalInfo/updateInfo/{id}",1)
                .contentType("application/json").content(updatedPersonalInformation))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        PersonalInformation resultPersonalInfo = getResultPersonalInfo(response);
        assertEquals("Kothas", resultPersonalInfo.getLastName());
        assertEquals("Brown", resultPersonalInfo.getHairColor());
    }

    @Test
    public void deletePersonalInformation() throws Exception {
        when(personalInfoDAO.findone(1)).thenReturn(getPersonalInfo());
        mockMvc.perform(delete("/personalInfo//deletePersonalInfo/{id}",1))
                .andExpect(status().isOk());
        verify(personalInfoDAO).delete(any(PersonalInformation.class));
    }

    private PersonalInformation getResultPersonalInfo(MockHttpServletResponse response) throws IOException {
        return mapper.readValue(response.getContentAsString(), PersonalInformation.class);
    }

    private List<PersonalInformation> getAllPersonalInfo(MockHttpServletResponse response) throws IOException {
        return mapper.readValue(response.getContentAsString(), new TypeReference<List<PersonalInformation>>() {
        });
    }

    private PersonalInformation getPersonalInfo() {
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setId(1l);
        personalInformation.setName("Prasanna");
        personalInformation.setLastName("Kotha");
        personalInformation.setAddress("Chile");
        personalInformation.setPhoneNum(123345678);
        personalInformation.setHairColor("Black");
        return personalInformation;
    }

    private PersonalInformation getUpdatedPersonalInfo() {
        PersonalInformation updatedPersonalInformation = getPersonalInfo();
        updatedPersonalInformation.setLastName("Kothas");
        updatedPersonalInformation.setHairColor("Brown");
        return updatedPersonalInformation;
    }
}
