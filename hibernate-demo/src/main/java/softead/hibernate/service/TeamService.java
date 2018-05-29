package softead.hibernate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import softead.hibernate.dao.PlayerDao;
import softead.hibernate.dao.TeamDao;
import softead.hibernate.models.Player;
import softead.hibernate.models.Team;

@Service
public class TeamService {

	@Autowired
	private TeamDao teamDao;
	@Autowired
	private PlayerDao playerDao;
	
	// 
	@Transactional
	public List<Team> getTeams(){
		List<Team> list = new ArrayList<>();
		teamDao.findAll().forEach(list::add);
		return list;
	}
	
	public Team getTeam(int id) {
		Team team = null;
		Optional<Team> optionalTeam =  teamDao.findById(id);
		if(optionalTeam.isPresent()) {
			team = optionalTeam.get();
		}
		return team;
	}
	
	public void removeTeam(int id) {
		Team team = getTeam(id);
		if(team!=null && team.getPlayers()!=null) {
			for(Player player: team.getPlayers()) {
				player.setTeam(null);
				playerDao.save(player);
			}
		}
		team.setPlayers(new ArrayList<>());
		saveTeam(team);
		System.out.println("Backend Delete : " + team.toString());
		teamDao.deleteById(id);
	}
	
	public void saveTeam(Team team) {
		teamDao.save(team);
		for(Player player : team.getPlayers()) {
			player.setTeam(team);
			playerDao.save(player);
		}
	}

	public List<Player> getTeamPlayers(int id) {
		Team team = null;
		Optional<Team> optionalTeam =  teamDao.findById(id);
		if(optionalTeam.isPresent()) {
			team = optionalTeam.get();
		}
		return team.getPlayers();
	}

}
