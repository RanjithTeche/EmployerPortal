package com.example.demo.cntroller;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.EmployeeVO;
import com.example.demo.model.UniqueEmployeeVO;
import com.example.demo.service.EmployeeService;
import com.example.demo.util.EmployeeCSVHelper;

@RestController
@RequestMapping("/resource/v1/employee")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@PostMapping()
	public ResponseEntity<URI> createEmployee(@RequestBody EmployeeVO request) {
		Long id = employeeService.createEmployee(request);
		URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uriLocation).build();
	}

	@PostMapping(value= "/upload", consumes = {"application/octet-stream", "text/csv", "multipart/form-data"})
	public ResponseEntity<List<EmployeeVO>> createEmployeesByUpload(@RequestParam("file") MultipartFile file) {
		List<EmployeeVO> employeeVOs = employeeService
				.createEmployees(EmployeeCSVHelper.convertFIleTOEmployees(file));
		return ResponseEntity.ok(employeeVOs);

	}
	@PostMapping(value= "/identify-duplicate", consumes = {"application/octet-stream", "text/csv", "multipart/form-data"})
	public ResponseEntity<Set<UniqueEmployeeVO>> identifyEmployees(@RequestParam("file") MultipartFile file) {
		List<EmployeeVO> employeeVOs = EmployeeCSVHelper.convertFIleTOEmployees(file);
		//Here we just use equeals and hashcode override based name and age than if we insert into set than it will remove duplicates
		Set<UniqueEmployeeVO> uniqueEmps = employeeVOs.stream().map(it -> {
			UniqueEmployeeVO unique = new UniqueEmployeeVO(); 
			BeanUtils.copyProperties(it, unique);
			return unique;
		}).collect(Collectors.toSet());
		
		return ResponseEntity.ok(uniqueEmps);

	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeVO> createEmployee(@PathVariable Long id) {
		EmployeeVO response = employeeService.findEmployee(id);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{empNo}")
	public ResponseEntity<Void> updateEmployerWithEmpNo(@PathVariable String empNo, @RequestBody EmployeeVO request) {
		employeeService.updateEmployeeWithEmpNo(empNo, request);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeEmployee(@PathVariable Long id) {
		employeeService.removeEmployee(id);
		return ResponseEntity.noContent().build();
	}

}
