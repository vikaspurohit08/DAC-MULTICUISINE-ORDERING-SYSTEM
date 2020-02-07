package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name="blockuser")
public class BlockUser {
	
	private Integer b_id;
	private int u_id;
	private String email;
	private String contact;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="blockid")
	public Integer getB_id() {
		return b_id;
	}
	public void setB_id(Integer b_id) {
		this.b_id = b_id;
	}
	
	@Column(name="userid")
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	
	@Column(name="block_email",length = 50,nullable = false,unique = true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="contact",length = 15,nullable = false)
	public String getContact() {
		return contact;
	}
	
	
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
}
