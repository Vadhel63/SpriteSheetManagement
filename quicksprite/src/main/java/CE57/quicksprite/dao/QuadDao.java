package CE57.quicksprite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import CE57.quicksprite.entity.Quad;

@Repository
public class QuadDao {
	
	private EntityManager entityManager;
	
	@Autowired
	public QuadDao(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}
	
	public Quad save(Quad newQuad)
	{
		return entityManager.merge(newQuad);
	}
	
	public void deleteByParentImage(int sID)
	{
		Query theQuery = entityManager.createQuery("delete from Quad where parentImage.id=:sID");
		theQuery.setParameter("sID", sID);
		theQuery.executeUpdate();
	}
}
