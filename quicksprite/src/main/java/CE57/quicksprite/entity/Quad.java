package CE57.quicksprite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="quad")
public class Quad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name="x_coordinate")
	private int x;
	
	@Column(name="y_coordinate")
	private int y;
	
	@Column(name="width")
	private int width;
	
	@Column(name="height")
	private int height;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="parent_spritesheet")
	private Spritesheet parentImage;
	
	public Quad() {}

	public Quad(int x, int y, int width, int height, Spritesheet parentImage) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.parentImage = parentImage;
	}

	@Override
	public String toString() {
		return "Quad [id=" + id + ", x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", parentImage="
				+ parentImage + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Spritesheet getParentImage() {
		return parentImage;
	}

	public void setParentImage(Spritesheet parentImage) {
		this.parentImage = parentImage;
	}
}

