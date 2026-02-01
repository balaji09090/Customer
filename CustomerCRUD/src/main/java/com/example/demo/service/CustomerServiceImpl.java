package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InvalidIdException;
import com.example.demo.exception.InvalidMobileNumber;
import com.example.demo.exception.InvalidNameException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository cr;
	private Customer customer;                   

	@Override
	public void add(Customer customer) {
		// TODO Auto-generated method stub
//		cr.save(customer);
		
		
//		Name Validation
		String name = customer.getName();
		
		if (name.length() < 2 || name.length() > 20) {
			throw new InvalidNameException("Name must be between 2 and 20 characters");
		}
		for (char c: name.toCharArray()) {
			if (!Character.isLetter(c) && c != ' ') {
		        throw new InvalidNameException("Name must contain only letters and spaces");
		    }
		}
//		Mobile validation
		String mob = customer.getMob();
		
		if (mob.length() == 10) {
			if (mob.charAt(0) == '0' || mob.charAt(0) == '1' || mob.charAt(0) == '2' || mob.charAt(0) == '3' || mob.charAt(0) == '4' || mob.charAt(0) == '5')
				throw new InvalidMobileNumber("INVALID MOBILE NUMBER");
			
			for (int i=0; i< mob.length(); i++) {
				if (!Character.isDigit(mob.charAt(i)))
					throw new InvalidMobileNumber("INVALID MOBILE NUMBER");
		}
	}else 
	    throw new InvalidMobileNumber("INVALID MOBILE NUMBER");
	    
	    cr.save(customer); 
	    
 
}
	

	
	
	@Override
	public List<Customer> display() {
		// TODO Auto-generated method stub
		return cr.findAll();
	}

	@Override
	public Customer delete(Integer id) {
		// TODO Auto-generated method stub
//Search
		if(cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			cr.deleteById(id); 
			return temp;
		}
		
		return null;
	}

	@Override
	public void update(Customer customer, Integer id) {
		// TODO Auto-generated method stub
		customer.setId(id);
		cr.save(customer);
		
	}

	@Override
	public Customer search(Integer id) {
		// TODO Auto-generated method stub
		
		if(cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			cr.deleteById(id); 
			return temp;
		}
		return null;
	}

	@Override
	public void addAll(List<Customer> list) {
		// TODO Auto-generated method stub
		cr.saveAll(list);
	}

	@Override
	public Customer findMob(String mob) {
		// TODO Auto-generated method stub
		return cr.findByMob(mob);
		
	
	}

}
