package softead.hibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import softead.hibernate.models.Player;
import softead.hibernate.models.Team;
import softead.hibernate.service.PlayerService;
import softead.hibernate.service.TeamService;

@RestController
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	PlayerService playerService;
	@Autowired
	TeamService teamService;
	
	// Get All Playres
	@RequestMapping("")
	public List<Player> getAllPlayers(){
		return playerService.getPlayers();
	}
	
	//GET, Single Player
	@RequestMapping("/{id}")
	public Player getPlayer(@PathVariable("id") int id) {
		return playerService.getPlayer(id);
	}
	
	// POST, Save User
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	public void savePlayer(@RequestBody Player player) {
		playerService.savePlayer(player);
	}
	
	//DELETE
	@RequestMapping(value ="/delete/{id}", method=RequestMethod.DELETE)
	public void deletePlayer(@PathVariable("id") int id) {
		playerService.removePlayer(id);
	}
	
	//Get Player By Team
		@RequestMapping(value ="/team/{id}", method=RequestMethod.GET)
		public List<Player> getPlayerByTeam(@PathVariable() int id) {
			Team team = teamService.getTeam(id);
			System.out.println("Plyer by Teams : " + team);
			return playerService.getPlayersByTeam(team);
		}
		
	
}
