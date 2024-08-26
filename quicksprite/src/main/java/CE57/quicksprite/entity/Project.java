package CE57.quicksprite.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="project",cascade=CascadeType.ALL)
	private List<Spritesheet> spritesheets;

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

	public List<Spritesheet> getSpritesheets() {
		return spritesheets;
	}

	public void setSpritesheets(List<Spritesheet> spritesheets) {
		this.spritesheets = spritesheets;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + "]";
	}
}
