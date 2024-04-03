package domain.entities;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import domain.enums.WorkerLevel;

@Entity
@Table(name = "workers")
public class Worker implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_worker")
	private Integer id;
	
	private String name;
	
	@Enumerated(value =  EnumType.STRING)
	private WorkerLevel level;
	
	private Double baseSalary;
	
	@OneToMany(mappedBy = "worker", cascade = CascadeType.ALL)
	List<HourContract> contracts = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "iddepartment")
	Department department;

	public Worker() {
	}

	public Worker(String name, WorkerLevel level, Double baseSalary, List<HourContract> contracts,
			Department department) {
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
		this.contracts = contracts;
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorkerLevel getLevel() {
		return level;
	}

	public void setLevel(WorkerLevel level) {
		this.level = level;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public List<HourContract> getContracts() {
		return contracts;
	}

	public void setContracts(List<HourContract> contracts) {
		this.contracts = contracts;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public void addContract(HourContract contract) {
		contracts.add(contract);
	}
	
	public void removeContract(HourContract contract) {
		contracts.remove(contract);
	}
	
	public Double income(int year, int month) {
		Double income = 0.0;
		YearMonth yearMonthFilter = YearMonth.of(year, month);
		for (HourContract contract : contracts) {
			YearMonth ym = YearMonth.of(contract.getDate().getYear(), contract.getDate().getMonthValue());
			if (yearMonthFilter.equals(ym)) {
				income += contract.getTotalValue();
			}
		}
		return income + baseSalary;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Worker other = (Worker) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "id: " + id + ", name: " + name + ", level: " + level + ", baseSalary: " + baseSalary
				+ ", contracts: " + contracts+ ", department: " + department;
	}
	
	
	
	
	
	

}
