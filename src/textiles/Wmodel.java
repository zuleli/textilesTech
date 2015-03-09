package textiles;
/* Author: Zule Li
 * Email:zule.li@hotmail.com
 * Last Modified Date:Mar.7,2015
 * */

//Copyright (c) 2001 Home
import javax.swing.table.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author Zule Li
 */
public class Wmodel extends AbstractTableModel {
	private Vector data = new Vector();;
	private int row;
	private int column;
	protected String[] columnNames;
	private boolean[] editable;
	private String id = " ";
	public int MSU = 1;

	
	/**
	 * Constructor
	 */

	public Wmodel(String[] names) {
		columnNames = names;

	}

	public Wmodel(String[] names, boolean[] edit) {
		columnNames = names;
		editable = edit;
		// System.out.println("??"+"="+getColumnCount()+getColumnName(1));
	}

	public Vector getData() {
		return data;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}

	public void setData(Vector newData) {
		data = newData;
		this.fireTableStructureChanged();
	}

	public void setData(String[] ss) {
		if (id.equalsIgnoreCase("Style")) {
			String[] ss0 = (String[]) (data.get(0));
			ss0[0] = ss[0];
			ss0[1] = ss[1];
			ss0[2] = ss[2];
			ss0[3] = ss[3];
			ss0[4] = ss[4];
			ss0[5] = ss[5];
			ss0[6] = ss[6];
			ss0[7] = ss[7];
			ss0[8] = ss[37];
			ss0 = (String[]) (data.get(1));
			ss0[1] = ss[8];
			ss0[3] = ss[12];
			ss0[5] = ss[15];
			ss0[7] = ss[19];
			ss0[9] = ss[23];
			ss0 = (String[]) (data.get(2));
			ss0[1] = ss[9];
			ss0[3] = ss[13];
			ss0[5] = ss[16];
			ss0[7] = ss[20];
			ss0[9] = ss[24];
			ss0 = (String[]) (data.get(3));
			ss0[1] = ss[10];
			ss0[3] = ss[14];
			ss0[5] = ss[17];
			ss0[7] = ss[21];
			ss0[9] = ss[25];
			ss0 = (String[]) (data.get(4));
			ss0[1] = ss[11];

			ss0[5] = ss[18];
			ss0[7] = ss[22];
			ss0[9] = ss[26];
			ss0 = (String[]) (data.get(5));
			ss0[1] = ss[27];
			ss0[3] = ss[31];
			ss0 = (String[]) (data.get(6));
			ss0[1] = ss[28];
			ss0[3] = ss[32];

			ss0 = (String[]) (data.get(7));
			ss0[1] = ss[29];

			ss0[9] = ss[33];

			ss0 = (String[]) (data.get(8));
			ss0[1] = ss[30];

			ss0[9] = ss[34];
		}
		if (id.equalsIgnoreCase("drawing")) {
			// System.out.println(">>>>>>"+ss[35]);
			char[] sss = ss[35].toCharArray();
			int len = sss.length;
			String[] tem = null;
			for (int i = 0; i < data.size(); i++) {
				tem = (String[]) (data.get(i));
				for (int j = 0; j < tem.length; j++) {
					if (len < 60 * (i + 1))
						tem[j] = " ";
					else
						tem[j] = "" + sss[i * 60 + j];
				}
			}
		}
		this.fireTableDataChanged();
	}

	public void addData(String[] ss) {
		data.add(ss);
		this.fireTableDataChanged();
	}

	public void addData(String[] ss, int index) {
		data.add(index, ss);
		this.fireTableDataChanged();
	}

	public void delete(int index) {
		data.removeElementAt(index);
		this.fireTableDataChanged();
	}

	public String getDraw() {
		String[] ss = null;
		String d = "";
		char c = ' ';
		for (int i = 0; i < data.size(); i++) {
			ss = (String[]) (data.get(i));
			for (int j = 0; j < ss.length; j++) {
				if (ss[j] == null || (ss[j].trim()).length() == 0)
					ss[j] = " ";
				c = ss[j].charAt(0);
				d = d + c;
			}

		}
		return d;
	}

	public String[] getStyleData() {

		String[] ss = new String[37];
		ss[0] = (String) (getValueAt(0, 0));// styleno
		ss[1] = (String) (getValueAt(0, 1));// version
		ss[2] = (String) (getValueAt(0, 2));// Width
		ss[3] = (String) (getValueAt(0, 3));// pick
		ss[4] = (String) (getValueAt(0, 4));// fwidth
		ss[5] = (String) (getValueAt(0, 5));// fpick
		ss[6] = (String) (getValueAt(0, 6));// Y/p
		ss[7] = (String) (getValueAt(0, 7));// Fy/p
		ss[8] = (String) (getValueAt(1, 1));// Gs
		ss[9] = (String) (getValueAt(2, 1));// GP
		ss[10] = (String) (getValueAt(3, 1));// GE
		ss[11] = (String) (getValueAt(4, 1));// GY

		ss[12] = (String) (getValueAt(1, 3));// FS
		ss[13] = (String) (getValueAt(2, 3));// GS
		ss[14] = (String) (getValueAt(3, 3));// FY

		ss[15] = (String) (getValueAt(1, 5));// BS
		ss[16] = (String) (getValueAt(2, 5));// GS
		ss[17] = (String) (getValueAt(3, 5));// BE
		ss[18] = (String) (getValueAt(4, 5));// BY

		ss[19] = (String) (getValueAt(1, 7));// GuS
		ss[20] = (String) (getValueAt(2, 7));// GS
		ss[21] = (String) (getValueAt(3, 7));// GuE
		ss[22] = (String) (getValueAt(4, 7));// GuY

		ss[23] = (String) (getValueAt(1, 9));// eS
		ss[24] = (String) (getValueAt(2, 9));// GS
		ss[25] = (String) (getValueAt(3, 9));// eE
		ss[26] = (String) (getValueAt(4, 9));// eY

		ss[27] = (String) (getValueAt(5, 1));// mS
		ss[28] = (String) (getValueAt(6, 1));// GS
		ss[29] = (String) (getValueAt(7, 1));// mE
		ss[30] = (String) (getValueAt(8, 1));// mY

		ss[31] = (String) (getValueAt(5, 3));// Reed
		ss[32] = (String) (getValueAt(6, 3));// Drawing

		ss[33] = (String) (getValueAt(7, 9));// Memo
		ss[34] = (String) (getValueAt(8, 9));// selected

		return ss;
	}

	public void delete(String[] ss) {

		for (int i = 0; i < ss.length; i++) {
			delete(ss[i]);
		}
	}

	public void delete(String s) {
		String tem = "";
		for (int i = 0; i < data.size(); i++) {
			tem = ((String[]) (data.get(i)))[0];
			if (tem.equalsIgnoreCase(s)) {
				data.removeElementAt(i);
				this.fireTableDataChanged();
				return;
			}
		}
	}

	public int getColumnCount() {
		if (data.size() == 0)
			return columnNames.length;
		else
			return ((String[]) (data.get(0))).length;
	}

	public int getRowCount() {
		return (data == null) ? 0 : data.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public String deleteCharacter(String s) {
		String result = "0";
		char tem = ' ';
		int length = s.length();
		for (int i = 0; i < length; i++) {
			tem = s.charAt(i);
			if (tem == '.' && i != 0)
				break;
			else if (tem == '.' && i == 0) {
				result = "0";
				break;
			}

			if (Character.isDigit(tem))
				result = result + tem;
		}
		return "" + Long.parseLong(result);
	}

	public void setValueAt(Object value, int row, int col) {
		// *

		String[] ss = (String[]) (data.get(row));
		ss[col] = ((String) value).trim();
		if (ss[col].length() == 0)
			ss[col] = " ";

		Object o = null;
		String s = "";
		double r = 0, sr = 0;

		if (this.id.equalsIgnoreCase("Calculator")) {
			for (int i = 2; i < 9; i++) {
				if (i == 3 || i == 4)
					continue;
				o = getValueAt(0, i);// ground
				s = (o == null ? "0" : (String) o);
				s = s.trim();
				if (s.length() == 0)
					s = "0";
				r = Integer.parseInt(s);

				o = getValueAt(1, i);// end
				s = (o == null ? "0" : (String) o);
				s = s.trim();
				if (s.length() == 0)
					s = "0";
				r = r * Double.parseDouble(s);

				o = getValueAt(2, i);// constraction
				s = (o == null ? "0" : (String) o);
				s = s.trim();
				if (s.length() == 0)
					s = "0";
				r = r * Double.parseDouble(s) / 100;
				sr = sr + r;
			}

			o = getValueAt(0, 3);
			s = (o == null ? "0" : (String) o);
			s = s.trim();
			if (s.length() == 0)
				s = "0";
			r = Double.parseDouble(s);

			o = getValueAt(1, 3);
			s = (o == null ? "0" : (String) o);
			s = s.trim();
			if (s.length() == 0)
				s = "0";
			r = r * Double.parseDouble(s) * 2;

			o = getValueAt(0, 4);
			s = (o == null ? "0" : (String) o);
			s = s.trim();
			if (s.length() == 0)
				s = "0";
			r = r * Double.parseDouble(s);

			o = getValueAt(2, 4);
			s = (o == null ? "0" : (String) o);
			s = s.trim();
			if (s.length() == 0)
				s = "0";
			r = r * Double.parseDouble(s);

			o = getValueAt(2, 3);
			s = (o == null ? "0" : (String) o);
			s = s.trim();
			if (s.length() == 0)
				s = "0";
			r = r * Double.parseDouble(s) / 100 * 36 / 36 / 9000 * 0.9142;

			sr = (sr / 9000 * 0.9142 + r) * 100 / 453.5;
			ss = (String[]) (data.get(0));
			ss[1] = "" + sr;
			ss[0] = "" + (100 / sr);

		} else if (this.id.equalsIgnoreCase("Loom")) {
			if (ss[col].trim().length() == 0
					&& (col == 2 || col == 3 || col == 4))
				ss[col] = "0";

		}
		this.fireTableDataChanged();
	}

	public Object getValueAt(int row, int col) {
		// System.out.println(row+","+col);
		return ((String[]) (data.get(row)))[col];

	}

	public Class getColumnClass(int c) {
		return (new String("aa")).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		if (this.id.equalsIgnoreCase("Drawing"))
			return true;

		return editable[col];

	}

	public void setNames(String[] names) {
		this.columnNames = names;
		editable = new boolean[names.length];
	}

	public void update(int index) {
		this.fireTableRowsUpdated(index, index);
	}

	public void updateItem(String id, String operator, String type,
			String accountno) {
		int index = getIndex(id, 0);
		String[] ss = (String[]) (data.get(index));
		if (type.equalsIgnoreCase("IN")) {
			ss[5] = "IN";
			ss[6] = "NA";
			if (this.getColumnCount() > 11)
				ss[10] = operator;
		} else if (type.equalsIgnoreCase("OUT")) {
			ss[5] = "OUT";
			ss[6] = accountno;
			if (this.getColumnCount() > 11)
				ss[10] = operator;
		}

		this.fireTableRowsUpdated(index, index);
	}

	private int getIndex(String value, int column) {
		String tem = "";
		for (int i = 0; i < data.size(); i++) {
			tem = ((String[]) (data.get(i)))[column];
			if (tem.equalsIgnoreCase(value))
				return i;
		}
		return 1000000000;
	}

	public boolean isUnConfirmed(int row) {
		String[] ss = (String[]) (data.get(row));
		if (ss.length == 5 && ss[4].equalsIgnoreCase("UC"))
			return true;
		else
			return false;
	}

	public String cancelReturnMark(int row) {
		String[] ss = (String[]) (data.get(row));
		if (ss[2].equalsIgnoreCase("Ready")) {
			ss[2] = "Return Canceled";
			this.fireTableCellUpdated(row, 2);
			return ss[0];
		}
		return "NA";
	}

	public void setConfirmedIDs(String[] ids, boolean[] result) {
		int index = 0;
		for (int i = 0; i < ids.length; i++) {
			index = getIndex(ids[i], 0);
			if (result[i]) {
				((String[]) (data.get(index)))[4] = "CMED";
				((String[]) (data.get(index)))[2] = (new java.sql.Date(
						System.currentTimeMillis())).toString();
				this.fireTableCellUpdated(index, 2);
			}
		}

	}

	public String[] getUnConfirmedIDs() {
		Vector tem = new Vector();
		String[] ss = null;
		for (int i = 0; i < data.size(); i++) {
			ss = (String[]) (data.get(i));
			if (ss.length == 5 && ss[4].equalsIgnoreCase("UC"))
				tem.add(ss[0]);
		}
		ss = new String[tem.size()];

		for (int i = 0; i < tem.size(); i++) {
			ss[i] = (String) (tem.get(i));
		}

		return ss;
	}

	public void markReturn(String id) {
		String[] ss = null;
		for (int i = 0; i < data.size(); i++) {
			ss = (String[]) (data.get(i));
			if (ss[0].equalsIgnoreCase(id)) {
				ss[2] = "Ready";
				this.fireTableCellUpdated(i, 2);
				return;
			}
		}
		this.fireTableDataChanged();
	}

	public boolean check(String value, int column) {
		boolean result = false;
		String tem = "";
		for (int i = 0; i < data.size(); i++) {
			tem = ((String[]) (data.get(i)))[column];
			if (value.equalsIgnoreCase(tem)) {
				return true;
			}
		}
		return result;
	}

	public void update(String[] ss) {
		String[] dt = null;
		for (int i = 0; i < data.size(); i++) {
			dt = (String[]) (data.get(i));
			if (dt[0].equalsIgnoreCase(ss[0])) {
				if (id.equalsIgnoreCase("CustomerModel")) {
					dt[1] = ss[1];
					dt[2] = ss[2];
					dt[3] = ss[3];
					this.fireTableDataChanged();
					return;
				}
				if (id.equalsIgnoreCase("itemModel")) {
					dt[1] = ss[1];
					dt[2] = ss[2];
					dt[3] = ss[3];
					dt[4] = ss[4];
					// dt[5]=ss[5];
					this.fireTableDataChanged();
					return;
				}

			}

		}

	}

}
