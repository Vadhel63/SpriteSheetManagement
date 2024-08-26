package CE57.quicksprite.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CE57.quicksprite.entity.Quad;
import CE57.quicksprite.entity.Spritesheet;
import CE57.quicksprite.dao.QuadDao;
import CE57.quicksprite.dao.SpritesheetDao;

@Service
public class SpritesheetService
{
	
	private SpritesheetDao spritesheetDao;
	private QuadDao quadDao;
	
	@Autowired
	public SpritesheetService(SpritesheetDao spritesheetDao, QuadDao quadDao)
	{
		this.spritesheetDao = spritesheetDao;
		this.quadDao = quadDao;
	}
	
	@Transactional
	public List<Spritesheet> findByProject(int pID) 
	{
		return spritesheetDao.findByProject(pID);
	}

	@Transactional
	public Spritesheet findById(int sID) 
	{	
		return spritesheetDao.findById(sID);
	}

	@Transactional
	public Spritesheet save(Spritesheet newSpritesheet) 
	{
		newSpritesheet = spritesheetDao.save(newSpritesheet);
		
		quadDao.deleteByParentImage(newSpritesheet.getId());
		
		try 
		{
	      // Decode base64 string into byte array
	      byte[] imageBytes = Base64.getDecoder().decode(newSpritesheet.getImageData());
	
	      // Create ByteArrayInputStream to read image from byte array
	      ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
	
	      // Read the image
	      BufferedImage image = ImageIO.read(bis);
	
	      // Get dimensions of the image
	      int imageWidth = image.getWidth();
	      int imageHeight = image.getHeight();
	      int tileWidth = newSpritesheet.getSpriteWidth();
	      int tileHeight = newSpritesheet.getSpriteHeight();
	      
	      for(int y=0; y<imageHeight ; y+=tileHeight)
			{
				for(int x=0; x<imageWidth; x+=tileWidth)
				{
					Quad newQuad = new Quad(x,y,tileWidth,tileHeight, newSpritesheet);
					quadDao.save(newQuad);
				}
			}
	
	      // Close the ByteArrayInputStream
	      bis.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return newSpritesheet;
	}

	@Transactional
	public void deleteById(int sID) 
	{
		spritesheetDao.deleteById(sID);
	}

}

