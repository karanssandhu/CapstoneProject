package ca.sheridancollege.sandh408.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.sandh408.beans.Capstone;
import ca.sheridancollege.sandh408.database.DatabaseAccess;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/capstones")
public class EventController {

	private DatabaseAccess da;

	// mapping to retrieve the list of the capstones from the database
	@GetMapping
	public List<Capstone> getEventCollection() {
		return da.findAll();
	}

	// mapping to retrieve the capstone by passing an id
	@GetMapping(value = "/{id}")
	public Capstone getIndividualEvent(@PathVariable Long id) {
		return da.findById(id);
	}

	// mapping to increase the team rank by 1
	@GetMapping(value = "/up/{id}")
	public String upById(@PathVariable Long id) {
		Capstone capstone = da.findById(id);
		capstone.up();
		return "http://localhost:8080/capstones" + da.update(capstone, capstone.getId());
	}

	// mapping to decrease the team rank by 1
	// Well tbh, all teams did well, we shouldn't be down voting someone :(
	// but it's just an extra functionality
	@GetMapping(value = "/down/{id}")
	public String downById(@PathVariable Long id) {
		Capstone capstone = da.findById(id);
		capstone.down();
		return "http://localhost:8080/capstones" + da.update(capstone, capstone.getId());
	}

	// mapping to insert a capstone
	@PostMapping(consumes = "application/json")
	public String postEvent(@RequestBody Capstone capstone) {
		return "http://localhost:8080/capstones" + da.save(capstone);
	}

}
