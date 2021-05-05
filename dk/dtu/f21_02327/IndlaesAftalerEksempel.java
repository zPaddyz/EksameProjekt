package dk.dtu.f21_02327;


import java.io.IOException;
import java.sql.*;
import java.util.List;

public class IndlaesAftalerEksempel {

	public static void main(String[] args) {
		IndlaesVaccinationsAftaler laeser = new IndlaesVaccinationsAftaler();
		try {

			Connector connector = new Connector();
			Statement stmt = connector.connection.createStatement();
			int ip = 0;

			// hurtig select af database til at tælle hvor mange personer der er i clientid
			/*String strSelect = "SELECT * FROM examproject.clientId;";
			System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
			int rowCount = 0;
			ResultSet rset = stmt.executeQuery(strSelect);
			while(rset.next()) {   // Repeatedly process each row
				++rowCount;
			}
			System.out.println("Total number of records = " + rowCount + " before");*/

			List<VaccinationsAftale> aftaler = laeser.indlaesAftaler("dk/dtu/f21_02327/vaccinationsaftaler.csv");
			for(VaccinationsAftale aftale : aftaler) {
				// resetter tæller her fordi at nuværende database kun har 17 medarbejdere.
				if(ip==17) ip= 0;
				ip++;

				java.sql.Date sqlDate = new java.sql.Date(aftale.getAftaltTidspunkt().getTime());
				System.out.println("java.sql.Date  : " + sqlDate);

				java.sql.Time sqlTime = new java.sql.Time(aftale.getAftaltTidspunkt().getTime());
				System.out.println("java.sql.sqlTime  : " + sqlTime);

				try {
					String strUpdate = "INSERT INTO examproject.ClientId " + "VALUES(" + aftale.getCprnr() + ", '" + aftale.getNavn() + "')";
					System.out.println("The SQL statement is: " + strUpdate); // Echo For debugging
					stmt.executeUpdate(strUpdate); }
				catch (SQLIntegrityConstraintViolationException error){
					System.out.println("This is a duplicate \n");
					}
				try {
					String strUpdate2 = "INSERT INTO examproject.Injection " + "VALUES('" + aftale.getVaccineType() + "', " + aftale.getCprnr() + ", " + ip + ", '" + sqlDate + "', '" + sqlTime + "')";
					System.out.println("The SQL statement is: " + strUpdate2); // Echo For debugging
					stmt.executeUpdate(strUpdate2);
				} catch(SQLIntegrityConstraintViolationException e){
					System.out.println("This is a duplicate \n");
				} try{
					String strUpdate3 = "INSERT INTO examproject.Location " + "VALUE('" + aftale.getLokation() + "', " + null + ")";
					System.out.println("The SQL statement is: " + strUpdate3); // Echo For debugging
					stmt.executeUpdate(strUpdate3);
				} catch(SQLIntegrityConstraintViolationException e){
					System.out.println("This is a duplicate \n");
				}


			}
			// Select statement der kan bruges til at hurtigt vise funktionalitet af programmet
			/*rowCount = 0;
			rset = stmt.executeQuery(strSelect);
			while(rset.next()) {   // Repeatedly process each row
				long CPR = rset.getLong("CPR");
				String ClientName = rset.getString("ClientName");
				System.out.println(CPR + ", " + ClientName );
				++rowCount;
			}
			System.out.println("Total number of records = " + rowCount + " after");*/

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

}