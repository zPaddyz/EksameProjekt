package dk.dtu.f21_02327;


import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.List;

public class IndlaesAftalerEksempel {

	public static void main(String[] args) {
		IndlaesVaccinationsAftaler laeser = new IndlaesVaccinationsAftaler();
		try {
			Date dagensDato = null;
			int divocAntal = 0;
			int asperaAntal = 0;
			int covaxxAntal = 0;
			int blast3000Antal = 0;

			List<VaccinationsAftale> aftaler = laeser.indlaesAftaler("dk/dtu/f21_02327/vaccinationsaftaler.csv");
			for(VaccinationsAftale aftale : aftaler) {
				//System.out.println(aftale+ "\n");
				//if(dagensDato.compareTo(aftale.getAftaltTidspunkt()) < 0)
				System.out.println(aftale.getAftaltTidspunkt());
				switch(aftale.getVaccineType()){
					case "blast3000":
						blast3000Antal++;
						break;
					case "aspera":
						asperaAntal++;
						break;
					case "covaxx":
						covaxxAntal++;
						break;
					case "divoc":
						divocAntal++;
						break;
					default:
						break;
				}
			}
			System.out.println("divocAntal : " + divocAntal);
			System.out.println("asperaAntal : " + asperaAntal);
			System.out.println("covaxxAntal : " + covaxxAntal);
			System.out.println("blast3000Antal : " + blast3000Antal);
			/*Connector connector = new Connector();
			Statement stmt = connector.connection.createStatement();
			String strSelect = "SELECT * FROM examproject.vaccine;";
			System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging

			int rowCount = 0;
			ResultSet rset = stmt.executeQuery(strSelect);
			while(rset.next()) {   // Repeatedly process each row
				String title = rset.getString("VacType");  // retrieve a 'String'-cell in the row
				String price = rset.getString("Price");  // retrieve a 'double'-cell in the row
				System.out.println(title + ", " + price);
				++rowCount;
			}
			System.out.println("Total number of records = " + rowCount);*/
		} catch (IOException e/*| SQLException e*/) {
			e.printStackTrace();
		} finally {

		}
	}
}