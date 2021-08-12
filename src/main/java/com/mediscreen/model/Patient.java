package com.mediscreen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "Given name is mandatory")
  private String given;

  @NotBlank(message = "Family Name is mandatory")
  private String family;

  @Pattern(regexp = "^[0-9]{4}(-)[0-9]{2}(-)[0-9]{2}$", message = "The birthdate has to get this format yyyy-mm-dd")
  private String dob;

  @NotBlank(message = "Sex is mandatory")
  @Pattern(regexp = "^(M|F){1}$", message = "The sex has to be F or M")
  private String sex;

  // postal address
  private String address;

  private String phone;

}
