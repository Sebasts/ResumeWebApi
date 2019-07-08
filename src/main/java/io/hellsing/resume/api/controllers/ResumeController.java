package io.hellsing.resume.api.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import io.hellsing.resume.api.service.ResumeService;
import io.hellsing.resume.entities.Resume;



@RestController
public class ResumeController {
    
	@Autowired
	ResumeService resumeService;
	
	@Autowired
	ObjectMapper objectMapper;
	
    @RequestMapping(value = "/api/listAll", method = RequestMethod.GET )
    public String listAllResumes() {
    	
        ObjectMapper objectMapper = new ObjectMapper();

	        try {
				return objectMapper.writeValueAsString(resumeService.listAll());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "e";
			}
    	
    }
    
    @RequestMapping(value = "/api/findByOwner/{owner}", method = RequestMethod.GET )
    public String findResumesByOwner(@PathVariable String owner) {
    	
        ObjectMapper objectMapper = new ObjectMapper();

	        try {
				return objectMapper.writeValueAsString(resumeService.getResumesByOwner(owner));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "e";
			}
    	
    }
    
    @RequestMapping(value = "/api/findActive/{owner}", method = RequestMethod.GET )
    public String findActiveResumeByOwner(@PathVariable String owner) {
    	
        ObjectMapper objectMapper = new ObjectMapper();
        
	        try {
				return objectMapper.writeValueAsString(resumeService.getActiveResumeByOwner(owner));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "e";
			}
    	
    }
    
    @RequestMapping(value = "/api/update", method = RequestMethod.PUT )
    @ResponseBody
    public String updateResume(@RequestBody Resume tempResume) {
    	
        ObjectMapper objectMapper = new ObjectMapper();
       // Resume tempResume;
		try {
			//tempResume = objectMapper.readValue(resume, Resume.class);
		
        System.out.println(tempResume);
		 try {
				return objectMapper.writeValueAsString(resumeService.updateResume(tempResume));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "e";
			}
		} catch(Exception e ) {
			
		}
    	
		return null;
    }
    
    
}
