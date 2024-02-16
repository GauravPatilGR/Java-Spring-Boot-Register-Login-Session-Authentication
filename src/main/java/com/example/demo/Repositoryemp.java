package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositoryemp extends JpaRepository<empdata, Integer>
{

	
 empdata findByEmailAndPassword(String email,String password);
}
