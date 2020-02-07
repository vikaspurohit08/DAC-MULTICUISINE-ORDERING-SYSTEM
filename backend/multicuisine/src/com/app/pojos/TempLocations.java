package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "temploc")
public class TempLocations {
	private Integer l_id;
	private String Street;
	private String Area;
	
	private City tempcity;
	
	
	
	public TempLocations() {
		System.out.println("inside templocations table");
	}

	public TempLocations(Integer l_id, String street, String area) {
		super();
		this.l_id = l_id;
		Street = street;
		Area = area;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getL_id() {
		return l_id;
	}

	public void setL_id(Integer l_id) {
		this.l_id = l_id;
	}
	@Column(name = "street",length = 50,nullable = false)
	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}
	@Column(name = "area",length = 50,nullable = false)
	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	public City getTempcity() {
		return tempcity;
	}

	public void setTempcity(City tempcity) {
		this.tempcity = tempcity;
	}



	@Override
	public String toString() {
		return "TempLocations [l_id=" + l_id + ", Street=" + Street + ", Area=" + Area + "]";
	}
	
	
}