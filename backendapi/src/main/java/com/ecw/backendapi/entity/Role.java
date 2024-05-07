package com.ecw.backendapi.entity;
import com.ecw.backendapi.enums.ERole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Enumerated(EnumType.STRING)
  private ERole name;

  public Role() {

  }

  public Role(ERole name) {
    this.name = name;
  }

public ERole getName() {
	return name;
}

public void setName(ERole name) {
	this.name = name;
}

}