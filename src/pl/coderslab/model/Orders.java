package pl.coderslab.model;

public class Orders extends ActiveRecord{
	public Orders() {
		super();
		tableName = "Orders";
		setTableFieldsValue("get_data", "plan_to_repair", "start_data", "employee_id", "notes_problem", "notes_repair", "cost_client", "cost_parts", "cost", "how_long");
	}

	public Orders(boolean word) {
		super();
		tableName = "Orders";
		setTableFieldsValue("id", "get_data", "plan_to_repair", "start_data", "employee_id", "notes_problem", "notes_repair", "cost_client", "cost_parts", "cost", "how_long");
	}
}
