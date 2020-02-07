package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "states")
public class State 
{
	private Integer s_id;
	private String state;
	@JsonIgnore
	private List<City> cities=new ArrayList<City>();
	
	//paramless ctor
	public State() 
	{
		System.out.println("Inside state ctor");
	}
	//param ctor
	public State(String state) 
	{
		super();
		this.state = state;
	}
	
	@Id
	@Column(name ="state_id")
	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}
	
	@Column(name="state",length = 50,nullable = false,unique = true)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@OneToMany(mappedBy = "state",cascade = CascadeType.PERSIST,orphanRemoval = true)
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	@Override
	public String toString() {
		return "State [s_id=" + s_id + ", state=" + state + "]";
	}

}
