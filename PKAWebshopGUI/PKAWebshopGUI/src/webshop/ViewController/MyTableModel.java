package webshop.ViewController;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import webshop.Artikel.Artikel;

public class MyTableModel extends DefaultTableModel {

	private String[] columns;

	public MyTableModel(Object[][] array, String[] strings) {
		super(array, strings);
		columns = strings;
	}

	public Class<?> getColumnClass(int column) {
		if (getValueAt(0, column) != null) {
			return getValueAt(0, column).getClass();
		}
		return Object.class;
	}

	public String getColumnName(int index) {
		return columns[index];
	}
}