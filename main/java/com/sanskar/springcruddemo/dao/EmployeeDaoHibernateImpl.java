package com.sanskar.springcruddemo.dao;

import com.sanskar.springcruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDaoHibernateImpl implements EmployeeDAO {

    private EntityManager em;

    public EmployeeDaoHibernateImpl(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //get the current hibernate session
        Session currentSession = em.unwrap(Session.class);
        //create a query
        Query<Employee> theQuery = currentSession.createQuery("select e from Employee e", Employee.class);
        // execute query and get result list
        List<Employee>  employeeList = theQuery.getResultList();
        //return the results
        return employeeList;
    }

    @Override
    public Employee findById(Integer id) {
        // get the current hibernate session
        Session currentSesstion = em.unwrap(Session.class);
        //get the employee
        Employee employee = currentSesstion.get(Employee.class, id);
        //return the employee
        return employee;
    }

    @Override
    public void save(Employee employee) {
        Session currentSession = em.unwrap(Session.class);
        currentSession.saveOrUpdate(employee);
    }

    @Override
    public void deleteById(Integer id) {
        Session currentSession = em.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId", id);
        theQuery.executeUpdate();
    }
}
