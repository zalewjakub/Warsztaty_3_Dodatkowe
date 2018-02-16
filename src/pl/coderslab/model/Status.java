package pl.coderslab.model;

public class Status extends ActiveRecord {

	public Status() {
		super();
		tableName = "Status";
		setTableFieldsValue("descripte");
	}

	public Status(boolean word) {
		super();
		tableName = "Status";
		setTableFieldsValueWithId("id", "descripte");
	}
}
