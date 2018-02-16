package pl.coderslab.model;

public class Customer extends ActiveRecord {
	public Customer() {
		super();
		tableName = "Customer";
		setTableFieldsValue("name", "surname", "birthdate", "car_id");
	}

	public Customer(boolean word) {
		super();
		tableName = "Customer";
		setTableFieldsValue("id", "name", "surname", "birthdate", "car_id");
	}
}
