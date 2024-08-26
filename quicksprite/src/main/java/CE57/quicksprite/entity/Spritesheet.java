package CE57.quicksprite.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="spritesheet")
public class Spritesheet
 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="name")
    private String name;

    @Column(name="image_data", length=100000000)
    private String imageData;
    
    @Column(name="sprite_width")
    private int spriteWidth;
    
    @Column(name="sprite_height")
    private int spriteHeight;
    
    @JsonIgnore
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="project_id")
    private Project project;	
    
    @OneToMany(mappedBy = "parentImage", cascade=CascadeType.ALL)
    private List<Quad> quads;
        
    // Getters and setters

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
	
	public int getSpriteWidth() {
		return spriteWidth;
	}

	public void setSpriteWidth(int spriteWidth) {
		this.spriteWidth = spriteWidth;
	}

	public int getSpriteHeight() {
		return spriteHeight;
	}

	public void setSpriteHeight(int spriteHeight) {
		this.spriteHeight = spriteHeight;
	}
	

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Quad> getQuads() {
		return quads;
	}

	public void setQuads(List<Quad> quads) {
		this.quads = quads;
	}

	@Override
	public String toString() {
		return "Spritesheet [id=" + id + ", name=" + name + "]";
	}
}
