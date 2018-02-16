package pl.coderslab.model;

public class Employee extends ActiveRecord{
	public Employee() {
		super();
		tableName = "Employee";
		setTableFieldsValue("name", "surname", "adress", "notes", "phone", "salary");
	}
	
	public Employee(boolean word) {
		super();
		tableName = "Employee";
		setTableFieldsValue("id", "name", "surname", "adress", "notes", "phone", "salary");
	}
}
