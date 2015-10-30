package model;

import java.util.List;

/**
 * @author David Ilgen
 * @version 0.0.1
 *
 */
public interface IQuarterbackDAO {

	void createRecord(Quarterback quarterback);

	Quarterback retrieveRecordByRank(int rank);

	List<Quarterback> retrieveAllRecords();

	void updateRecord(Quarterback updatedQuarterback);

	void deleteRecord(int rank);

	void deleteRecord(Quarterback quarterback);

	String toString();

	Quarterback retrieveRecordByName(String lastName);

	Quarterback retrieveRecordByTeam(String team);

}