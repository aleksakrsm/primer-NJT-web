package it.engineering.spring.mvc.ds.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import it.engineering.spring.mvc.ds.dao.CityDao;
import it.engineering.spring.mvc.ds.dto.CityDto;
import it.engineering.spring.mvc.ds.service.CityService;
@Service
public class CityServiceInMemory implements CityService {
	private List<CityDto> cities;
	
	public CityServiceInMemory() {
		cities = new ArrayList<CityDto>();
	}
	
	public void save(CityDto cityDto) throws Exception{
		cities.add(cityDto);
	}
	
	public List<CityDto> getAll()throws Exception{
		return cities;
	}
	public CityDto findById(Long number)throws Exception {
		return cities.stream().filter(city->city.getNumber().equals(number)).findAny().orElse(null);
	}
}
