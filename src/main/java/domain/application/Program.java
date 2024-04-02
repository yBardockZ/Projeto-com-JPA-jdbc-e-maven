package domain.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		menu();

	}

	public static void menu() {

		int opcao;

		do {

			System.out.println("\nMenu:");
			System.out.println("1. Check list of workers");
			System.out.println("2. Register a worker");
			System.out.println("3. Check income");
			System.out.println("4. Exit");

			System.out.print("Choose an option: ");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
					System.out.println("show");
					
				break;

			case 2:

				try {

					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

					em.getTransaction().begin();

					Worker worker = new Worker();

					System.out.print("\nEnter department's name: ");
					sc.nextLine();
					String departmentName = sc.nextLine();

					Department department = new Department(departmentName);
					em.persist(department);

					worker.setDepartment(department);

					System.out.println("Enter worker data: ");

					System.out.print("Name: ");
					String workerName = sc.nextLine();
					worker.setName(workerName);

					System.out.print("Level: ");
					String levelString = sc.nextLine();
					worker.setLevel(WorkerLevel.valueOf(levelString));

					System.out.print("Base salary: ");
					Double baseSalary = sc.nextDouble();
					worker.setBaseSalary(baseSalary);

					System.out.print("How many contracts to this worker? ");
					sc.nextLine();
					Integer num = sc.nextInt();

					if (num > 0) {
						for (int i = 0; i < num; i++) {
							System.out.println("\nEnter contract #" + (i + 1) + " data:");

							System.out.print("Date (dd/mm/yyyy): ");
							sc.nextLine();
							String dateString = sc.nextLine();

							System.out.print("Value per hour: ");
							double valuePerHour = sc.nextDouble();

							System.out.print("Duration (hours): ");
							int duration = sc.nextInt();

							LocalDate date = LocalDate.parse(dateString, fmt);
							HourContract contract = new HourContract(date, valuePerHour, duration);
							contract.setWorker(worker);

							em.persist(contract);
							worker.addContract(contract);
						}
					}

					em.persist(worker);
					em.getTransaction().commit();

					System.out.println("Successfully registered");

				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}

				break;

			case 3:
				System.out.println("Você escolheu a opção 3.");

				break;

			case 4:
				System.out.println("Saindo...");
				break;

			default:
				System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
			}

		} while (opcao != 4);

		sc.close();
		em.close();

	}

}