package domain.entities;

import java.time.LocalDate;
import java.util.Objects;

public class HourContract {
	
	private Integer id;
	private LocalDate date;
	private Double valuePerHour;
	private Integer hours;

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
	
	public Double getTotalValue() {
		return valuePerHour * hours;
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
		HourContract other = (HourContract) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "HourContract [id=" + id + ", date=" + date + ", valuePerHour=" + valuePerHour + ", hours=" + hours
				+ "]";
	}

	
	
	
	

}
