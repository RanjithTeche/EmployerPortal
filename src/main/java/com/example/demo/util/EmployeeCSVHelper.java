package com.example.demo.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.BadCSVFile;
import com.example.demo.exception.UnsupportedFile;
import com.example.demo.model.EmployeeVO;

public class EmployeeCSVHelper {
    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeCSVHelper.class);

	public static String TYPE = "text/csv";
	static String[] HEADERs = { "EMPID", "ENAME", "HIREDATE", "SAL", "DEPTID", "AGE", "LOCATION" };

	public static boolean hasCSVFormat(MultipartFile file) {
		if (TYPE.equals(file.getContentType()) || file.getContentType().equals("application/vnd.ms-excel")) {
			return true;
		}

		return false;
	}

	public static List<EmployeeVO> convertFIleTOEmployees(MultipartFile file) {
		if (!hasCSVFormat(file)) {
			throw new UnsupportedFile("File format not supported");
		}
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			
			List<EmployeeVO> employeeList = new ArrayList<>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			EmployeeVO employee = null;
			for (CSVRecord csvRecord : csvRecords) {
				try {
				employee = new EmployeeVO(null, csvRecord.get("ENAME"), csvRecord.get("EMPID"));
				employee.setDateOfHire(csvRecord.get("HIREDATE"));
				employee.setSalary(new BigDecimal(csvRecord.get("SAL")));
				employee.setDepartment(csvRecord.get("DEPTID"));
				employee.setAge(Integer.parseInt(csvRecord.get("AGE")));
				employee.setLocation(csvRecord.get("LOCATION"));

				}catch (IllegalArgumentException e) {
					LOGGER.warn("Exception occured while parse {}", e.getMessage());
				}
				employeeList.add(employee);
			}

			return employeeList;
		} catch (Exception e) {
			throw new BadCSVFile("fail to parse CSV file: " + e.getMessage());
		}
	}

}
