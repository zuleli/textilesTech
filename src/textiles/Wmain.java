package textiles;
/* Author: Zule Li
 * Email:zule.li@hotmail.com
 * Last Modified Date:Mar.7,2015
 * */

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.table.TableColumn;

public class Wmain extends JFrame implements ActionListener {
	private JTabbedPane tab = new JTabbedPane();
	private JPanel panel1 = new JPanel(new BorderLayout());
	private JTable calTable, styTable, draTable, lTable;
	private Wmodel calModel, styModel, draModel, lModel;
	private JButton styUpdate, styNew, styGoto, styP, styN, draAdd, draDelete,
			draReset;
	private JButton loomP, loomN, loomGoto, loomUpdate;
	private JTextArea styNote = new JTextArea();
	private JLabel photo = new JLabel();
	private JComboBox style, styVer, loomDate;
	private int styleIndex = 0;
	private java.sql.Date loomD = null;

	public Wmain() {
		/*/
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        //*/
		try {
			init();
			jbInit();
			enableEvents(AWTEvent.WINDOW_EVENT_MASK);
			pack();
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Wmain wm = new Wmain();
	}

	private void jbInit() throws Exception {
		this.setTitle("RoxWell Synthetic Inc.");
		this.setSize(new Dimension(854, 591));
		tab.addTab("Calculator", panel1);
		panel1.add(new JScrollPane(calTable));

		JPanel styControl = new JPanel();
		styControl.add(styUpdate);
		styControl.add(styNew);
		styControl.add(styGoto);
		styControl.add(style);
		styControl.add(styVer);
		styControl.add(styP);
		styControl.add(styN);
		JPanel styU = new JPanel(new BorderLayout());
		styU.add(styControl, BorderLayout.NORTH);
		JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				new JScrollPane(styNote), photo);
		// styNote.setWrapStyleWord(true);
		photo.setAutoscrolls(true);

		JSplitPane styDetail = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				new JScrollPane(styTable), split2);

		styU.add(styDetail);
		// draTable.setPreferredScrollableViewportSize(new Dimension(100,50));
		JPanel draPanel = new JPanel(new BorderLayout());
		JPanel draControl = new JPanel();
		draControl.add(draAdd);
		draControl.add(draDelete);
		draControl.add(draReset);
		draPanel.add(draControl, BorderLayout.SOUTH);
		draPanel.add(new JScrollPane(draTable));
		JSplitPane split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, styU,
				draPanel);
		split1.setOneTouchExpandable(true);
		split2.setOneTouchExpandable(true);
		styDetail.setOneTouchExpandable(true);

		JPanel loomPane = new JPanel(new BorderLayout());
		JPanel lControl = new JPanel();
		lControl.add(loomGoto);
		lControl.add(loomDate);
		lControl.add(loomP);
		lControl.add(loomN);
		lControl.add(loomUpdate);
		loomPane.add(lControl, BorderLayout.NORTH);
		loomPane.add(new JScrollPane(lTable));

		tab.add("Styles", split1);
		tab.add("Looms", loomPane);
		this.getContentPane().add(tab, BorderLayout.CENTER);

	}

	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			dispose();
			System.exit(0);
		}
		super.processWindowEvent(e);
	}

	private int getIndex(String id) {
		for (int i = 0; i < style.getItemCount(); i++) {
			if (id.equalsIgnoreCase((String) (style.getItemAt(i))))
				return i;
		}
		return 0;
	}

	private void init() {
		String[] names = new String[] { "Y/P", "P/100Y", "Ground", "Filling",
				"Pick", "Binder", "Gut", "Edge", "Marker" };
		boolean[] editable = new boolean[] { false, false, true, true, true,
				true, true, true, true };
		calModel = new Wmodel(names, editable);
		calModel.setID("Calculator");
		calModel.setData(this.getForm1());
		calTable = new JTable(calModel);
		calTable.setRowHeight(30);

		names = new String[] { "StyleNo", "Version", "Width", "Pick", "FWidth",
				"FPick", "Y/P", "FY/P", "Date", "" };
		editable = new boolean[] { true, true, true, true, true, true, true,
				true, true, true };
		styModel = new Wmodel(names, editable);
		styModel.setData(this.getStyleForm());
		styModel.setID("Style");

		styTable = new JTable(styModel);
		styTable.setRowHeight(30);
		styTable.setAutoscrolls(true);

		names = new String[60];
		for (int i = 0; i < 60; i++) {
			names[i] = "" + i;
		}

		draModel = new Wmodel(names);
		draModel.setID("drawing");
		draModel.setData(this.getDrawing(50, 60));
		draTable = new JTable(draModel);
		draTable.setRowHeight(15);

		styUpdate = new JButton("Update");
		styNew = new JButton("New");
		styGoto = new JButton("Goto");
		styP = new JButton("Previous");
		styN = new JButton("Next");
		draAdd = new JButton("Add Row");
		draDelete = new JButton("Delete Row");
		draReset = new JButton("Reset");
		loomGoto = new JButton("Goto");
		loomP = new JButton("Previous");
		loomN = new JButton("Next");
		loomUpdate = new JButton("Update");
		loomUpdate.addActionListener(this);
		loomN.addActionListener(this);
		loomP.addActionListener(this);
		loomGoto.addActionListener(this);
		draReset.addActionListener(this);
		styUpdate.addActionListener(this);
		styNew.addActionListener(this);
		styGoto.addActionListener(this);
		styP.addActionListener(this);
		styN.addActionListener(this);
		draAdd.addActionListener(this);
		draDelete.addActionListener(this);

		Vector tem = Database.getStyleNos();
		style = new JComboBox(tem);
		style.addActionListener(this);
		styVer = new JComboBox(Database.getStyleVersions((String) style
				.getItemAt(0)));
		styVer.addItem("1");

		names = new String[] { "Loom No", "StyleNo", "First", "Second", "Third" };
		editable = new boolean[] { false, true, true, true, true };
		lModel = new Wmodel(names, editable);
		lModel.setID("Loom");
		lModel.setData(this.getLoomForm());
		lTable = new JTable(lModel);
		lTable.setRowHeight(30);
		TableColumn sportColumn = lTable.getColumnModel().getColumn(1);
		JComboBox comboBox = new JComboBox(tem);
		sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

		loomDate = new JComboBox();
		for (long i = 0; i < 30; i++) {
			loomDate.addItem(new java.sql.Date(System.currentTimeMillis() - i
					* 1000 * 60 * 60 * 24));
		}
		loomD = (java.sql.Date) (loomDate.getItemAt(0));
	}

	private Vector getForm1() {
		Vector modeldata = new Vector();
		String[] row1, row2, row3, row4;
		row1 = new String[] { " ", " ", " ", " ", " ", " ", " ", " ", "" };
		modeldata.add(row1);
		row1 = new String[] { " ", "End", " ", "1", "Width", " ", " ", "1", "" };
		modeldata.add(row1);
		row1 = new String[] { " ", "Cons", " 97", "96", " ", "90", "98", "70",
				"96" };
		modeldata.add(row1);
		return modeldata;
	}

	private Vector getStyleForm() {
		Vector modeldata = new Vector();
		String[] row1, row2, row3, row4;
		row1 = new String[] { " ", "1", " ", " ", " ", " ", " ", " ", " ", "" };
		modeldata.add(row1);
		row1 = new String[] { "Ground", "", "Filling", "", "Binder", "", "Gut",
				"", "Edge", "" };
		modeldata.add(row1);
		row1 = new String[] { "Ply", "1", "Ply", "1", "Ply", "1", "Ply", "1",
				"Ply", "1" };
		modeldata.add(row1);
		row1 = new String[] { "End", "", "Yarn", "", "End", "", "End", "",
				"End", "" };
		modeldata.add(row1);
		row1 = new String[] { "Yarn", "", "", "", "Yarn", "", "Yarn", "",
				"Yarn", "" };
		modeldata.add(row1);

		row1 = new String[] { "Marker", "", "Reed", "", "", "", "", "", "", "" };
		modeldata.add(row1);
		row1 = new String[] { "Ply", "1", "R.Plan", "", "", "", "", "", "", "" };
		modeldata.add(row1);
		row1 = new String[] { "End", "", "", "", "", "", "", "", "Memo", "" };
		modeldata.add(row1);
		row1 = new String[] { "Yarn", "", "", "", "", "", "", "", "Selected",
				"" };
		modeldata.add(row1);
		return modeldata;
	}

	private Vector getLoomForm() {
		Vector modeldata = new Vector();
		String[] row1;
		for (int i = 0; i < 10; i++) {
			row1 = new String[] { "" + (1 + i), " ", "0", "0", "0" };
			modeldata.add(row1);
		}

		return modeldata;
	}

	private Vector getDrawing(int row, int col) {
		Vector modeldata = new Vector();
		String[] row1;
		for (int j = 0; j < row; j++) {
			row1 = new String[col];
			for (int i = 0; i < col; i++) {
				if (j == 11 && i > 0) {
					row1[i] = "" + i;
					row1[i] = "" + row1[i].charAt(row1[i].length() - 1);
				} else if (j == 26 && i > 0 && i < 5) {
					row1[i] = "" + i;
				} else
					row1[i] = " ";
			}
			if (j <= 10 && j >= 5)
				row1[0] = "" + (11 - j);

			if (j == 14 || j == 15)
				row1[0] = "" + (16 - j);

			if (j >= 18 && j <= 25)
				row1[0] = "" + (26 - j);
			modeldata.add(row1);
		}
		return modeldata;
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == loomUpdate) {
			loomUpdateOp();
			return;
		}
		if (source == loomN) {
			loomNOp();
			return;
		}
		if (source == loomP) {
			loomPOp();
			return;
		}
		if (source == loomGoto) {
			loomGotoOp();
			return;
		}
		if (source == style) {
			styleOp();
			return;
		}
		if (source == draReset) {
			draResetOp();
			return;
		}
		if (source == styUpdate) {
			styUpdateOp();
			return;
		}
		if (source == styNew) {
			styNewOp();
			return;
		}
		if (source == styGoto) {
			styGotoOp();
			return;
		}
		if (source == styP) {
			styPOp();
			return;
		}
		if (source == styN) {
			styNOp();
			return;
		}
		if (source == draAdd) {
			draAddOp();
			return;
		}
		if (source == draDelete) {
			draDeleteOp();
			return;
		}

	}

	private void loomUpdateOp() {
		java.sql.Date ndate = (java.sql.Date) (loomDate.getSelectedItem());
		Database.updateLoomData(lModel.getData(),
				Database.getLoomStatus(ndate), ndate);
	}

	private void loomNOp() {
		loomD = new java.sql.Date(loomD.getTime() + 1000 * 60 * 60 * 24);
		;
		lModel.setData(Database.getLoomData(loomD));
		loomDate.setSelectedItem(loomD);
	}

	private void loomPOp() {
		loomD = new java.sql.Date(loomD.getTime() - 1000 * 60 * 60 * 24);
		;
		lModel.setData(Database.getLoomData(loomD));
		loomDate.setSelectedItem(loomD);
	}

	private void loomGotoOp() {
		loomD = (java.sql.Date) (loomDate.getSelectedItem());
		lModel.setData(Database.getLoomData(loomD));
	}

	private void styUpdateOp() {

		String[] ss = styModel.getStyleData();
		if (ss[0] == null || (ss[0].trim()).length() == 0) {
			JOptionPane.showMessageDialog(styTable, "Missing Style Number");
			return;
		}
		ss[35] = draModel.getDraw();
		ss[36] = styNote.getText().trim();
		for (int i = 0; i < ss.length; i++) {
			if (i == 0 || i == 11 || i == 18 || i == 22 || i == 26 || i == 30
					|| i == 32 || i == 33 || i == 35 || i == 36)
				continue;
			else if (ss[i] == null || (ss[i].trim()).length() == 0)
				ss[i] = "0";
			else
				ss[i] = ss[i].trim();
		}
		int status = Database.getStatus(ss[0], Integer.parseInt(ss[1]));
		if (Database.addStyle(ss, status)) {
			if (status == 0) {
				style.addItem(ss[0]);
			}
		} else
			JOptionPane.showMessageDialog(styTable, "Update Failed");
	}

	private void styleOp() {
		Vector data = Database.getStyleVersions((String) (style
				.getSelectedItem()));
		styVer.removeAllItems();
		for (int i = 0; i < data.size(); i++) {
			styVer.addItem(data.get(i));
		}
	}

	private void draResetOp() {
		draModel.setData(this.getDrawing(50, 60));
	}

	private void styNewOp() {
		styModel.setData(this.getStyleForm());
	}

	private void styGotoOp() {
		String[] ss = Database.getStyleData((String) (style.getSelectedItem()),
				Integer.parseInt((String) styVer.getSelectedItem()));
		styModel.setData(ss);
		draModel.setData(ss);
		styNote.setText(ss[36]);
		styleIndex = getIndex(ss[0]);
	}

	private void styPOp() {

		if ((styleIndex - 1) < 0)
			return;
		String[] ss = Database.getStyleData(
				(String) (style.getItemAt(styleIndex - 1)), 1);
		styModel.setData(ss);
		draModel.setData(ss);
		styNote.setText(ss[36]);
		styleIndex = getIndex(ss[0]);
	}

	private void styNOp() {

		if ((styleIndex + 1) > (style.getItemCount() - 1))
			return;
		String[] ss = Database.getStyleData(
				(String) (style.getItemAt(styleIndex + 1)), 1);
		styModel.setData(ss);
		draModel.setData(ss);
		styNote.setText(ss[36]);
		styleIndex = getIndex(ss[0]);
	}

	private void draAddOp() {
		String[] ss = new String[60];
		int index = draTable.getSelectedRow();
		if (index < 0)
			draModel.addData(ss);
		else
			draModel.addData(ss, index);
	}

	private void draDeleteOp() {
		int index = draTable.getSelectedRow();
		if (index < 0)
			return;
		draModel.delete(index);
	}
}