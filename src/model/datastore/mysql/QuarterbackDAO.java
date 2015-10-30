package model.datastore.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Quarterback;
import model.IQuarterbackDAO;

/**
 * @author David Ilgen
 * @version 0.0.1
 *
 */
public class QuarterbackDAO implements IQuarterbackDAO {
	
	protected final static boolean DEBUG = true;

/**
 * This method creates an entry for a new quarterback record using SQL.
 * 
 * @param quarterback a Quarterback object. The object quarterback is created in Quarterback.java when a new record is added.
 * @throws SQLException ex
 */
	@Override
	public void createRecord(Quarterback quarterback) {
		final String QUERY = "insert into quarterback (rank, lastName, firstName, yards, team, opponent) VALUES (null, ?, ?, ?, ?, ?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY);) {
			stmt.setString(1, quarterback.getLastName());
			stmt.setString(2, quarterback.getFirstName());
			stmt.setInt(3, quarterback.getYards());
			stmt.setString(4, quarterback.getTeam());
			stmt.setString(5, quarterback.getOpponent());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("createRecord SQLException: " + ex.getMessage());
		}
	}

	/**
	 * This method retrieves aa single entry in the Quarterback Database. It prints it by using a toString() method.
	 * 
	 * @return returns a Quarterback object entry.
	 * @throws SQLException ex
	 */
	
	@Override
	public Quarterback retrieveRecordByRank(int rank) {
		final String QUERY = "select rank, lastName, firstName, yards, team, opponent from quarterback where rank = " + rank;
		// final String QUERY = "select rank, lastName, firstName, yards,
		// team, opponent from quarterback where rank = ?";
		Quarterback qb = null;

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			// stmt.setInt(1, rank);
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);

			if (rs.next()) {
				qb = new Quarterback(rs.getInt("rank"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getInt("yards"), rs.getString("team"), rs.getString("opponent"));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveRecordByRank SQLException: " + ex.getMessage());
		}

		return qb;
	}
	
	/**
	 * This method retrieves all entries for a specific quarterback, by lastName. It prints all of that quarterback's records in ascending order by rank.
	 * 
	 * @return returns a list of lastName objects.
	 * @throws SQLException ex
	 */
	
	@Override
	public Quarterback retrieveRecordByName(String lastName) {
		final String QUERY = "select rank, lastName, firstName, yards, team, opponent from quarterback where lastName = " + lastName;
		// final String QUERY = "select rank, lastName, firstName, yards,
		// team, opponent from quarterback where lastName = ?";
		Quarterback qb = null;

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			// stmt.setInt(1, rank);
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);

			if (rs.next()) {
				qb = new Quarterback(rs.getInt("rank"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getInt("yards"), rs.getString("team"), rs.getString("opponent"));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveRecordByName SQLException: " + ex.getMessage());
		}

		return qb;
	}
	
	/**
	 * This method retrieves all entries for a specific team, by team. It prints all of that team's records in ascending order by rank.
	 * 
	 * @return returns a list of team objects.
	 * @throws SQLException ex
	 */
	
	@Override
	public Quarterback retrieveRecordByTeam(String team) {
		final String QUERY = "select rank, lastName, firstName, yards, team, opponent from quarterback where team = " + team;
		// final String QUERY = "select rank, lastName, firstName, yards,
		// team, opponent from quarterback where team = ?";
		Quarterback qb = null;

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			// stmt.setInt(1, rank);
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);

			if (rs.next()) {
				qb = new Quarterback(rs.getInt("rank"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getInt("yards"), rs.getString("team"), rs.getString("opponent"));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveRecordByTeam SQLException: " + ex.getMessage());
		}

		return qb;
	}
	
	/**
	 * This method returns a list of all entries in the Quarterback Database. It lists them in descending order by rank by using a toString() method.
	 * 
	 * @return returns all entries in the database.
	 * @throws SQLException ex
	 */
	
	@Override
	public List<Quarterback> retrieveAllRecords() {
		final List<Quarterback> myList = new ArrayList<>();
		final String QUERY = "select rank, lastName, firstName, yards, team, opponent from quarterback";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);
			
			while (rs.next()) {
				myList.add(new Quarterback(rs.getInt("rank"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getInt("yards"), rs.getString("team"), rs.getString("opponent")));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveAllRecords SQLException: " + ex.getMessage());
		}

		return myList;
	}
	
	/**
	 * This method updates an existing entry for quarterback in the database, using SQL.
	 * 
	 * @param updatedQuarterback a Quarterback object. The object quarterback is created in Quarterback.java when an existing record is updated.
	 * @throws SQLException ex
	 */

	@Override
	public void updateRecord(Quarterback updatedQuarterback) {
		final String QUERY = "update quarterback set lastName=?, firstName=?, yards=?, team=?, opponent=? where rank=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setString(1, updatedQuarterback.getLastName());
			stmt.setString(2, updatedQuarterback.getFirstName());
			stmt.setInt(3, updatedQuarterback.getYards());
			stmt.setString(4, updatedQuarterback.getTeam());
			stmt.setString(5, updatedQuarterback.getOpponent());
			stmt.setInt(6, updatedQuarterback.getRank());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("updateRecord SQLException: " + ex.getMessage());
		}
	}

	/**
	 * This method deletes a record by rank, using SQL. This will delete a specific entry based on the rank entered.
	 * 
	 * @param rank an int object. The int is passed from QuarterbackApp.java when a record is deleted.
	 * @throws SQLException ex
	 */
	
	@Override
	public void deleteRecord(int rank) {
		final String QUERY = "delete from quarterback where rank = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, rank);
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}
	
/**
* This method deletes a record by quarterback, using SQL. This will delete a specific entry based on the quarterback entered.
* 
* @param quarterback a Quarterback object. The quarterback is passed from QuarterbackApp.java when a record is deleted.
* @throws SQLException ex
*/

	@Override
	public void deleteRecord(Quarterback quarterback) {
		final String QUERY = "delete from quarterback where rank = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, quarterback.getRank());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Quarterback quarterback : retrieveAllRecords()) {
			sb.append(quarterback.toString() + "\n");
		}

		return sb.toString();
	}
}
