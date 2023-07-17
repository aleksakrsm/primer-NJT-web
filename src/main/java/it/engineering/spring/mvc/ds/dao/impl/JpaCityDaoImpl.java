package it.engineering.spring.mvc.ds.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.spring.mvc.ds.dao.CityDao;
import it.engineering.spring.mvc.ds.entity.CityEntity;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class JpaCityDaoImpl implements CityDao{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void save(CityEntity cityEntity) throws Exception {
        entityManager.persist(cityEntity);
    }

    @Override
    public CityEntity findbyId(long number) throws Exception {
        return entityManager.find(CityEntity.class,number);
    }

    @Override
    public List<CityEntity> getAll() throws Exception {
        return entityManager.createQuery("SELECT city from CityEntity city ORDER BY name", CityEntity.class).getResultList();
    }
	
}
