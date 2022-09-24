package com.training.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.training.ifaces.EmployeeRepository;
import com.training.model.Employee;
import com.training.utilis.ConnectionFactory;

public class EmployeeRepositoryImpl implements EmployeeRepository<Employee> {
	
	private Connection con;
		public EmployeeRepositoryImpl() {
			super();
			this.con=ConnectionFactory.getMySqlConnection();
		}
		@Override
		public boolean save(Employee obj) {
			String sql="insert into employee_personal_details values(?,?,?,?,?,?,?,?)";
			int rowUpdated=0;
			
			try {
				PreparedStatement pstmt=con.prepareStatement(sql);	
				pstmt.setInt(1,obj.getEmployeeId());
				pstmt.setString(2,obj.getFirstName());
				pstmt.setString(3,obj.getLastName());
				pstmt.setString(4,obj.getAddress());
				pstmt.setString(5,obj.getEmailAddress());
				pstmt.setLong(6,obj.getPhoneNumber());
				java.sql.Date date=java.sql.Date.valueOf(obj.getDateOfBirth());
				pstmt.setDate(7, date);
				java.sql.Date date1=java.sql.Date.valueOf(obj.getWeddingDate());
				pstmt.setDate(8, date1);
				rowUpdated=pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return rowUpdated==1?true:false;
			
		}
		
		@Override
		public List<Employee> findAll() {
			String sql="select * from employee_personal_details";
			List<Employee> list = new ArrayList<>();
			LocalDate date = null;
			try {
				PreparedStatement pstmt=con.prepareStatement(sql);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()) {
					Employee emp=new Employee();
					emp.setEmployeeId(rs.getInt(1));
					emp.setFirstName(rs.getString(2));
					emp.setLastName(rs.getString(3));
			    	emp.setAddress(rs.getString(4));
			    	emp.setEmailAddress(rs.getString(5));
			    	emp.setPhoneNumber(rs.getLong(6));
			    	date=rs.getDate(7).toLocalDate();
			    	emp.setDateOfBirth(date);
			    	date=rs.getDate(8).toLocalDate();
			    	emp.setWeddingDate(date);
			    	list.add(emp);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		
		@Override
		public boolean deleteByFirstName(String firstName) {
			String sql="delete from test.employee_personal_details where first_name=?";
			int rowDeleted=0;
			try {
				PreparedStatement pstmt=con.prepareStatement(sql);		
				pstmt.setString(1, firstName);
				rowDeleted=pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rowDeleted==1?true:false;
		}
		
		@Override
		public boolean updateEmailAndPhoneNumberByEmployeeId(int employeeId, String emailAddress, long phoneNumber) {
			String sql="update employee_personal_details set email=?,phone_number=? where employee_id=?";
			int rowUpdated=0;
			try {
				PreparedStatement pstmt=con.prepareStatement(sql);		
				pstmt.setString(1, emailAddress);
				pstmt.setLong(2, phoneNumber);
				pstmt.setInt(3,employeeId);
				rowUpdated=pstmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return rowUpdated==1?true:false;
		}



}
