package br.com.alexandreesl.handson.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Named
@Path("/")
@RestController
public class EmployeeRest {

	private static List<Employee> clients = new ArrayList<Employee>();
	@Inject
	private RestTemplate restTemplate;
	
	static {
		
		Department product1 = new Department();
		product1.setId(1);
		product1.setName("pricing");
		product1.setMinSalary("90k");
		product1.setMaxSalary("100k");
		
		Department product2 = new Department();
		product2.setId(2);
		product2.setName("solutions");
		product2.setMinSalary("90k");
		product2.setMaxSalary("100k");

		Department product3 = new Department();
		product3.setId(3);
		product3.setName("cia");
		product3.setMinSalary("90k");
		product3.setMaxSalary("100k");
		
		Department product4 = new Department();
		product4.setId(4);
		product4.setName("forecasting");
		product4.setMinSalary("90k");
		product4.setMaxSalary("100k");

		Employee customer1 = new Employee();
		customer1.setId(1);
		customer1.setName("Cliente 1");
		customer1.setEmail("customer1@gmail.com");
		customer1.setDept("pricing");
		customer1.setManagerName("Scott");
		customer1.setSalary("100k");
		customer1.setDepartment(product1);
		
		Employee customer2 = new Employee();
		customer2.setId(2);
		customer2.setName("Cliente 2");
		customer2.setEmail("customer2@gmail.com");
		customer2.setDept("pricing");
		customer2.setManagerName("Scott");
		customer2.setSalary("100k");
		customer2.setDepartment(product2);
		
		Employee customer3 = new Employee();
		customer3.setId(3);
		customer3.setName("Cliente 3");
		customer3.setEmail("customer3@gmail.com");
		customer3.setDept("pricing");
		customer3.setManagerName("Scott");
		customer3.setSalary("100k");
		customer3.setDepartment(product3);

		Employee customer4 = new Employee();
		customer4.setId(4);
		customer4.setName("Cliente 4");
		customer4.setEmail("customer4@gmail.com");
		customer4.setDept("pricing");
		customer4.setManagerName("Scott");
		customer4.setSalary("100k");
		customer4.setDepartment(product4);

		Employee customer5 = new Employee();
		customer5.setId(5);
		customer5.setName("Cliente 5");
		customer5.setEmail("customer5@gmail.com");
		customer5.setDept("pricing");
		customer5.setManagerName("Scott");
		customer5.setSalary("100k");
		customer5.setDepartment(product4);

		clients.add(customer1);
		clients.add(customer2);
		clients.add(customer3);
		clients.add(customer4);
		clients.add(customer5);

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getClientes() {
		return clients;
	}

	@GET
	@Path("employee")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getCliente(@QueryParam("id") long id) throws Exception {

		Employee cli = null;

		for (Employee c : clients) {

			if (c.getId() == id){
				cli = c;
				return cli;
			}
		}
		throw new Exception();
	}
	
	@PUT
	@Path("employee")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateEmployee(@QueryParam("empId") long empId,@QueryParam("deptId") long deptId,@RequestBody Employee employee) {
		
		//assuming pathvariable id is same reqeustbody id of employee
		
		Department department = restTemplate.getForObject(
				"http://localhost:8084/department?deptId={deptId}", Department.class,
				deptId);
		
		boolean validated = validate(employee, department);
		if(validated){
			for (Employee c : clients) {
				if (c.getId() == empId){
				//update employee
				}
			}
		}

		return true;
	}
	
	@POST
	@Path("employee")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addEmployee(@QueryParam("empId") long empId,@QueryParam("depId") long deptId,@RequestBody Employee employee) {
		
		//assuming pathvariable id is same reqeustbody id of employee
		
		Department department = restTemplate.getForObject(
				"http://localhost:8084/department?depId={depId}", Department.class,
				deptId);
		System.out.println(" department in employee "+department.getName());
		boolean validated = validateExists(employee);
		
		if(validated){
	      //add employee
		}
		return true;
	}
	
	@DELETE
	@Path("employee")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteEmployee(@PathVariable long employeeId) {
		
		Iterator<Employee> it = clients.iterator();
		while (it.hasNext()) {
			Employee user = it.next();
			  if (user.getId() == employeeId) {
			    it.remove();
			  }
			}
		return true;
	}
	
	
	private boolean validateExists(Employee employee) {
		//TODO
		System.out.println(" employee name"+employee.getName());
		System.out.println(" employee dept"+employee.getDept());
		System.out.println(" employee salary"+employee.getSalary());

		Iterator<Employee> it = clients.iterator();
		while (it.hasNext()) {
			Employee user = it.next();
			  if (user.getName().equals(employee.getName())) {
			    return false;
			  }
			}
		return true;
	}
	
	private boolean validate(Employee employee, Department department) {
		//TODO
		System.out.println(" employee dept"+employee.getDept());
		System.out.println(" employee salary"+employee.getSalary());
		System.out.println(" department name"+department.getName());

		if(employee.getDept().equals(department.getName())){
			
			return true;
		}
		return false;
	}


}
