package br.com.alexandreesl.handson.exceptions;

/**
 * @author Raja Koteru
 */
@SuppressWarnings("serial")
// tag::code[]
class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(Long employeeId) {
		super("could not find Employee '" + employeeId + "'.");
	}
}
// end::code[]
