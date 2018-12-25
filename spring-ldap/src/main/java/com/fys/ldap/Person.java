package com.fys.ldap;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(base = "dc=hadoop,dc=apache,dc=org", objectClasses = {"top","person"} )
@Data
public class Person {

  @Id
  private Name id;

  @DnAttribute(value = "ou")
  private String ou;

  @Attribute(name = "cn")
  private String cn;

  @Attribute(name = "uid")
  private String uid;

}
