package CE57.quicksprite.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import CE57.quicksprite.entity.Project;

@Repository
public class ProjectDao{
	
	private EntityManager entityManager;
	
	@Autowired
	public ProjectDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Project> findAll() 
	{
		TypedQuery<Project> theQuery = entityManager.createQuery("from Project", Project.class);
		List<Project> projects =theQuery.getResultList();
		return projects;
	}

	public Project findById(int pID) 
	{
		Project project =  entityManager.find(Project.class, pID);
		return project;
	}

	public Project save(Project newProject) 
	{
		return entityManager.merge(newProject);
	}

	public void deleteById(int pID) 
	{	
		entityManager.remove(entityManager.find(Project.class, pID));
	}
	
	

}
