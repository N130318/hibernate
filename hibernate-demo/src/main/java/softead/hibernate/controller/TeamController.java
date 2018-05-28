package softead.hibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/teams")
@CrossOrigin(value = "http://localhost:4200")
public class TeamController {
	@Autowired
	TeamService teamService;
	
	// Get All Playres
	@RequestMapping("")
	public List<Team> getTeams(){
		return teamService.getTeams();
	}
	
	//GET, Single Player
	@RequestMapping("/{id}")
	@Transactional
	public Team getTeam(@PathVariable("id") int id) {
		System.out.println(teamService.getTeam(id).getDescription());
		return teamService.getTeam(id);
	}
	
	// POST, Save Team
	@RequestMapping(value="/add", method = RequestMethod.POST)
	@Transactional
	public void saveTeam(@RequestBody Team team) {
		System.out.println("Saving team ::::::: " + team.getTeam());
		teamService.saveTeam(team);
	}
	
	//DELETE
	@RequestMapping(value ="/delete/{id}", method=RequestMethod.DELETE)
	public void deleteTeam(@PathVariable("id") int id) {
		teamService.removeTeam(id);
	}
	
	//Get Players
	@RequestMapping(value ="/{id}/players")
	public List<Player> getTeamPlayers(@PathVariable("id") int id) {
		return teamService.getTeamPlayers(id);
	}

}
