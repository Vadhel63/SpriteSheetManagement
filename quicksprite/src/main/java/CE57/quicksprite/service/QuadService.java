package CE57.quicksprite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import CE57.quicksprite.dao.QuadDao;
import CE57.quicksprite.entity.Quad;

@Service
public class QuadService {
	
	private QuadDao quadDao;
	
	@Autowired
	public QuadService(QuadDao quadDao)
	{
		this.quadDao = quadDao;
	}
	
	@Transactional
	public Quad save(Quad newQuad)
	{
		return quadDao.save(newQuad);
	}
	
	@Transactional
	public void deleteByParentImage(int sID)
	{
		quadDao.deleteByParentImage(sID);
	}
}
