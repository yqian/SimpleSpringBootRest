package org.yqian.SimpleSpringBootRest.model;
import javax.persistence.Entity;import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})@Entitypublic class User {
 @Id private int id;
 private String name;
 private String role;
 public int getId() {  return id; }
 public void setId(int id) {  this.id = id; }
 public String getName() {  return name; }
 public void setName(String name) {  this.name = name; }
 public String getRole() {  return role; }
 public void setRole(String role) {  this.role = role; }
 public boolean equals(Object o) {  if (o == null) {   return false;  }  if (!(o instanceof User)) {   return false;  }  if (this == o) {   return true;  }  User u = (User) o;  return this.id == u.id; }
 public int hashcode() {  int prime = 37;  int result = prime * this.id;  result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());  result = prime * result + ((this.role == null) ? 0 : this.role.hashCode());  return result; }
 public String toString() {  return "User ID: ".concat(Integer.toString(id)).concat(" Name: ").concat(name).concat(" Role: ").concat(role); }}
