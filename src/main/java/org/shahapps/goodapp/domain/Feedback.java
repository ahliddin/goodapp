package org.shahapps.goodapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Feedback implements Serializable
{
   private static final long serialVersionUID = 2L;

   @Id
   @GeneratedValue
   private Long id;

   @NotNull
   @Size(min = 1, max = 30)
   @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
   private String name;

   @NotNull
   @NotEmpty
   @Size(min = 1, max = 50)
   @Email
   private String email;

   @NotNull
   @NotEmpty
   @Size(min = 1, max = 250)
   @Column (name="comment")
   private String comment;
   
   @NotNull
   @Column (name="spam")
   private Integer spam;
   
   public Long getId() {
      return id;
   }
   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   
   public String getComment () {
	   return comment;
   }
   public void setComment (String comment) {
	   this.comment = comment;
   }
  
   public Integer getSpam () {
	   return spam;
   }
   public void setSpam (Integer spam) {
	   this.spam = spam;
   }
}
