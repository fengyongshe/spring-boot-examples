package com.fys.ldap;

import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;
import java.util.List;

public interface PersonRepository extends CrudRepository<Person,Name> {
  List<Person> findByUid(String uid);
  Person findByCn(String cn);
}
