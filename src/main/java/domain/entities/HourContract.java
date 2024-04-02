package domain.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hour_contract")
public class HourContract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_hour_contract")
	private Integer id;
	
	private LocalDate date;
	
	@Column(name = "value_per_hour")
	private Double valuePerHour;
	
	private Integer hours;
	
	@ManyToOne
	@JoinColumn(name = "worker")
	private Worker worker;

	public HourContract() {
	}

	public HourContract(LocalDate date, Double valuePerHour, Integer hours) {
		this.date = date;
		this.valuePerHour = valuePerHour;
		this.hours = hours;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getValuePerHour() {
		return valuePerHour;
	}

	public void setValuePerHour(Double valuePerHour) {
		this.valuePerHour = valuePerHour;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}
	
	public Worker getWorker() {
		return worker;
	}

	public Double getTotalValue() {
		return valuePerHour * hours;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HourContract other = (HourContract) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "HourContract [id=" + id + ", date=" + date + ", valuePerHour=" + valuePerHour + ", hours=" + hours
				+ "]";
	}

	
	
	
	

}
