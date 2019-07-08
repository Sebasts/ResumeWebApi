package io.hellsing.resume.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;

import io.hellsing.resume.api.service.ResumeService;
import io.hellsing.resume.entities.Resume;

@Component
public class ResumeServiceImpl implements ResumeService {
	
	private EntityManager entityManager;
	 static EntityManagerFactory factory = Persistence.createEntityManagerFactory("ResumePersistenceUnit");
	
	@Override
	public List<Resume> listAll() {
	        EntityManager em = factory.createEntityManager();
	        em.getTransaction().begin();	        
	       
	        List<Resume> resumes;
			try {
				resumes = em.createQuery(
					    "SELECT resume from Resume resume", Resume.class)
					    .getResultList();
			} catch (Exception e) {
				resumes = new ArrayList<>();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
	        em.close();
		return resumes;
	}

	@Override
	public List<Resume> getResumesByOwner(String owner) {
	        EntityManager em = factory.createEntityManager();

	        em.getTransaction().begin(); 
	       
	        List<Resume> resumes;
			try {
				resumes = em.createQuery(
					    "SELECT resume from Resume resume where resume.owner ='" + owner + "'", Resume.class)
					    .getResultList();
			} catch (Exception e) {
				resumes = new ArrayList<>();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        em.close();
		return resumes;
	}

	@Override
	public Resume getActiveResumeByOwner(String owner) {
		EntityManager em = factory.createEntityManager();

        em.getTransaction().begin(); 
       
        Resume resume;
		try {
			resume = em.createQuery(
				    "SELECT resume from resume resume where resume.isDefault = 1 and resume.owner ='" + owner + "'", Resume.class)
				    .getSingleResult();
		} catch (Exception e) {
			resume = new Resume();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        em.close();
	return resume;
	}

	@Override
	public boolean deleteResumeById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Resume updateResume(Resume resume) {
		EntityManager em = factory.createEntityManager();

        em.getTransaction().begin(); 
       
//        Resume updatedResume;
//        System.out.println(resume.getId() + " Is the ID of the resume being updated."); 
//      // updatedResume = this.entityManager.find(Resume.class, resume.getId());
//        updatedResume = getResumeById(resume.getId());
//        em.refresh(updatedResume);
//
//        System.out.println(updatedResume);
//        updatedResume.setAddress(resume.getAddress());
//        updatedResume.setDefault(resume.isDefault());
//        updatedResume.setEducation(resume.getEducation());
//        updatedResume.setIntro(resume.getIntro());
//        updatedResume.setOrganization(resume.getOrganization());
//        updatedResume.setOwner(resume.getOwner());
//        
//        updatedResume.setSkills(resume.getSkills());
//        
        resume.getSkills().forEach(sk -> {
        	if(sk.getKey().getSkill_Id() < 1) {
        		em.persist(sk);
        	}else {
        		em.merge(sk);
        	}
        });
        resume.getOrganization().getBulletPoints().forEach(bp -> {
        	if(bp.getKey().getBulletPointId() < 1) {
        		em.persist(bp);
        	}else {
        		em.merge(bp);
        	}
        });
        em.flush();
		em.getTransaction().commit();
		entityManager = factory.createEntityManager();

//        
//        em.refresh(updatedResume);
		try {
			entityManager.merge(resume);
			
		} catch (Exception e) {
		//	updatedResume = new Resume();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();
		em.close();
		return resume;
	}

	@Override
	public Resume getResumeById(Resume resume) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resume getResumeById(int id) {
		EntityManager em = factory.createEntityManager();

        em.getTransaction().begin(); 
       
        Resume resume;
		try {
			resume = em.find(Resume.class, id);
//					em.createQuery(
//				    "SELECT resume from Resume resume where resume.id=" + id, Resume.class)
//				    .getSingleResult();
		} catch (Exception e) {
			resume = new Resume();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		em.flush();
        em.close();
	return resume;
	}

}
