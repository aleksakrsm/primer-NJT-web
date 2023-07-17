package it.engineering.spring.mvc.ds.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CityDto implements Serializable{
	private static final long serialVersionUID = -8838870410328204377L;
	@NotNull(message = "Number is required...")
	private Long number;
	@Size(min = 3,max = 10,message = "Betweeen 3 and 10...")
	@NotEmpty(message = "Name is required...")
	private String name;

	public CityDto() {
		System.out.println("====================================================");
		System.out.println("Kreiran je objekat klase CityDto.");
		System.out.println("====================================================");
			
	}

	public CityDto(Long number, String name) {
		this.number = number;
		this.name = name;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CityDto [number=" + number + ", name=" + name + "]";
	}
	
	
}
