package model.datastore.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Quarterback;
import model.IQuarterbackDAO;

/**
 * @author David Ilgen
 * @version 0.0.1
 * 
 */
public class QuarterbackDAO implements IQuarterbackDAO {

	protected String fileName = null;
	protected final List<Quarterback> myList;

	public QuarterbackDAO() {
		Properties props = new Properties();
		FileInputStream fis = null;

		// read the properties file
		try {
			fis = new FileInputStream("res/file/db.properties");
			props.load(fis);
			this.fileName = props.getProperty("DB_FILENAME");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.myList = new ArrayList<>();
		try {
			Files.createFile(Paths.get(fileName));
		} catch (FileAlreadyExistsException fae) {
			;
		} catch (IOException ioe) {
			System.out.println("Create file error with " + ioe.getMessage());
		}
		readList();
	}

	@Override
	public void createRecord(Quarterback quarterback) {
		myList.add(quarterback);
		writeList();
	}

	@Override
	public Quarterback retrieveRecordByRank(int rank) {
		for (Quarterback quarterback : myList) {
			if (quarterback.getRank() == rank) {
				return quarterback;
			}
		}
		return null;
	}

	@Override
	public List<Quarterback> retrieveAllRecords() {
		return myList;
	}

	@Override
	public void updateRecord(Quarterback updatedQuarterback) {
		for (Quarterback quarterback : myList) {
			if (quarterback.getRank() == updatedQuarterback.getRank()) {
				quarterback.setLastName(updatedQuarterback.getLastName());
				quarterback.setFirstName(updatedQuarterback.getFirstName());
				quarterback.setYards(updatedQuarterback.getYards());
				quarterback.setTeam(updatedQuarterback.getTeam());
				quarterback.setOpponent(updatedQuarterback.getOpponent());
				break;
			}
		}
		writeList();
	}

	@Override
	public void deleteRecord(int rank) {
		for (Quarterback quarterback : myList) {
			if (quarterback.getRank() == rank) {
				myList.remove(quarterback);
				break;
			}
		}
		writeList();
	}

	@Override
	public void deleteRecord(Quarterback quarterback) {
		myList.remove(quarterback);
		writeList();
	}

	protected void readList() {
		Path path = Paths.get(fileName);
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				int rank = Integer.parseInt(data[0]);
				String last = data[1];
				String first = data[2];
				int yards = Integer.parseInt(data[3]);
				String team = data[4];
				String opponent = data[5];
				Quarterback quarterback = new Quarterback(rank, last, first, yards, team, opponent);
				myList.add(quarterback);
			}
		} catch (IOException ioe) {
			System.out.println("Read file error with " + ioe.getMessage());
		}
	}

	protected void writeList() {
		Path path = Paths.get(fileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
			for (Quarterback quarterback : myList) {
				writer.write(String.format("%d,%s,%s,%d,%s,%s,%.2f\n", quarterback.getRank(), quarterback.getLastName(), quarterback.getFirstName(), quarterback.getYards(), 
						quarterback.getTeam(), quarterback.getOpponent()));
			}
		} catch (IOException ioe) {
			System.out.println("Write file error with " + ioe.getMessage());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Quarterback quarterback : myList) {
			sb.append(String.format("%5d : %s,%s,%d,%s,%s,%.2f\n", quarterback.getRank(), quarterback.getLastName(), quarterback.getFirstName(), quarterback.getYards(), 
						quarterback.getTeam(), quarterback.getOpponent()));
		}

		return sb.toString();
	}

	@Override
	public Quarterback retrieveRecordByName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quarterback retrieveRecordByTeam(String team) {
		// TODO Auto-generated method stub
		return null;
	}
}
