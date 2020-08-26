package com.sanskar.springcruddemo.dao;

import com.sanskar.springcruddemo.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
//@Primary
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOJpaImpl.class);
    private EntityManager em;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Employee> findAll() {
        logger.info("findAll");
        TypedQuery<Employee> query = em.createQuery("select e from Employee e", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findById(Integer id) {
        logger.info("findById, {}", id);
        return em.find(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {
        logger.info("save, {}", employee);
        Employee dbEmployee = em.merge(employee);
        employee.setId(dbEmployee.getId());
    }

    @Override
    public void deleteById(Integer id) {
        logger.info("deleteById, id = {}", id);
        Query query = em.createQuery("delete from Employee where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
