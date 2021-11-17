package tn.esprit.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTest {
	@Autowired
	IEmployeService IEmployeService;
	@Autowired
	EmployeRepository EmployeRepository;

	private static final Logger l = LogManager.getLogger(EmployeeTest.class);
	private static final String NOM = "Ouertani"; 
	private static final String PRENOM = "Akrem"; 
	private static final String EMAIL = "akremM@esprit.tn"; 

	@Test
	public void verifTaille() {
		List<Employe> employes = IEmployeService.getAllEmployes();
		assertTrue(!employes.isEmpty());
		String s = "Taille: " + employes.size();
		l.info(s);
	}
	
	@Test
	public void testAjout() {
		long i = IEmployeService.getNombreEmployeJPQL();

		Employe emp = new Employe();
		emp.setNom(NOM);
		emp.setPrenom(PRENOM);
		emp.setEmail(EMAIL);
		emp = IEmployeService.ajouterEmploye(emp);
		l.info("Nbr: " + IEmployeService.getNombreEmployeJPQL());
		assertEquals(i + 1, IEmployeService.getNombreEmployeJPQL());
		//EmployeRepository.delete(emp);
	}
	
	@Test
	public void testModif() {
		Employe emp = new Employe();
		emp.setNom(NOM);
		emp.setPrenom(PRENOM);
		emp.setEmail(EMAIL);
		emp = IEmployeService.ajouterEmploye(emp);
		emp.setPrenom("ahmed");
		emp = EmployeRepository.save(emp);
		assertEquals("ahmed" ,emp.getPrenom());
		EmployeRepository.delete(emp);
	}
	
	@Test
	public void testSuppr() {
		Employe emp = new Employe();
		emp.setNom(NOM);
		emp.setPrenom(PRENOM);
		emp.setEmail(EMAIL);
		emp = IEmployeService.ajouterEmploye(emp);
		long i = IEmployeService.getNombreEmployeJPQL();
		EmployeRepository.delete(emp);
		assertEquals(i - 1, IEmployeService.getNombreEmployeJPQL());
		//commit pipeline test
		
	}
}
