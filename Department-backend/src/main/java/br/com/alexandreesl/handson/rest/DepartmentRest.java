package br.com.alexandreesl.handson.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestBody;

@Named
@Path("/")
public class DepartmentRest {

	private static List<Department> departments = new ArrayList<Department>();

	static {

		Department product1 = new Department();
		product1.setId(1);
		product1.setName("pricing");
		product1.setMaxSalary("90k");
		product1.setMaxSalary("100k");
		
		Department product2 = new Department();
		product2.setId(2);
		product2.setName("solutions");
		product2.setMaxSalary("90k");
		product2.setMaxSalary("100k");

		Department product3 = new Department();
		product3.setId(3);
		product3.setName("cia");
		product3.setMaxSalary("90k");
		product3.setMaxSalary("100k");
		
		Department product4 = new Department();
		product4.setId(4);
		product4.setName("forecasting");
		product4.setMaxSalary("90k");
		product4.setMaxSalary("100k");

		departments.add(product1);
		departments.add(product2);
		departments.add(product3);
		departments.add(product4);

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> getProdutos() {
		return departments;
	}

	@GET
	@Path("department")
	@Produces(MediaType.APPLICATION_JSON)
	public Department getProduto(@QueryParam("depId") long id) {
		System.out.println(" i am in department id"+id);
		Department prod = null;

		for (Department p : departments) {
			System.out.println(" department"+p.getId());
			if (p.getId() == id)
				prod = p;

		}

		return prod;
	}
	
	@POST
	@Path("department")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addEmployee(@QueryParam("depId") long deptId,@RequestBody Department department) {
		
		//assuming depId id is same reqeustbody id of dept
		
		System.out.println(" department name "+department.getName());
		boolean validated = validateExists(department);
		
		if(validated){
	      //add department
			return false;
		}
		return true;
	}
	
	private boolean validateExists(Department department) {
		//TODO
		Iterator<Department> it = departments.iterator();
		while (it.hasNext()) {
			System.out.println(" loop department name"+department.getName());

			Department dep = it.next();
			  if (department.getName().trim().equals(dep.getName().trim())) {
			    return true;
			  }
			}
		return false;
	}

}
