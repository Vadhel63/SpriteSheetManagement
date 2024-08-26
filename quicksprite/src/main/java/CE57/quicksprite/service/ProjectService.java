package CE57.quicksprite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CE57.quicksprite.entity.Project;
import CE57.quicksprite.dao.ProjectDao;

@Service
public class ProjectService
{
	private ProjectDao projectDao;
	
	@Autowired
	public ProjectService(ProjectDao projectDao)
	{
		this.projectDao = projectDao;
	}
	
	@Transactional
	public List<Project> findAll() 
	{
		return projectDao.findAll();
	}

	@Transactional
	public Project findById(int pID) 
	{
		return projectDao.findById(pID);
	}

	@Transactional
	public Project save(Project newProject) 
	{
		return projectDao.save(newProject);
	}

	@Transactional
	public void deleteById(int pID) 
	{	
		projectDao.deleteById(pID);
	}
}
