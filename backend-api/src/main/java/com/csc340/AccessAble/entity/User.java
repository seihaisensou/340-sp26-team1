package com.csc340.AccessAble.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  private String email;
  private String password;
  private String role;

  public User() {
  }

  public User(String email, String password, String role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public Long getUserId() {
    return userId;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getRole() {
    return role;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password){
    this.password = password;
  }

  public void setRole(String role){
    this.role = role;
  }
}
