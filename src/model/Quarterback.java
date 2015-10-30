package model;

/**
 * The Quarterback class represents a single quarterback entry which includes the quarterback last name, first name, rank of game yards all time, game yards, 
 * quarterback team, and opponent team.
 * 
 * @author David Ilgen
 * @version 0.0.1
 *
 */
public class Quarterback {

	private int rank;
	private String lastName;
	private String firstName;
	private int yards;
	private String team;
	private String opponent;

	public Quarterback() {
		rank = 0;
		lastName = "";
		firstName = "";
		yards = 0;
		team = "";
		opponent = "";
	}

	public Quarterback(int rank, String lastName, String firstName, int yards, String team, String opponent) {
		this.rank = rank;
		this.lastName = lastName;
		this.firstName = firstName;
		this.yards = yards;
		this.team = team;
		this.opponent = opponent;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getYards() {
		return yards;
	}

	public void setYards(int yards) {
		this.yards = yards;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
	
	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	@Override
	public String toString() {
		return String.format("%5d : %s, %s, %4d, %s, %s", this.getRank(), this.getLastName(),
				this.getFirstName(), this.getYards(), this.getTeam(), this.getOpponent());
	}
}