package io.hellsing.resume.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.hellsing.resume.entities.Resume;

@Service
public interface ResumeService {
	
	List<Resume> listAll();
	List<Resume> getResumesByOwner(String owner);
	Resume getActiveResumeByOwner(String owner);
	boolean deleteResumeById(int id);
	Resume updateResume(Resume resume);
	Resume getResumeById(Resume resume);
	Resume getResumeById(int id);

}
