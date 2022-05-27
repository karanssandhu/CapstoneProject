package ca.sheridancollege.sandh408.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.sandh408.beans.Capstone;

@Repository
public class DatabaseAccess {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	// method that retrieves the list of the capstones from the database
	// also it orders them by their rank in descending order
	public List<Capstone> findAll() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM capstone ORDER BY rank desc";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Capstone>(Capstone.class));
	}

	// method that retrieves an capstone
	public Capstone findById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM capstone WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Capstone>(Capstone.class)).get(0);
	}

	// method that saves an capstone in the database
	public Long save(Capstone capstone) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		String query = "INSERT INTO capstone(title, link, teamName, rank) VALUES (:title, :link, :teamName, :rank)";
		namedParameters.addValue("title", capstone.getTitle());
		namedParameters.addValue("link", capstone.getLink());
		namedParameters.addValue("teamName", capstone.getTeamName());
		namedParameters.addValue("rank", capstone.getRank());
		jdbc.update(query, namedParameters, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKey();
	}

	// method that updates a capstone in the database
	public Long update(Capstone capstone, Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		String query = "UPDATE capstone set (title, link, teamName, rank) = (:title, :link, :teamName, :rank) where id=:id";
		namedParameters.addValue("id", id);
		namedParameters.addValue("title", capstone.getTitle());
		namedParameters.addValue("link", capstone.getLink());
		namedParameters.addValue("teamName", capstone.getTeamName());
		namedParameters.addValue("rank", capstone.getRank());
		jdbc.update(query, namedParameters, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKey();
	}

}
