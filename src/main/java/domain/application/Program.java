package domain.application;

import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.entities.Department;
import domain.entities.HourContract;
import domain.entities.Worker;
import domain.enums.WorkerLevel;

public class Program {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUN");
	private static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {
		
		em.getTransaction().begin();
		
		Department department = new Department("Design");
		
		em.persist(department);
		
		HourContract h1 = new HourContract(LocalDate.of(2018, 8, 20), 50.00, 20);
		HourContract h2 = new HourContract(LocalDate.of(2018, 6, 13), 30.00, 18);
		HourContract h3 = new HourContract(LocalDate.of(2018, 8, 25), 80.00, 10);
		
		Worker worker = new Worker();
		worker.setName("Alex");
		worker.setLevel(WorkerLevel.MID_LEVEL);
		worker.setBaseSalary(1200.00);
		
		h1.setWorker(worker);
		h2.setWorker(worker);
		h3.setWorker(worker);
		em.persist(h1);
		em.persist(h2);
		em.persist(h3);
		
		worker.setContracts(Arrays.asList(h1, h2, h3));
		worker.setDepartment(department);
		
		em.persist(worker);
		
		em.getTransaction().commit();
		
		em.close();
		
	}

}
