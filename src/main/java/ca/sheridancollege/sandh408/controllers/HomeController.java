package ca.sheridancollege.sandh408.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.sandh408.beans.Capstone;

@Controller
public class HomeController {

	// Get mapping for the root page
	@GetMapping("/")
	public String index(Model model, RestTemplate restTemplate) {
		ResponseEntity<Capstone[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/capstones",
				Capstone[].class);
		model.addAttribute("capstone", new Capstone());
		model.addAttribute("capstoneList", responseEntity.getBody());
		return "index";
	}

	// post mapping to insert an capstone into the database
	// Also I'm not adding rank in the form, because according to my pov, ranks come
	// after that project or something has been uploaded to a platform like how it
	// works on youtube
	@PostMapping(value = "/insertCapstone")
	public String addCapstone(Model model, RestTemplate restTemplate, @ModelAttribute Capstone capstone) {
		restTemplate.postForEntity("http://localhost:8080/capstones/", capstone, String.class);
		model.addAttribute("capstone", new Capstone());
		ResponseEntity<Capstone[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/capstones",
				Capstone[].class);
		model.addAttribute("capstoneList", responseEntity.getBody());
		return "index";
	}

	// get mapping to retrieve capstone by id
	@GetMapping(value = "/getCapstone/{id}", produces = "application/json")
	@ResponseBody
	public Capstone getCapstone(@PathVariable int id, RestTemplate restTemplate) {
		ResponseEntity<Capstone> responseEntity = restTemplate.getForEntity("http://localhost:8080/capstones/" + id,
				Capstone.class);

		return responseEntity.getBody();
	}

	// get mapping to the restful web service to increase the rank of a capstone
	// from the database
	@GetMapping("/upVoteById/{id}")
	public String upVoteById(Model model, RestTemplate restTemplate, @PathVariable int id) {
		restTemplate.getForEntity("http://localhost:8080/capstones/up/" + id, String.class);
		model.addAttribute("capstone", new Capstone());
		ResponseEntity<Capstone[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/capstones",
				Capstone[].class);
		model.addAttribute("capstoneList", responseEntity.getBody());
		return "index";
	}

	// get mapping to the restful web service to decrease the rank of a capstone
	// from the database
	@GetMapping("/downVoteById/{id}")
	public String downVoteById(Model model, RestTemplate restTemplate, @PathVariable int id) {
		restTemplate.getForEntity("http://localhost:8080/capstones/down/" + id, String.class);
		model.addAttribute("capstone", new Capstone());
		ResponseEntity<Capstone[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/capstones",
				Capstone[].class);
		model.addAttribute("capstoneList", responseEntity.getBody());
		return "index";
	}

}
