package com.training.ifaces;


public interface EmployeeRepository<T> extends CrudRepository<T> {
	public boolean deleteByFirstName(String firstName);
	public boolean updateEmailAndPhoneNumberByEmployeeId(int employeeId,String emailAddress,long phoneNumber);
}
