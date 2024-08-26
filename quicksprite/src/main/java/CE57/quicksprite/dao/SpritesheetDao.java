package CE57.quicksprite.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import CE57.quicksprite.entity.*;

@Repository
public class SpritesheetDao
{
	private EntityManager entityManager;
	
	@Autowired
	public SpritesheetDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public List<Spritesheet> findByProject(int pID) 
	{
		TypedQuery<Spritesheet> theQuery = entityManager.createQuery("from Spritesheet where project.id=:pID ", Spritesheet.class);
		theQuery.setParameter("pID",pID);
		List<Spritesheet> spritesheets=theQuery.getResultList();
		return spritesheets;
	}

	
	public Spritesheet findById(int sID) 
	{	
		return entityManager.find(Spritesheet.class, sID);
	}

	
	public Spritesheet save(Spritesheet newSpritesheet) 
	{
		return entityManager.merge(newSpritesheet);	
	}

	
	public void deleteById(int sID) 
	{
		entityManager.remove(entityManager.find(Spritesheet.class, sID));
	}

}

