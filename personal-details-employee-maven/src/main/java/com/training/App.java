package com.training;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.training.exception.ElementNotFoundException;
import com.training.model.Employee;
import com.training.services.EmployeeService;



public class App 
{
	
       
    
	 public static void main( String[] args )
	    {
	    	int choice;
	    	int employeeId=0;
	    	String firstName;
	    	String lastName;
	    	String address;
	    	String emailAddress;
	    	long phoneNumber;
	    	DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    	LocalDate dateOfBirth;
	    	LocalDate weddingDate;
	    	Scanner scan=new Scanner(System.in);
   
	    	EmployeeService service=new EmployeeService();
	    		System.out.println("Enter 0 to Quit");
	            System.out.println("Enter 1 for Adding New Employee");
	            System.out.println("Enter 2 for Getting the List of employees by their firstName.");
	            System.out.println("Enter 3 for Getting the List of employees with FirstName and Phone Number");
	            System.out.println("Enter 4 for Updating the email and phoneNumber of a particular employee.");
	            System.out.println("Enter 5 for Deleting Details of a Particular employee by firstName.");
	            System.out.println("Enter 6 for Getting a list of employees with their firstName and emailAddress  whose Birthday falls on the given date.");
	            System.out.println("Enter 7 for Gettting the list of employees with their firstName and phone Number whose Wedding Anniversary falls on the given date.");
	           
	            System.out.println("Select one option");
	            choice = scan.nextInt();
	            switch (choice)  {
	            
	            case 0:
	            	System.exit(0);
	            case 1:
	            	
	            	try {
	            	System.out.println("Enter the Employee Id");
	            	employeeId=scan.nextInt();
	            	}
	            	catch(InputMismatchException e) {
	            		System.err.println("Err:13 Employee Id must be an Integer");
	            		break;
	            	}
	            
	       
	            	System.out.println("Enter the Employee First Name:");
	            	firstName=scan.next();
	            	
	            	System.out.println("Enter the Employee Last Name:");
	            	lastName=scan.next();
	            	
	            	System.out.println("Enter the Employee Address:");
	            	address=scan.next();
	            	
	            	System.out.println("Enter the Employee Email Address:");
	            	emailAddress=scan.next();
	            	
	            	try {
	            	System.out.println("Enter the Employee Phone Number:");
	            	phoneNumber=scan.nextLong();
	            	}
	            	catch(InputMismatchException e) {
	            		  
	            		System.err.println("Err:13 Phone number must be an Integer");
	            		break;
	            	}
	            	
	            	try {
	            	System.out.println("Enter the Employee Date Of Birth(dd/mm/yyyy:)");
	                dateOfBirth= LocalDate.parse(scan.next(), JEFormatter);
	            	}
	            	catch(DateTimeParseException e) {
	            		  
	            		System.err.println("Err:13 Date of birth must be in the above format");
	            		break;
	            	}
	            	try {
	                System.out.print("Enter the Employee Wedding Date(dd/mm/yyyy:)");
	            	weddingDate= LocalDate.parse(scan.next(), JEFormatter);
	            	}
	            	catch(DateTimeParseException e) {
	            		  
	            		System.err.println("Err:13 Wedding date must be in the above format");
	            		break;
	            	}
	                System.out.println(service.addEmployee(new Employee(employeeId,firstName,lastName,address,emailAddress,phoneNumber,dateOfBirth,weddingDate)));
	                break;
	            case 2:
	            	System.out.println("Enter the Employee First Name:");
	            	firstName=scan.next();
	            	try {
						service.findByFirstName(firstName);
					} catch (ElementNotFoundException e) {
						e.printStackTrace();
					}
	            	break;
	            
	            case 3:
	            	try {
	                    service.FindEmployeesWithFirstNameAndPhoneNumber();
	                } catch (ElementNotFoundException e) {
	                    e.printStackTrace();
	                }
	                break;
	            	
	            
	            case 4:
	            	try {
	            	System.out.println("Enter the Employee Id:");
	            	employeeId=scan.nextInt();}
	            	catch(InputMismatchException e) {
	            		System.err.println("Err:13 Employee Id must be an Integer");
	            		break;
	            	}
	            	
	            	System.out.println("Enter the Employee Email Address:");
	            	emailAddress=scan.next();
	            	
	            	try {	        
	            	System.out.println("Enter the Employee Phone Number");
	            	phoneNumber=scan.nextLong();}
	            	catch(InputMismatchException e) {
	            		  
	            		System.err.println("Err:13 Phone number must be an Integer");
	            		break;
	            	}
	            	
	            	try {
						System.out.println(service.updateEmailAndPhoneNumberByEmployeeId(employeeId,emailAddress,phoneNumber));
					} catch (ElementNotFoundException e1) {
						e1.printStackTrace();
					}
	            	break;
	            case 5:
	            	System.out.println("Enter the Employee First Name:");
	            	firstName=scan.next();
	            	try {
						System.out.println(service.deleteByFirstName(firstName));
					} catch (ElementNotFoundException e1) {
						e1.printStackTrace();
					}
	            	break;
	            case 6:
	            	try {
	            	System.out.print("Enter the Employee Date Of Birth(DD/MM/YYYY):");
	                dateOfBirth= LocalDate.parse(scan.next(), JEFormatter);}
	            	catch(DateTimeParseException e) {
	            		  
	            		System.err.println("Err:13 Date of birth must be in the above format");
	            		break;
	            	}
	            	
	                try {
						service.getEmployeesByDateOfBirth(dateOfBirth);
					} catch (ElementNotFoundException e) {
						e.printStackTrace();
					}
	            	break;
	            case 7:
	            	try {
	            	System.out.print("Enter the Employee Wedding Anniversary(DD/MM/YYYY):");
	                weddingDate= LocalDate.parse(scan.next(), JEFormatter);}
	            	catch(DateTimeParseException e) {
	            		  
	            		System.err.println("Err:13 Wedding date must be in the above format");
	            		break;
	            	}
	                try {
						service.getEmployeesByWeddingDate(weddingDate);
					} catch (ElementNotFoundException e) {
						e.printStackTrace();
					}
	            	break;
	            
	            default:
	                System.out.println("Invalid choice!!! Please make a valid choice.");
	            }
     } 
   
}
