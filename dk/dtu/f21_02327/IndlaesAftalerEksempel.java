package dk.dtu.f21_02327;


import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.List;

public class IndlaesAftalerEksempel {

	public static void main(String[] args) {
		IndlaesVaccinationsAftaler laeser = new IndlaesVaccinationsAftaler();
		try {

			Connector connector = new Connector();
			Statement stmt = connector.connection.createStatement();
			String strSelect = "SELECT * FROM examproject.referral;";
			System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging

			int ip = 0;
			int rowCount = 0;
			ResultSet rset = stmt.executeQuery(strSelect);
			while(rset.next()) {   // Repeatedly process each row
				++rowCount;
			}
			System.out.println("Total number of records = " + rowCount + " before");

			List<VaccinationsAftale> aftaler = laeser.indlaesAftaler("dk/dtu/f21_02327/vaccinationsaftaler.csv");
			for(VaccinationsAftale aftale : aftaler) {
				try {
					ip++;
					/*java.util.Date utilDate = new java.util.Date();
					System.out.println("java.util.Date : " + utilDate);

					java.sql.Date sqlDate = new java.sql.Date(aftale.getAftaltTidspunkt().getTime());
					System.out.println("java.sql.Date  : " + sqlDate);

					java.sql.Time sqlTime = new java.sql.Time(aftale.getAftaltTidspunkt().getTime());
					System.out.println("java.sql.sqlTime  : " + sqlTime);

					java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(aftale.getAftaltTidspunkt().getTime());
					System.out.println("java.sql.sqlTime  : " + sqlTimestamp);*/

					String strUpdate = "INSERT INTO examproject.referral "+"VALUES(" + aftale.getCprnr() + ", '" + aftale.getNavn() + "', " + /*aftale.getAftaltTidspunkt().getTime()*/ip + ", '"+ /*sqlTimestamp.toString() +", " + /*aftale.getAftaltTidspunkt().getHours()sqlTime + ", " + */ aftale.getVaccineType() + "', '" + aftale.getLokation() + "')";
					System.out.println("The SQL statement is: " + strUpdate + "\n"); // Echo For debugging
					stmt.executeUpdate(strUpdate);
				} catch (SQLIntegrityConstraintViolationException e){
					System.out.println("This person already exists");
				}
			}
			rowCount = 0;
			rset = stmt.executeQuery(strSelect);
			while(rset.next()) {   // Repeatedly process each row
				long CPR = rset.getLong("CPR");
				String ClientName = rset.getString("ClientName");
				long RefDate = rset.getLong("RefDate"); // retrieve a 'double'-cell in the row
				//int RefTime = rset.getInt("RefTime");
				String VacType = rset.getString("VacType");
				String Address = rset.getString("Address");
				System.out.println(CPR + ", " + ClientName + ", " + RefDate + ", " + /*RefTime +*/ ", " + VacType + ", " + Address);
				++rowCount;
			}
			System.out.println("Total number of records = " + rowCount + " after");

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}