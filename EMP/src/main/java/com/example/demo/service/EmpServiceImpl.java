package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EmpEntity;
import com.example.demo.repo.EmpRepo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
    private EmpRepo empRepo;
	
	
	
	
	@Override
	public List<EmpEntity> getAllEmployees() {
		 return empRepo.findAll();
		
	}


	@Override
	public void saveEmployee(EmpEntity empEntity) {
		// TODO Auto-generated method stub
		this.empRepo.save(empEntity);
		
	}


	@Override
	public EmpEntity getEmployeeById(long id) {
		Optional < EmpEntity > optional = empRepo.findById(id);
		EmpEntity empEntity = null;
	    if (optional.isPresent()) {
	    	empEntity = optional.get();
	    } else {
	        throw new RuntimeException(" Employee not found for id :: " + id);
	    }
	    return empEntity;
	}


	@Override
	public void deleteEmployeeById(long id) {
		this.empRepo.deleteById(id);
		
	}


	@Override
	public Page<EmpEntity> findPaginated(int pageNo, int pageSize) {
		 Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		 return this.empRepo.findAll(pageable);
	}

}
