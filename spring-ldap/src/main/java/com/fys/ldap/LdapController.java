package com.fys.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import java.util.Set;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@RestController
public class LdapController {

  @Autowired
  private LdapTemplate ldapTemplate;

  @Autowired
  private PersonRepository personRepository;

  @GetMapping("/ldap/all")
  public void findAll() throws Exception {
    personRepository.findAll()
        .forEach( p -> System.out.print(p) );
  }

  @GetMapping("/ldap/uid")
  public void findAllByUid() throws Exception {
    personRepository.findByUid("hdfs")
        .forEach( p -> System.out.println(p) );
  }

  @GetMapping("/ldap/template")
  public String findByCn(@RequestParam  String cn) {
    Person p =
        ldapTemplate.findOne(query().where("cn").is(cn),Person.class);

    System.out.println("Found, "+ p.toString());
    return p.toString();
  }
}
