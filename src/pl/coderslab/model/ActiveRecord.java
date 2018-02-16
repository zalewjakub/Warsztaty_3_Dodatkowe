package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ActiveRecord {
	protected String id = "0";
	protected String tableName = "";
	protected HashMap<String, String> tableFieldsValues = new HashMap<>();
	protected HashMap<String, String> tableFieldsValuesWithId = new HashMap<>();
	protected ArrayList<String> displayOrder = new ArrayList<>();
	protected Connection conn = null;
	public int countRecord = 0;

	public ActiveRecord() {
		try {
			conn = DbUtil.getConn();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	protected void setTableFieldsValue(String... fieldNames) {
		for (String name : fieldNames) {
			displayOrder.add(name);
			tableFieldsValues.put(name, "");
		}
	}

	protected void setTableFieldsValueWithId(String... fieldNames) {
		for (String name : fieldNames) {
			displayOrder.add(name);
			tableFieldsValuesWithId.put(name, "");
		}
	}

	public String getTableName() {
		return tableName;
	}

	public int getcountRecord() {
		return countRecord;
	}

	public String getValue(String key) {
		return tableFieldsValues.get(key);
	}

	public String getValueWithId(String key) {
		return tableFieldsValuesWithId.get(key);
	}

	public void setValue(String key, String value) {
		if (tableFieldsValues.containsKey(key)) {
			tableFieldsValues.put(key, value);
		} else {
			throw new RuntimeException(String.format("%s is not present in %s fields", key, tableName));
		}
	}

	public void setValueWithId(String key, String value) {
		if (tableFieldsValuesWithId.containsKey(key)) {
			tableFieldsValuesWithId.put(key, value);
		} else {
			throw new RuntimeException(String.format("%s is not present in %s fields", key, tableName));
		}
	}

	public String[] getFields() {
		String[] tmp = new String[tableFieldsValues.size()];
		return displayOrder.toArray(tmp);
	}

	public String[] getFieldsWithId() {
		String[] tmp = new String[tableFieldsValuesWithId.size()];
		return displayOrder.toArray(tmp);
	}

	public ArrayList<String> loadWithLimit(int limit) {
		ArrayList<String> datas = new ArrayList<String>();
		try {
			String sql = String.format("SELECT * FROM %s LIMIT %s", tableName, limit);
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				for (String fieldName : tableFieldsValuesWithId.keySet()) {
					tableFieldsValuesWithId.put(fieldName, result.getString(fieldName));
				}
				for (String key : this.getFieldsWithId()) {
					datas.add(this.getValueWithId(key));
				}
			}
			return datas;
		} catch (SQLException e) {
			System.out.println(e);
			return new ArrayList<String>();
		}
	}

	public ArrayList<String> loadAllResult(String where) {
		ArrayList<String> datas = new ArrayList<String>();
		try {

			String sql = String.format("SELECT * FROM %s %s", tableName, where);
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				for (String fieldName : tableFieldsValuesWithId.keySet()) {
					tableFieldsValuesWithId.put(fieldName, result.getString(fieldName));
				}
				for (String key : this.getFieldsWithId()) {
					datas.add(this.getValueWithId(key));
				}
				countRecord++;
			}
			this.countRecord = countRecord;
			return datas;
		} catch (SQLException e) {
			System.out.println(e);
			return new ArrayList<String>();
		}
	}

	public ActiveRecord getById(int id) {
		try {
			String sql = String.format("SELECT * FROM %s WHERE id = ?", tableName);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			result.next();
			for (String fieldName : tableFieldsValues.keySet()) {
				tableFieldsValues.put(fieldName, result.getString(fieldName));
			}
			return this;
		} catch (SQLException e) {
			System.out.println(e);
			return new ActiveRecord();
		}

	}

	private String TrimBrackets(String text) {
		if (text.indexOf('[') == 0 && text.indexOf(']') == text.length() - 1) {
			return text.substring(1, text.length() - 1);
		}
		return text;
	}

	private String quotationMarks() {
		String result = "";
		for (String name : displayOrder) {
			result += "?,";

		}
		return result.substring(0, result.length() - 1);
	}

	protected void createNew() {
		String sql = String.format("INSERT INTO %s(%s) VALUES(%s)", tableName, TrimBrackets(displayOrder.toString()),
				quotationMarks());
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			int index = 1;
			for (String field : displayOrder) {
				stmt.setString(index++, getValue(field));
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Wypełnij wszystkie pola");
		}
	}

	public void update(String whatUpdate, int id) {
		String sql = String.format("UPDATE %s SET %s where id = %s", tableName, whatUpdate, id);
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void delete(int id) throws SQLException {
		if (id > 0) {
			String sql = String.format("DELETE FROM %s WHERE id= %s", tableName, id);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		}
	}

	public void save(int id, String whatUpdate) {
		if (id == 0) {
			createNew();
		} else {
			update(whatUpdate, id);
		}
	}

	public ArrayList<String> loadAllResult(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
