package domain.application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Program {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceU");
	private static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {
		System.out.println("Rodando...");
	}

}
