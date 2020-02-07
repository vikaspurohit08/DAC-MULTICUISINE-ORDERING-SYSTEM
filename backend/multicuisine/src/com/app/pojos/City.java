package com.app.pojos;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "cities")
public class City 
{

	private Integer c_id;
	private String code;
	private String city;
	
	private State state;
	@JsonIgnore
	private List<Locations> location;

	@OneToMany(mappedBy = "city",cascade = CascadeType.PERSIST,orphanRemoval = true)
	public List<Locations> getLocation() {
		return location;
	}

	public void setLocation(List<Locations> location) {
		this.location = location;
	}

	public City() {
		System.out.println("inside city ctor");
	}
	
	public City(String code, String city) {
		this.code = code;
		this.city = city;
	}
	@Id
	@Column(name = "city_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getC_id() {
		return c_id;
	}

	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}
	@Column(length = 10,nullable = false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(length=50,nullable = false)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	@ManyToOne
	@JoinColumn(name = "state_id")
		public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}



	@Override
	public String toString() {
		return "City [c_id=" + c_id + ", code=" + code + ", city=" + city + "]";
	}

	public void addLocations(Locations location)
	{
		this.location.add(location);
		location.setCity(this);
	}
	public void deleteLocations(Locations location)
	{
		this.location.remove(location);
		location.setCity(null);
	}
	
	
}
