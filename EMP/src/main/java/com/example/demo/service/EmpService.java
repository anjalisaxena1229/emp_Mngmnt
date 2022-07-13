package com.example.demo.service;
 import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.model.EmpEntity;

public interface EmpService {

	
		 List<EmpEntity> getAllEmployees();
		 void saveEmployee(EmpEntity empEntity);
		 EmpEntity getEmployeeById(long id);
		 void deleteEmployeeById(long id);
		 Page<EmpEntity> findPaginated(int pageNo, int pageSize);
		
}
