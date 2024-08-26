package CE57.quicksprite.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CE57.quicksprite.entity.*;
import CE57.quicksprite.service.*;

@RestController
@RequestMapping("api/")
public class MainController {
	
	private ProjectService projectService;
	private SpritesheetService spritesheetService;
	
	@Autowired
	public MainController(ProjectService projectService, SpritesheetService spritesheetService)
	{
		this.projectService = projectService;
		this.spritesheetService = spritesheetService;
	}
	
	
	@GetMapping("projects")
	public List<Project> listAllProjects()
	{
		return projectService.findAll();
	}
	
	@GetMapping("projects/{pID}")
	public  Project getProject(@PathVariable int pID)
	{
		return projectService.findById(pID);
	}
	
	@GetMapping("projects/{pID}/spritesheets")
	public List<Spritesheet> listAllSpritesheets(@PathVariable int pID)
	{
		return spritesheetService.findByProject(pID);
	}
	
	@GetMapping("projects/{pID}/spritesheets/{sID}")
	public Spritesheet getSpritesheet(@PathVariable Long pID, @PathVariable int sID)
	{
		return spritesheetService.findById(sID);
	}
	
	
	@PostMapping("/projects")
	public Project createProject(@RequestBody Project newProject) 
	{
		return projectService.save(newProject);
	}
	
	@PostMapping("/projects/{pID}/spritesheets")
	public Spritesheet createSpritesheet(@PathVariable int pID, @RequestBody Spritesheet newSpritesheet)
	{
		Project pj = projectService.findById(pID);
		newSpritesheet.setProject(pj);
		return spritesheetService.save(newSpritesheet);
	}
	
	@PutMapping("projects/{pID}")
	public Project updateProject(@RequestBody Project updatedProject)
	{
		return projectService.save(updatedProject);
	}
	
	@PutMapping("projects/{pID}/spritesheets/{sID}")
	public Spritesheet updateSpritesheet(@PathVariable int pID, @RequestBody Spritesheet updatedSpritesheet)
	{
		Project pj = projectService.findById(pID);
		updatedSpritesheet.setProject(pj);
		return spritesheetService.save(updatedSpritesheet);
	}
	
	@DeleteMapping("projects/{pID}")
	public void deleteProject(@PathVariable int pID)
	{
		projectService.deleteById(pID);
	}
	
	@DeleteMapping("projects/{pID}/spritesheets/{sID}")
	public void deleteSpritesheet(@PathVariable int sID)
	{
		spritesheetService.deleteById(sID);
	}
}

