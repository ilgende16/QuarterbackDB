package viewui;

import java.util.Scanner;

import model.Quarterback;
import model.IQuarterbackDAO;
import model.datastore.mysql.QuarterbackDAO;

/**
 * @author David Ilgen
 * @version 0.0.1
 * 
 */
public class QuarterbackApp {

	IQuarterbackDAO qbList = new QuarterbackDAO();
	Scanner sc = new Scanner(System.in);

	public QuarterbackApp() {
		menuLoop();
	}

	private void menuLoop() {
		int rank, yards;
		String lastName = null, firstName, team = null, opponent;
		String choice = "1";
		while (!choice.equals("0")) {
			System.out.println("\nQuarterback App");
			System.out.println("0 = Quit");
			System.out.println("1 = List All Records");
			System.out.println("2 = Create New Record");
			System.out.println("3 = Retrieve Record by Rank");
			System.out.println("4 = Retrieve Record by Last Name");
			System.out.println("5 = Retrieve Record by Team");
			System.out.println("6 = Update Record");
			System.out.println("7 = Delete Record");
			choice = Validator.getLine(sc, "Number of choice: ", "^[0-7]$");

			switch (choice) {
			case "1":
				System.out.println(qbList.toString());
				break;
			case "2":
				rank = Validator.getInt(sc, "New rank: ");
				lastName = Validator.getLine(sc, "Last name: ");
				firstName = Validator.getLine(sc, "First name: ");
				yards = Validator.getInt(sc, "Yards: ");
				team = Validator.getLine(sc, "Team: ");
				opponent = Validator.getLine(sc, "Opponent: ");
				qbList.createRecord(new Quarterback(rank, lastName, firstName, yards, team, opponent));
				break;
			case "3":
				rank = Validator.getInt(sc, "Rank to retrieve: ");
				System.out.println(qbList.retrieveRecordByRank(rank));
				break;
			case "4":
				lastName = Validator.getLine(sc, "Last name to retrieve: ");
				System.out.println(qbList.retrieveRecordByName(lastName));
				break;
			case "5":
				lastName = Validator.getLine(sc, "Team to retrieve: ");
				System.out.println(qbList.retrieveRecordByTeam(team));
				break;
			case "6":
				rank = Validator.getInt(sc, "Rank to update: ");
				lastName = Validator.getLine(sc, "Last name: ");
				firstName = Validator.getLine(sc, "First name: ");
				yards = Validator.getInt(sc, "Yards: ");
				team = Validator.getLine(sc, "Team: ");
				opponent = Validator.getLine(sc, "Opponent: ");
				qbList.updateRecord(new Quarterback(rank, lastName, firstName, yards, team, opponent));
				break;
			case "7":
				rank = Validator.getInt(sc, "Rank to delete: ");
				System.out.println(qbList.retrieveRecordByRank(rank));
				String ok = Validator.getLine(sc, "Delete this record? (y/n) ", "^[yYnN]$");
				if (ok.equalsIgnoreCase("Y")) {
					qbList.deleteRecord(rank);
				}
				break;
			}
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		new QuarterbackApp();
	}
}
