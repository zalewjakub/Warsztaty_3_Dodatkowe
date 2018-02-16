package pl.coderslab.model;

public class Vehicle extends ActiveRecord{
	public Vehicle() {
		super();
		tableName = "Vehicle";
		setTableFieldsValue("model", "mark", "production", "license", "checkup", "user_id");
	}

	public Vehicle(boolean word) {
		super();
		tableName = "Vehicle";
		setTableFieldsValue("id", "mark", "production", "license", "checkup", "user_id");
	}
}
