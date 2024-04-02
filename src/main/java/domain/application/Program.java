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
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceU");
	private static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {
		Worker worker = new Worker();
		worker.setName("Alex");
		worker.setLevel(WorkerLevel.MID_LEVEL);
		worker.setBaseSalary(1200.00);
		worker.setContracts(Arrays.asList(new HourContract(LocalDate.of(2018, 8, 20), 50.00, 20),
				new HourContract(LocalDate.of(2018, 6, 13), 30.00, 18),
				new HourContract(LocalDate.of(2018, 8, 25), 80.00, 10)));
		worker.setDepartment(new Department("Design"));
		
		System.out.println(worker);
		System.out.println("income for 08/2018: " + worker.income(2018, 8));
	}

}
