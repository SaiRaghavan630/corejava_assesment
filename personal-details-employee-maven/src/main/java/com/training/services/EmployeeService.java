package com.training.services;

import com.training.exception.*;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.training.model.Employee;
import com.training.repos.EmployeeRepositoryImpl;

public class EmployeeService {
	public static final Logger logger =LogManager.getRootLogger();
	EmployeeRepositoryImpl dao=null;
	List<Employee> employeeList;
	public EmployeeService() {
		this.dao=new EmployeeRepositoryImpl();
		employeeList=dao.findAll();
	}
	public String addEmployee(Employee emp) {
		boolean rowAdded=this.dao.save(emp);
		employeeList=dao.findAll();
		if(!rowAdded) {
			logger.error("Employee Details Added : "+rowAdded);
			return "Enter Correct Employee Details";
		}
		else {
			logger.info("Employee Added : "+rowAdded);
			return rowAdded+"One row added to data base";
		}
	}
	public String updateEmailAndPhoneNumberByEmployeeId(int employeeId, String emailAddress, long phoneNumber) throws ElementNotFoundException {
		boolean rowUpdated=this.dao.updateEmailAndPhoneNumberByEmployeeId(employeeId, emailAddress, phoneNumber);
		employeeList=dao.findAll();
		if(!rowUpdated) {
			logger.error("Employee Details Updated : "+rowUpdated);
			throw new ElementNotFoundException("ERR-102","No employee with the given employeeId: "+employeeId+"  is found in the data base");	
		}
		else {
			logger.info("Employee Details Updated : "+rowUpdated);
			return rowUpdated+" One row got updated in the data base";
		}
	}
	
	public String deleteByFirstName(String firstName) throws ElementNotFoundException {
		boolean rowDeleted=this.dao.deleteByFirstName(firstName);
		employeeList=dao.findAll();
		if(!rowDeleted) {
			logger.error("Employee Details Updated : "+rowDeleted);
			throw new ElementNotFoundException("ERR-102","No employee with first name: "+firstName+" is found in the data base");	
		}
		else {
			logger.info("Employee Details Updated : "+rowDeleted);
			return rowDeleted+" One row got updated in the data base";
		}
	}
	public void findByFirstName(String firstName) throws ElementNotFoundException {
		List<Employee> employeeData= this.employeeList.stream().filter(e->e.getFirstName().equals(firstName)).collect(toList());	
		if(employeeData.size()==0) {
			throw new ElementNotFoundException("ERR-102","No employee with first Name:"+firstName+" is found in the data base");
		}
		else {
			 employeeData.forEach(e->logger.info(e));
		}
	}
	
	public void FindEmployeesWithFirstNameAndPhoneNumber() throws ElementNotFoundException {
        Map<String, Long> map=this.employeeList.stream()
                .collect(Collectors.toMap(Employee::getFirstName,Employee::getPhoneNumber ));
        Collection<String> list=new ArrayList<>();
         map.forEach((x,y)->list.add("FirstName:"+x+" PhoneNumber:"+y));
        if(map.size()==0) {
            throw new ElementNotFoundException("ERR-102","No employees Exist in data base");
        }
        else {
            list.forEach(e->logger.info(e));
        }
    }
	
	public void getEmployeesByDateOfBirth(LocalDate dateOfBirth) throws ElementNotFoundException{
		List<Employee> list = employeeList.stream().filter(e->e.getDateOfBirth().getYear() <= dateOfBirth.getYear()).
				filter(e->e.getDateOfBirth().getMonth()==dateOfBirth.getMonth()).filter(e->e.getDateOfBirth().
						getDayOfMonth()==dateOfBirth.getDayOfMonth()).collect(toList());
		
			List<String> rtnList = new ArrayList<>();
		
		if(list.size()==0) {
    		throw new ElementNotFoundException("ERR-102","No employee with date of Birth :"+dateOfBirth+" found in the data base");
    	}
    	else {
    		for(Employee eachEmp:list) {
                
                long phoneNumber = eachEmp.getPhoneNumber();
                String firstName = eachEmp.getFirstName();
                
          
                rtnList.add("Employee First Name:" + firstName + " " + "Employee phone number:" +phoneNumber );
    	}
    		rtnList.forEach(System.out::println);

    	}
	}
	
	public void getEmployeesByWeddingDate(LocalDate weddingDate) throws ElementNotFoundException{
		List<Employee> list = employeeList.stream().filter(e->e.getWeddingDate().getYear() < weddingDate.getYear()).filter(e->e.getWeddingDate().getMonth()==weddingDate.getMonth()).
		        filter(e->e.getWeddingDate().getDayOfMonth()==weddingDate.getDayOfMonth()).collect(toList());



	       List<String> rtnList = new ArrayList<>();
		if(list.size()==0) {
    		throw new ElementNotFoundException("ERR-102","No employee with wedding date :"+weddingDate+" found in the data base");
    	}
    	else {
    	
    		for(Employee eachEmp:list) {
                
                long phoneNumber = eachEmp.getPhoneNumber();
                String firstName = eachEmp.getFirstName();
                
    
                rtnList.add("Employee First Name:" + firstName + " " + ","+"Employee phone number:" +phoneNumber );
    		
    	}
    		
    		rtnList.forEach(System.out::println);


	}
	
	}
}
