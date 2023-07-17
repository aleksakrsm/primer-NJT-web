package it.engineering.spring.mvc.ds.service.impl;

import it.engineering.spring.mvc.ds.dao.CityDao;
import it.engineering.spring.mvc.ds.dao.impl.JpaCityDaoImpl;
import java.util.List;
import it.engineering.spring.mvc.ds.dto.CityDto;
import it.engineering.spring.mvc.ds.entity.CityEntity;
import it.engineering.spring.mvc.ds.service.CityService;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cityServiceImpl")
@Transactional
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public void save(CityDto cityDto) throws Exception {
        cityDao.save(new CityEntity(cityDto.getNumber(), cityDto.getName()));
    }

    @Override
    public List<CityDto> getAll() throws Exception {
        return   cityDao.getAll().stream().map(city -> {return new CityDto(city.getNumber(),city.getName());}).collect(Collectors.toList());
    }

    @Override
    public CityDto findById(Long number) throws Exception {
		CityEntity cityEntity = cityDao.findbyId(number);
		return new CityDto(cityEntity.getNumber(),cityEntity.getName());
    }

}
