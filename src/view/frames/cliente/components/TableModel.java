package view.frames.cliente.components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import view.util.Misc;


@SuppressWarnings("serial")
public class TableModel<T> extends AbstractTableModel {
	private final List<T> dataList = new ArrayList<T>();
	@Override
	public int getRowCount() {
		return dataList.size();
	}
	@Override
	public int getColumnCount() {
		if(dataList.size()==0) return 0;
		T t = dataList.get(0);
		return t.getClass().getDeclaredFields().length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {	
		if(dataList.size()==0) return null;
		T t = dataList.get(rowIndex);
		return Misc.getFieldPositionObject(t,  columnIndex);
	}
	@Override
	public String getColumnName(int column) {
		if(dataList.size()==0) return null;
		T n = (T) dataList.get(0);
		return Misc.getFieldPositionColumnName(n, column);
	}
	public T getValueAtRow(int row) {
		if(Math.abs(row)<dataList.size()) return (T) dataList.get(row);
		return null;
	}
	public void clear() { dataList.clear(); }
	public void add(@SuppressWarnings("unchecked") T ...list) { 
		for (T t : list) { 
			dataList.add(t); 
		} 
	}
	public void delete(@SuppressWarnings("unchecked") T ...list) { 
		for (T t : list) {
			dataList.remove(t); 
		} 
	}
}
