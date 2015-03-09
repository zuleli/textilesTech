package textiles;

/* Author: Zule Li
 * Email:zule.li@hotmail.com
 * Last Modified Date:Mar.7,2015
 * */

import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Database extends Object {

	private static String source = "jdbc:odbc:RoxWell";
	private static Connection con;

	/**
	 * Constructor
	 */
	public Database() {

	}

	public static boolean removeSetting(String value, String resid) {

		String str = "";

		str = "delete from Catagory where (restaurantid=? and catagory=?)";

		boolean ok = false;
		try {
			con = DriverManager.getConnection(source);
			PreparedStatement pst = con.prepareStatement(str);
			pst.setString(1, resid);
			pst.setString(2, value);
			int index = pst.executeUpdate();
			if (index > 0)
				ok = true;

			pst.close();
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return ok;
	}

	public static boolean addStyle(String[] basic, int status) {
		System.out.println("Size=" + basic.length + " status=" + status);

		/**
		 * 0=spID,1-spName,2=spStreet,3=spUnit,4=spCity,5=spState,6=spZip,7=
		 * spCountry,8=spPhone,9=spFax,10=spEmail,11=spType 12-spContactPerson,
		 * 13-spWebsite,14-spDes,15-spNote.
		 * 
		 * //
		 */

		String str = "";
		if (status == 0)
			str = "insert into Styles  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		if (status == 1)
			str = "update Styles set width=?,pick=?,fwidth=?,fpick=?,yp=?,fyp=?,Ground=?,Gply=?,Gend=?"
					+ ",GYarn=?,Filling=?,FPly=?,FYarn=?,Binder=?,BPly=?,BEnd=?,BYarn=?,Gut=?,GutPly=?,GutEnd=?,GutYarn=?"
					+ ",Edge=?,EPly=?,EEnd=?,Eyarn=?,Marker=?,MPly=?,MEnd=?,MYarn=?,Reed=?,ReedPlan=?,SMemo=?,Selected=?"
					+ ",pattern=?,styleNote=?,DateModified=? where (StyleNo=? and Version=?) ";

		// String str="update customers set firstName=?,lastName=?,Street=?"+
		// ",apartment=?,city=?,State=?,zip=?,phone=? where accountNo=?";

		boolean ok = false;
		try {
			con = DriverManager.getConnection(source);
			con.setAutoCommit(false);
			PreparedStatement pst = con.prepareStatement(str);

			pst.setDouble(1, Double.parseDouble(basic[2]));
			pst.setDouble(2, Double.parseDouble(basic[3]));
			pst.setDouble(3, Double.parseDouble(basic[4]));
			pst.setDouble(4, Double.parseDouble(basic[5]));
			pst.setDouble(5, Double.parseDouble(basic[6]));
			pst.setDouble(6, Double.parseDouble(basic[7]));
			pst.setDouble(7, Double.parseDouble(basic[8]));
			pst.setInt(8, Integer.parseInt(basic[9]));
			pst.setInt(9, Integer.parseInt(basic[10]));
			pst.setString(10, basic[11]);
			pst.setDouble(11, Double.parseDouble(basic[12]));
			pst.setInt(12, Integer.parseInt(basic[13]));
			pst.setString(13, basic[14]);
			pst.setDouble(14, Double.parseDouble(basic[15]));
			pst.setInt(15, Integer.parseInt(basic[16]));
			pst.setInt(16, Integer.parseInt(basic[17]));
			pst.setString(17, basic[18]);
			pst.setDouble(18, Double.parseDouble(basic[19]));
			pst.setInt(19, Integer.parseInt(basic[20]));
			pst.setInt(20, Integer.parseInt(basic[21]));
			pst.setString(21, basic[22]);
			pst.setDouble(22, Double.parseDouble(basic[23]));
			pst.setInt(23, Integer.parseInt(basic[24]));
			pst.setInt(24, Integer.parseInt(basic[25]));
			pst.setString(25, basic[26]);
			pst.setDouble(26, Double.parseDouble(basic[27]));
			pst.setInt(27, Integer.parseInt(basic[28]));
			pst.setInt(28, Integer.parseInt(basic[29]));
			pst.setString(29, basic[30]);
			pst.setDouble(30, Double.parseDouble(basic[31]));
			pst.setString(31, basic[32]);
			pst.setString(32, basic[33]);
			pst.setInt(33, Integer.parseInt(basic[34]));
			pst.setString(34, basic[35]);
			pst.setString(35, basic[36]);
			pst.setDate(36, new java.sql.Date(System.currentTimeMillis()));

			if (status == 0) {

				pst.setDate(37, new java.sql.Date(System.currentTimeMillis()));
				pst.setString(38, basic[0]);
				pst.setInt(39, Integer.parseInt(basic[1]));
			} else if (status == 1) {

				pst.setString(37, basic[0]);
				pst.setInt(38, Integer.parseInt(basic[1]));
			}

			int index = pst.executeUpdate();
			if (index < 0) {

				con.rollback();
				pst.close();
				con.setAutoCommit(true);
				con.close();
				// p.p("index="+index);
				return ok;
			}

			con.commit();
			con.setAutoCommit(true);
			pst.close();
			con.close();
			return true;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}

		return ok;
	}

	public static boolean updateLoomData(Vector data, int status,
			java.sql.Date nDate) {
		String str = "";
		if (status == 0)
			str = "insert into Looms  values (?,?,?,?,?,?)";
		else if (status == 1)
			str = "update looms set styleno=?,first=?,second=?,third=? where (loomno=? and pddate=?)";

		boolean ok = false;
		try {
			con = DriverManager.getConnection(source);
			con.setAutoCommit(false);
			PreparedStatement pst = con.prepareStatement(str);

			pst = con.prepareStatement(str);
			String[] basic = null;
			for (int i = 0; i < data.size(); i++) {
				basic = (String[]) (data.get(i));
				// p.p(""+basic.length);
				pst.setString(1, basic[1]);
				pst.setInt(2, Integer.parseInt(basic[2]));
				pst.setInt(3, Integer.parseInt(basic[3]));
				pst.setInt(4, Integer.parseInt(basic[4]));
				pst.setString(5, basic[0]);
				pst.setDate(6, nDate);

				pst.executeUpdate();
			}

			con.commit();
			con.setAutoCommit(true);
			pst.close();
			con.close();
			return true;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return ok;
	}

	public static boolean addTechData(Vector data, int id) {

		String str = "delete from TechData where id=?";
		boolean ok = false;
		try {
			con = DriverManager.getConnection(source);
			con.setAutoCommit(false);
			PreparedStatement pst = con.prepareStatement(str);
			pst.setInt(1, id);
			pst.executeUpdate();

			str = "insert into TechData  values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst.close();

			pst = con.prepareStatement(str);
			String[] basic = null;
			for (int i = 0; i < data.size(); i++) {
				basic = (String[]) (data.get(i));
				// p.p(""+basic.length);
				pst.setInt(1, id);
				pst.setString(2, basic[0]);
				pst.setString(3, basic[1]);
				pst.setString(4, basic[2]);
				pst.setString(5, basic[3]);
				pst.setString(6, basic[4]);
				pst.setString(7, basic[5]);
				pst.setString(8, basic[6]);
				pst.setString(9, basic[7]);
				pst.setString(10, basic[8]);
				pst.setString(11, basic[9]);
				pst.setString(12, basic[10]);
				pst.setString(13, basic[11]);

				pst.executeUpdate();
			}

			con.commit();
			con.setAutoCommit(true);
			pst.close();
			con.close();
			return true;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return ok;
	}

	public static boolean updateCustomer(String[] basic) {

		/**
		 * 0=accountNO,1-first,2=last,3=street,4=apt,5=city,6=state,7=zip,8=
		 * phone,9=createDate,10=EndDate,11=status
		 * ss[0]=customerID.getText().trim(); ss[1]=firstName.getText().trim();
		 * ss[2]=lastName.getText().trim(); ss[3]=un1.getText().trim();//street
		 * ss[4]=un2.getText().trim();//aparty ss[5]=city.getText().trim();
		 * ss[6]=state.getSelectedItem().toString().trim();
		 * ss[7]=un4.getText().trim();//zip ss[8]=phone_Res.getText().trim();
		 * ss[9]=RESTAURANTID; //
		 */
		String str = "update Customers  set firstName=?,LastName=?,Street=?,Apartment=?,city=?,state=?"
				+ ",zip=?,phone=? where (restaurantid=? and customerid=?)";
		boolean ok = false;
		try {
			con = DriverManager.getConnection(source);
			con.setAutoCommit(false);
			PreparedStatement pst = con.prepareStatement(str);

			pst.setString(1, basic[2]);
			pst.setString(2, basic[3]);
			pst.setString(3, basic[4]);
			pst.setString(4, basic[5]);
			pst.setString(5, basic[6]);
			pst.setString(6, basic[7]);
			pst.setString(7, basic[8]);
			pst.setString(8, basic[9]);
			pst.setString(9, basic[0]);
			pst.setString(10, basic[1]);

			int index = pst.executeUpdate();
			if (index <= 0) {
				pst.close();
				con.rollback();
				con.setAutoCommit(true);
				con.close();
				return ok;
			}

			con.commit();
			con.setAutoCommit(true);
			pst.close();
			con.close();
			return true;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return ok;
	}

	public static Vector getLoomData(java.sql.Date ndate) {
		Vector data = new Vector();
		String[] tem = null;
		// *
		String str = "select * from looms where pdDate=?";
		try {
			con = DriverManager.getConnection(source);
			PreparedStatement ste = con.prepareStatement(str);
			ste.setDate(1, ndate);
			ResultSet rs = ste.executeQuery();
			while (rs.next()) {
				tem = new String[5];
				// 0==itemID,1=title,2=dateout,3=datedue
				tem[0] = rs.getString("LoomNo");
				tem[1] = rs.getString("StyleNo");
				tem[2] = "" + rs.getInt("first");
				tem[3] = "" + rs.getInt("second");
				tem[4] = "" + rs.getInt("third");
				data.add(tem);
			}
			rs.close();
			ste.close();
			con.close();
			return data;
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		// */
		return data;
	}

	public static boolean orderStatusChange(String orderno) {
		boolean status = false;
		String str = "UPDATE planning SET status=1 WHERE (order='" + orderno
				+ "' AND status=2)";
		try {
			con = DriverManager.getConnection(source);
			Statement ste = con.createStatement();
			int in = ste.executeUpdate(str);
			ste.close();
			con.close();
			if (in >= 1)
				status = true;
			return status;
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return false;
	}

	public static Vector getMeasureData() {
		String str = "select * from measurement where status=1";
		String[] ss = null;
		Vector v = new Vector();

		try {
			con = DriverManager.getConnection(source);
			Statement ste = con.createStatement();
			str = "select mc,position,stock,end,beamsize,measurement,"
					+ "((select capacity/(RO*RO-RI*RI)*((RO-measurement)*(RO-measurement)-RI*RI) from emptybeam where size=beamsize)/end*453.6/"
					+ "(select Rawm.size*Rawm.modify from Rawm where Rawm.stock=Measurement.stock)*9000/"
					+ "((select (speed*efficiency/100) from MCS where MCS.MC=measurement.mc)/"
					+ "(SELECT pick from styles where styles.styleno=(select styleno from mcs where MCS.MC=measurement.mc))/36*0.9199*60)),"
					+

					"(select sum([status])from beams where(status=1 and beams.stock=measurement.stock and beams.end=measurement.end)),"
					+

					"(select capacity/(RO*RO-RI*RI)*((RO-measurement)*(RO-measurement)-RI*RI) from emptybeam where size=beamsize)/end*453.6/"
					+ "(select Rawm.size*Rawm.modify from Rawm where Rawm.stock=Measurement.stock)*9000*0.9199*"
					+ "(SELECT (pick/Fpick) from styles where styles.styleno=(select styleno from mcs where MCS.MC=measurement.mc)),"
					+

					"hot,orderno from measurement where status=1";

			ResultSet rs = ste.executeQuery(str);
			long lon = 0;
			while (rs.next()) {
				ss = new String[12];
				// System.out.println("try0="+rs.getDouble(1));
				ss[0] = rs.getString("MC");
				ss[1] = "" + rs.getByte("Position");
				ss[2] = rs.getString("Stock");
				ss[3] = "" + rs.getInt("End");
				ss[4] = "" + rs.getByte("BeamSize");
				ss[5] = "" + rs.getDouble("Measurement");
				lon = (long) (rs.getDouble(7));
				ss[6] = ""
						+ (new java.sql.Date(System.currentTimeMillis() + lon
								* 60 * 60 * 1000)).toString();// dateOUt
				ss[7] = "" + rs.getInt(8);// Invertory
				ss[8] = "" + (int) (rs.getDouble(9));// yard
				// System.out.println(ss[6]);
				ss[10] = rs.getString("hot");
				ss[11] = rs.getString("Orderno");// */
				v.add(ss);
			}
			rs.close();
			ste.close();
			con.close();
			// System.out.println("try2");
			return v;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;

	}

	public static boolean updateItem(String[] ss) {
		boolean ok = false;
		try {
			con = DriverManager.getConnection(source);
			String str = "update menu set name=?,price=?"
					+ ",hot=?,catagory=?,status=?,description=? where (restaurantID=? and itemid=?)";
			System.out.println(str);
			PreparedStatement ste = con.prepareStatement(str);
			ste.setString(1, ss[2]);
			ste.setDouble(2, Double.parseDouble(ss[3]));
			ste.setString(3, ss[4]);
			ste.setString(4, ss[5]);
			ste.setString(5, ss[6]);
			ste.setString(6, ss[7]);
			ste.setString(7, ss[0]);
			ste.setString(8, ss[1]);
			int ind = ste.executeUpdate();
			if (ind > 0) {
				ok = true;
			}
			ste.close();
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return ok;

	}

	public static boolean updateSUPPLIER(String[] basic) {

		/**
		 * 0=accountNO,1-first,2=last,3=street,4=apt,5=city,6=state,7=zip,8=
		 * phone,9=createDate,10=EndDate,11=status
		 * ss[1]=businessName_Res.getText().trim();
		 * ss[2]=street_Res.getText().trim(); ss[3]=unit_Res.getText().trim();
		 * ss[4]=city_Res.getText().trim();
		 * ss[5]=state_Res.getSelectedItem().toString().trim();
		 * ss[6]=zip_Res.getText().trim(); ss[7]=phone_Res.getText().trim();
		 * ss[8]=abn ss[9]=firstName_Res.getText().trim();
		 * ss[10]=lastName_Res.getText().trim();
		 * ss[11]=cellPhone_Res.getText().trim();
		 * ss[12]=phone3_Res.getText().trim();
		 * 
		 * //
		 */
		boolean ok = false;
		try {
			con = DriverManager.getConnection(source);
			String str = "update suppliers set CompanyName=?"
					+ ",Street=?,Unit=?,City=?,State=?,Zip=?,Country=?,Phone=?,Fax=?,Email=?,Type=?,Contact=?,Website=?,SPNote=?,ModifyDate=? where SupplierID=? ";// ,Note=?
			PreparedStatement pst = con.prepareStatement(str);

			pst.setString(1, basic[1]);
			pst.setString(2, basic[2]);
			pst.setString(3, basic[3]);
			pst.setString(4, basic[4]);
			pst.setString(5, basic[5]);
			pst.setString(6, basic[6]);
			pst.setString(7, basic[7]);
			pst.setString(8, basic[8]);
			pst.setString(9, basic[9]);
			pst.setString(10, basic[10]);
			pst.setString(11, basic[11]);
			pst.setString(12, basic[12]);
			pst.setString(13, basic[13]);
			// pst.setString(14,basic[14]);
			pst.setString(14, basic[15]);
			pst.setDate(15, new java.sql.Date(System.currentTimeMillis()));// */
			pst.setInt(16, Integer.parseInt(basic[0]));

			int ind = pst.executeUpdate();

			if (ind > 0) {
				ok = true;
			}
			pst.close();
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return ok;

	}

	public static String deleteItem(String[] itid, String[] resid) {
		String ok = null;
		try {
			con = DriverManager.getConnection(source);
			// 0-id,1=title,2=version,3=location,4=catagory,5-publisher,6=summary,7=createDay
			String str = "delete from menu  where (restaurantid=? and itemid=?)";
			PreparedStatement ste = con.prepareStatement(str);
			for (int i = 0; i < itid.length; i++) {
				ste.setString(1, resid[i]);
				ste.setString(2, itid[i]);
				int ind = ste.executeUpdate();
				if (ind <= 0) {
					if (ok == null)
						ok = "The Following item failed to delete from database:\nRestaurant ID:"
								+ resid[i] + ", ItemID:" + itid[i];
					else
						ok = ok + "\nRestaurant ID:" + resid[i] + ", ItemID:"
								+ itid[i];
				}
			}
			ste.close();
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return ok;
	}

	public static String deleteRestaurant(String[] itid) {
		String ok = null;
		try {
			con = DriverManager.getConnection(source);
			// 0-id,1=title,2=version,3=location,4=catagory,5-publisher,6=summary,7=createDay
			String str = "delete from Restaurants  where restaurantid=?";
			System.out.println(str);
			PreparedStatement ste = con.prepareStatement(str);
			for (int i = 0; i < itid.length; i++) {
				ste.setString(1, itid[i]);
				int ind = ste.executeUpdate();
				if (ind < 0) {
					if (ok == null)
						ok = "The Following item failed to delete from database:\nRestaurant ID:"
								+ itid[i];
					else
						ok = ok + "\nRestaurant ID:" + itid[i];
				}
			}
			ste.close();
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return ok;
	}

	public static boolean updateAccount(String[] ss) {
		// 0=accountNO,1-first,2=last,3=street,4=apt,5=city,6=state,7=zip,8=phone,9=createDate,10=EndDate,11=status
		boolean ok = false;
		try {
			con = DriverManager.getConnection(source);
			String str = "update customers set firstName=?,lastName=?,Street=?"
					+ ",apartment=?,city=?,State=?,zip=?,phone=? where accountNo=?";
			System.out.println(str);
			PreparedStatement ste = con.prepareStatement(str);
			ste.setString(1, ss[1]);
			ste.setString(2, ss[2]);
			ste.setString(3, ss[3]);
			ste.setString(4, ss[4]);
			ste.setString(5, ss[5]);
			ste.setString(6, ss[6]);
			ste.setString(7, ss[7]);
			ste.setString(8, ss[8]);
			ste.setString(9, ss[0]);
			int ind = ste.executeUpdate();
			if (ind > 0) {
				ok = true;
			}
			ste.close();
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return ok;

	}

	public static String[] getMeasueUpdate(String[] ss) {
		String[] out = new String[3];
		try {
			con = DriverManager.getConnection(source);
			String str = "select((select capacity/(RO*RO-RI*RI)*((RO-measurement)*(RO-measurement)-RI*RI) from emptybeam where size=beamsize)/end*453.6/"
					+ "(select Rawm.size*Rawm.modify from Rawm where Rawm.stock=Measurement.stock)*9000/"
					+ "((select (speed*efficiency/100) from MCS where MCS.MC=measurement.mc)/"
					+ "(SELECT pick from styles where styles.styleno=(select styleno from mcs where MCS.MC=measurement.mc))/36*0.9199*60)),"
					+

					"(select sum([status])from beams where(status=1 and beams.stock=measurement.stock and beams.end=measurement.end)),"
					+

					"(select capacity/(RO*RO-RI*RI)*((RO-measurement)*(RO-measurement)-RI*RI) from emptybeam where size=beamsize)/end*453.6/"
					+ "(select Rawm.size*Rawm.modify from Rawm where Rawm.stock=Measurement.stock)*9000*0.9199*"
					+ "(SELECT (pick/Fpick) from styles where styles.styleno=(select styleno from mcs where MCS.MC=measurement.mc))"
					+

					" from measurement where (mc='"
					+ ss[0]
					+ "' and position="
					+ ss[1] + ")";
			Statement ste = con.createStatement();
			ResultSet rs = ste.executeQuery(str);
			if (rs.next()) {

				long lon = (long) (rs.getDouble(1));
				out[0] = ""
						+ (new java.sql.Date(System.currentTimeMillis() + lon
								* 60 * 60 * 1000)).toString();// dateOUt
				out[1] = "" + rs.getInt(2);
				out[2] = "" + (int) (rs.getDouble(3));
			}
			ste.close();
			con.close();
			return out;
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return null;

	}

	public static String setInActive(String[] ss) {
		String ok = null;
		try {
			con = DriverManager.getConnection(source);
			String str = "update customers set status='InActive' where accountno=?";
			PreparedStatement ste = con.prepareStatement(str);
			for (int i = 0; i < ss.length; i++) {
				ste.setString(1, ss[i]);
				int ind = ste.executeUpdate();
				if (ind < 0) {
					if (ok == null)
						ok = "The Following item failed to set InActive from database:\nAccount#:";
					else
						ok = ok + "\nAccount#:" + ss[i];
				}
			}
			ste.close();
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return ok;
	}

	public static boolean setLocalPort(String s) {
		boolean result = false;
		try {
			con = DriverManager.getConnection(source);

			String str = "update locals set localPort=?";
			PreparedStatement ste = con.prepareStatement(str);
			ste.setString(1, s);
			int ind = ste.executeUpdate();
			if (ind > 0) {
				result = true;
			}
			ste.close();
			con.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return result;
	}

	/**
	 * public static long getOrderID() { try {
	 * con=DriverManager.getConnection(source); Statement
	 * ste=con.createStatement(); ResultSet rs=null; String
	 * str="select max(TransactionNO) from Transactions"; long result=0;
	 * rs=ste.executeQuery(str); if(rs.next()) { result=rs.getLong(1);
	 * rs.close(); ste.close(); con.close(); return result; }
	 * 
	 * }catch(SQLException sqle) { sqle.printStackTrace();
	 * 
	 * } return 0; } //
	 */
	public static int getStatus(String id, int version) {

		String s = "";

		try {
			con = DriverManager.getConnection(source);

			ResultSet rs = null;
			String str = "select * from Styles where (StyleNo=? and Version=?)";
			PreparedStatement ste = con.prepareStatement(str);
			ste.setString(1, id);
			ste.setInt(2, version);
			rs = ste.executeQuery();
			if (rs.next()) {

				rs.close();
				ste.close();
				con.close();

				return 1;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return 0;
	}

	public static int getLoomStatus(java.sql.Date ndate) {

		String s = "";

		try {
			con = DriverManager.getConnection(source);

			ResultSet rs = null;
			String str = "select * from looms where PDDate=?";
			PreparedStatement ste = con.prepareStatement(str);
			ste.setDate(1, ndate);

			rs = ste.executeQuery();
			if (rs.next()) {

				rs.close();
				ste.close();
				con.close();

				return 1;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return 0;
	}

	public static int getMaxID() {

		try {
			con = DriverManager.getConnection(source);

			String str = "select Max(supplierID) from Companies";

			Statement ste = con.createStatement();
			ResultSet rs = ste.executeQuery(str);
			if (rs.next()) {
				int result = rs.getInt(1);
				rs.close();
				ste.close();
				con.close();

				return result;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return 0;
	}

	public static Vector getCustomerIDs() {

		Vector data = new Vector();
		try {
			con = DriverManager.getConnection(source);

			String str = "select customerID from Customers";

			Statement ste = con.createStatement();
			ResultSet rs = ste.executeQuery(str);
			while (rs.next()) {
				data.add("" + rs.getInt("customerID"));

			}
			rs.close();
			ste.close();
			con.close();
			return data;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return null;
	}

	public static Vector getSupplierIDs() {

		Vector data = new Vector();
		try {
			con = DriverManager.getConnection(source);

			String str = "select supplierID from Companies";

			Statement ste = con.createStatement();
			ResultSet rs = ste.executeQuery(str);
			while (rs.next()) {
				data.add("" + rs.getInt("SupplierID"));

			}
			rs.close();
			ste.close();
			con.close();
			return data;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return null;
	}

	public static long getNextValue(String id, String resid) {

		long s = 0;

		try {
			con = DriverManager.getConnection(source);

			ResultSet rs = null;
			String str = "select * from NextValue where restaurantid=?";
			PreparedStatement ste = con.prepareStatement(str);
			ste.setString(1, resid);

			rs = ste.executeQuery();
			if (rs.next()) {

				s = (long) (rs.getDouble(id));
				rs.close();
				ste.close();
				con.close();
				// p.p("resid="+resid+" s="+s+"  id="+id);
				return s;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return s;
	}

	public static boolean checkForItemID(String itemid, String resid,
			String type) {
		boolean result = false;
		try {
			con = DriverManager.getConnection(source);

			ResultSet rs = null;
			String str = "";
			if (type.equalsIgnoreCase("AccountNO"))
				str = "select * from customers where (restaurantid=? and customerID=?)";
			if (type.equalsIgnoreCase("ItemID"))
				str = "select * from menu where (restaurantid=? and itemID=?)";
			PreparedStatement ste = con.prepareStatement(str);
			ste.setString(1, resid);
			ste.setString(2, itemid);
			rs = ste.executeQuery();

			if (rs.next()) {
				result = true;

			}
			rs.close();
			ste.close();
			con.close();
			return result;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"unknown,database connection problem");

		}
		return true;
	}

	public static boolean checkForValid(String id, String type) {
		boolean result = false;
		try {
			con = DriverManager.getConnection(source);

			ResultSet rs = null;
			String str = "";
			if (type.equalsIgnoreCase("ItemID"))
				str = "select * from menu where itemid=?";
			if (type.equalsIgnoreCase("RestaurantID"))
				str = "select * from Restaurants where Restaurantid=?";
			PreparedStatement ste = con.prepareStatement(str);
			ste.setString(1, id);
			rs = ste.executeQuery();

			if (rs.next()) {
				result = true;

			}
			rs.close();
			ste.close();
			con.close();
			return result;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, type
					+ " unknown,database connection problem");

		}
		return true;
	}

	public static String[] getItem(String itemid, String resid) {
		String[] ss = new String[7];
		try {
			con = DriverManager.getConnection(source);

			ResultSet rs = null;
			String str = "select * from menu where (restaurantid=? and itemid=?)";
			PreparedStatement ste = con.prepareStatement(str);
			ste.setString(1, resid);
			ste.setString(2, itemid);

			rs = ste.executeQuery();
			// 0-id,1=title,2=version,3=location,4=catagory,5-publisher,6=summary,7=createDay,8=status,9=account
			if (rs.next()) {

				ss[0] = (rs.getString("ItemID")).trim();
				ss[1] = (rs.getString("Name")).trim();
				ss[2] = "" + rs.getDouble("Price");
				ss[3] = (rs.getString("Hot")).trim();
				ss[4] = (rs.getString("Catagory")).trim();
				ss[5] = (rs.getString("Status")).trim();
				ss[6] = (rs.getString("Description")).trim();
				rs.close();
				ste.close();
				con.close();
				return ss;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return ss;

	}

	public static void addErrorMessage(String s) {

	}

	public static void toHistory(Vector returns) {
		String[] basic = new String[8];
		String str = "insert into History values(?,?,?,?,?,?)";

		try {
			con = DriverManager.getConnection(source);
			PreparedStatement pst = con.prepareStatement(str);
			for (int i = 0; i < returns.size(); i++) {
				basic = (String[]) (returns.get(i));
				pst.setString(1, basic[0]);
				pst.setString(2, basic[5]);
				pst.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				pst.setString(4, basic[2]);
				pst.setString(5, basic[7]);
				pst.setString(6, basic[6]);

				int index = pst.executeUpdate();
				System.out.println("tohistory= " + basic[5] + "  index="
						+ index);

			}
			pst.close();
			con.close();

			// if(!Database.updateNextValue("Account",(acct+1)))
			// JOptionPane.showMessageDialog(null,"NextAccountNo update failed");
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
	}

	public static String getPath(String type) {
		// photopath,webphotopath
		try {
			con = DriverManager.getConnection(source);

			ResultSet rs = null;
			String str = "select * from paths";
			Statement ste = con.createStatement();
			rs = ste.executeQuery(str);
			if (rs.next()) {
				str = rs.getString(type);
				rs.close();
				ste.close();
				con.close();
				return str;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return null;
	}

	public static boolean setGreetingMessage(String message) {
		boolean ok = false;
		try {
			con = DriverManager.getConnection(source);
			String str = "update NextValue set greeting=?";
			PreparedStatement ste = con.prepareStatement(str);
			ste.setString(1, message);
			int ind = ste.executeUpdate();
			if (ind > 0) {
				ok = true;
			}
			ste.close();
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return ok;
	}

	public static String[] getStyleData(String styleno, int version) {

		String[] data = new String[38];
		try {
			con = DriverManager.getConnection(source);
			String str = "select * from Styles where styleno=? and version=?";
			PreparedStatement ste = con.prepareStatement(str);
			ste.setString(1, styleno);
			ste.setInt(2, version);
			ResultSet rs = ste.executeQuery();
			while (rs.next()) {

				data[2] = "" + rs.getDouble(1);
				data[3] = "" + rs.getDouble(2);
				data[4] = "" + rs.getDouble(3);
				data[5] = "" + rs.getDouble(4);
				data[6] = "" + rs.getDouble(5);
				data[7] = "" + rs.getDouble(6);
				data[8] = "" + rs.getDouble(7);
				data[9] = "" + rs.getInt(8);
				data[10] = "" + rs.getInt(9);
				data[11] = rs.getString(10);
				data[12] = "" + rs.getDouble(11);
				data[13] = "" + rs.getInt(12);
				data[14] = rs.getString(13);
				data[15] = "" + rs.getDouble(14);
				data[16] = "" + rs.getInt(15);
				data[17] = "" + rs.getInt(16);
				data[18] = rs.getString(17);
				data[19] = "" + rs.getDouble(18);
				data[20] = "" + rs.getInt(19);
				data[21] = "" + rs.getInt(20);
				data[22] = rs.getString(21);
				data[23] = "" + rs.getDouble(22);
				data[24] = "" + rs.getInt(23);
				data[25] = "" + rs.getInt(24);
				data[26] = rs.getString(25);
				data[27] = "" + rs.getDouble(26);
				data[28] = "" + rs.getInt(27);
				data[29] = "" + rs.getInt(28);
				data[30] = rs.getString(29);
				data[31] = "" + rs.getDouble(30);
				data[32] = rs.getString(31);
				data[33] = rs.getString(32);
				data[34] = "" + rs.getInt(33);
				data[35] = rs.getString(34);
				data[36] = rs.getString(35);
				data[37] = (rs.getDate(36)).toString();
				data[0] = rs.getString(38);
				data[1] = rs.getString(39);

			}
			rs.close();
			ste.close();
			con.close();
			return data;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return data;
	}

	public static Vector getType() {

		Vector data = new Vector();
		try {
			con = DriverManager.getConnection(source);
			Statement ste = con.createStatement();
			String str = "select * from Types";
			ResultSet rs = ste.executeQuery(str);
			while (rs.next()) {
				data.add(rs.getString("Name"));
			}
			rs.close();
			ste.close();
			con.close();
			return data;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return data;
	}

	public static Vector getState() {

		Vector data = new Vector();
		try {
			con = DriverManager.getConnection(source);
			Statement ste = con.createStatement();
			String str = "select * from State";
			ResultSet rs = ste.executeQuery(str);
			while (rs.next()) {
				data.add(rs.getString("Name"));
			}
			rs.close();
			ste.close();
			con.close();
			return data;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return data;
	}

	public static Vector getStyleVersions(String id) {

		Vector data = new Vector();
		try {
			con = DriverManager.getConnection(source);
			String str = "";

			str = "select * from Styles where StyleNo=?";
			ResultSet rs = null;
			PreparedStatement ste = con.prepareStatement(str);
			ste.setString(1, id);

			rs = ste.executeQuery();
			// 0-first,1=last,2=street,3=apt,4=city,5=state,6=zip,7=phone
			while (rs.next()) {
				data.add("" + rs.getInt("Version"));
			}
			rs.close();
			ste.close();
			con.close();
			return data;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return data;
	}

	public static String getItemStatus(String id) {
		String s = "";
		try {
			con = DriverManager.getConnection(source);

			ResultSet rs = null;
			String str = "select * from Items where itemid=?";
			PreparedStatement ste = con.prepareStatement(str);
			ste.setString(1, id);
			rs = ste.executeQuery();

			rs = ste.executeQuery(str);
			// 0-first,1=last,2=street,3=apt,4=city,5=state,6=zip,7=phone
			if (rs.next()) {

				s = (rs.getString("Status")).trim();
				rs.close();
				ste.close();
				con.close();
				return s;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return s;
	}

	public static String[] getRestaurants(String id, String status) {

		String[] ss = null;
		try {
			con = DriverManager.getConnection(source);
			String str = "select * from Restaurants where RestaurantID=? ";
			PreparedStatement ste = con.prepareStatement(str);
			ste.setString(1, id);
			ResultSet rs = ste.executeQuery();
			if (rs.next()) {
				if (status.equalsIgnoreCase("Detail"))
					ss = new String[14];
				else if (status.equalsIgnoreCase("Summary"))
					ss = new String[9];
				else if (status.equalsIgnoreCase("Browse"))
					ss = new String[13];
				ss[0] = (rs.getString("restaurantID")).trim();
				ss[1] = (rs.getString("Businessname")).trim();
				ss[2] = (rs.getString("City")).trim();
				ss[3] = (rs.getString("State")).trim();
				ss[4] = rs.getString("Zip").toString();
				ss[5] = (rs.getString("Phone")).trim();
				ss[6] = (rs.getString("ABName")).trim();
				ss[7] = (rs.getString("FirstName")).trim();
				ss[8] = (rs.getString("LastName")).trim();
				if (status.equalsIgnoreCase("Browse")) {
					ss[9] = (rs.getString("Street")).trim();
					ss[10] = (rs.getString("unit")).trim();
					ss[11] = (rs.getString("CellPhone")).trim();
					ss[12] = (rs.getString("Phone3")).trim();
				}

			}

			rs.close();
			ste.close();
			con.close();
			return ss;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return ss;
	}

	public static String[] getSupplier(String status) {
		String[] ss = null;
		try {
			con = DriverManager.getConnection(source);
			Statement ste = con.createStatement();
			ResultSet rs = null;
			String str = null;

			str = "select * from Companies where supplierID=" + status;

			rs = ste.executeQuery(str);
			// 0-id,1=title,2=version,3=location,4=catagory,5-publisher,6=summary,7=createDay,8=status,9=account
			while (rs.next()) {
				ss = new String[17];
				ss[0] = "" + rs.getInt("supplierID");
				ss[1] = rs.getString("companyName");
				ss[2] = rs.getString("Street");
				ss[3] = rs.getString("Unit");
				ss[4] = rs.getString("City");
				ss[5] = rs.getString("State");
				ss[6] = rs.getString("Zip");
				ss[7] = rs.getString("Country");
				ss[8] = rs.getString("Phone");
				ss[9] = rs.getString("fax");
				ss[10] = rs.getString("Email");
				ss[11] = rs.getString("Type");
				ss[12] = rs.getString("Contact");

				ss[13] = rs.getString("Website");
				ss[14] = rs.getString("Description");
				ss[15] = rs.getString("SPNote");
				ss[16] = "" + rs.getByte("Decided");

			}

			rs.close();
			ste.close();
			con.close();
			return ss;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return null;
	}

	public static Vector getStyleNos() {
		Vector data = new Vector();

		if (true)
			return data;
		try {
			con = DriverManager.getConnection(source);

			String str = "select distinct styleNo from Styles";

			Statement ste = con.createStatement();
			ResultSet rs = ste.executeQuery(str);
			while (rs.next()) {
				data.add(rs.getString("StyleNo"));

			}
			rs.close();
			ste.close();
			con.close();
			return data;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return null;
	}

	public static Vector getMenu(String resID, String catagory) {
		Vector data = new Vector();
		String[] ss = null;
		try {
			con = DriverManager.getConnection(source);
			String str = null;
			if (catagory == null || catagory.equalsIgnoreCase("ALL"))
				str = "select * from menu where restaurantID=?";
			else
				str = "select * from menu where (restaurantID=? and catagory=?)";
			PreparedStatement ste = con.prepareStatement(str);

			ResultSet rs = null;
			ste.setString(1, resID);
			if (catagory != null && !catagory.equalsIgnoreCase("ALL"))
				ste.setString(2, catagory);
			rs = ste.executeQuery();
			// 0-first,1=last,2=street,3=apt,4=city,5=state,6=zip,7=phone
			while (rs.next()) {
				ss = new String[6];
				ss[0] = (rs.getString("ItemID")).trim();
				ss[1] = (rs.getString("Name")).trim();
				ss[2] = "" + rs.getDouble("Price");
				ss[3] = (rs.getString("Hot")).trim();
				ss[4] = rs.getString("Catagory").toString();
				ss[5] = (rs.getString("Status")).trim();
				data.add(ss);
			}

			rs.close();
			ste.close();
			con.close();
			return data;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return data;
	}

	public static Vector search(String status, String type) {
		Vector data = new Vector();
		String[] ss = null;
		try {
			con = DriverManager.getConnection(source);
			Statement ste = con.createStatement();
			ResultSet rs = null;
			String str = null;
			if (type.equalsIgnoreCase("Account")) {
				str = "select * from History where accountno='" + status + "'";
				ss = new String[4];
			} else if (type.equalsIgnoreCase("ItemID")) {
				str = "select History.accountno,customers.firstname,customers.lastname,history.dateout,history.datein from History,customers where ((history.itemID='"
						+ status
						+ "') and (history.accountno=customers.accountno))";
				;
				ss = new String[5];

			}
			rs = ste.executeQuery(str);
			// 0-first,1=last,2=street,3=apt,4=city,5=state,6=zip,7=phone
			while (rs.next()) {
				if (type.equalsIgnoreCase("Account")) {
					ss = new String[4];
					ss[0] = (rs.getString(1)).trim();
					// ss[1]=getItemInfo(ss[0],"Title");
					ss[2] = (rs.getDate(2)).toString();
					ss[3] = (rs.getDate(3)).toString();
				} else if (type.equalsIgnoreCase("ItemID")) {
					ss = new String[5];
					ss[0] = (rs.getString(1)).trim();
					ss[1] = (rs.getString(2)).trim();
					ss[2] = (rs.getString(3)).trim();
					ss[3] = (rs.getDate(4)).toString();
					ss[4] = (rs.getDate(5)).toString();
					// System.out.println(ss[3]+"  "+ss[4]);
				}

				data.add(ss);

			}

			rs.close();
			ste.close();
			con.close();
			return data;

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return data;
	}

}
