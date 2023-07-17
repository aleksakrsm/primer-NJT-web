package it.engineering.spring.mvc.ds.service;

import java.util.List;

import it.engineering.spring.mvc.ds.dto.CityDto;

public interface CityService {
	void save(CityDto cityDto) throws Exception;
	List<CityDto> getAll()throws Exception;
	CityDto findById(Long number)throws Exception;
}
