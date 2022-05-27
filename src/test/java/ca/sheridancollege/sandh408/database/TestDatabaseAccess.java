package ca.sheridancollege.sandh408.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import ca.sheridancollege.sandh408.beans.Capstone;

@SpringBootTest
@AutoConfigureTestDatabase
public class TestDatabaseAccess {

	@Autowired
	private DatabaseAccess da;

	// test that checks the findAll and the save method of databaseAccess
	@Test
	public void checkFindAll_saveCapstone() {
		Capstone capstone = new Capstone();
		capstone.setTitle("wow");
		capstone.setLink("something.com");
		capstone.setTeamName("Testing");
		capstone.setRank(100000000); // manually set it to highest so that it will be on the top of the list
		Long a = da.save(capstone);
		capstone.setId(a);
		assertEquals(da.findAll().get(0), capstone);
	}

}
