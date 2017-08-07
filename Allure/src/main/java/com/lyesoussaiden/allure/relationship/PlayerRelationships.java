package com.lyesoussaiden.allure.relationship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.lyesoussaiden.allure.utils.AllureIO;
import com.lyesoussaiden.allure.utils.IAllureIO;

public class PlayerRelationships implements IAllureIO{
	
	public PlayerRelationships(AllureIO allureIO) {
		allureIO.registerConfigObject(this);
	}
	
	public class RelationshipPlayer {
		UUID playerID;
		Map<UUID, RelationshipStatus> acquaintances;
		public RelationshipPlayer() {
			acquaintances = new HashMap<UUID, RelationshipStatus>();
		}
		
	}
	public List<RelationshipPlayer> playerRelationships = new ArrayList<RelationshipPlayer>();
	
	public RelationshipStatus getRelationship (Player firstParty, Player secondParty) {
		RelationshipStatus status = null;
		for(RelationshipPlayer player : playerRelationships) {
			if(player.playerID == firstParty.getUniqueId()) {
				status = player.acquaintances.get(secondParty.getUniqueId());
				break;
			}
		}
		return status == null ? RelationshipStatus.NONE : status;
	}
	
	public void setRelationship (Player firstParty, Player secondParty, RelationshipStatus status) {
		//Loops through playerRelationships, checking to update any pre-existing playerRelationship(s) within the list.
		boolean hasSetRelationship = false;
		for(RelationshipPlayer player : playerRelationships) {
			if(player.playerID == firstParty.getUniqueId()) {
				player.acquaintances.put(secondParty.getUniqueId(), status);
				hasSetRelationship = true;
				break;
			}
		}
		
		//If a playerRelationship has not been found, create a new one and add it to the list.
		if(!hasSetRelationship) {
			RelationshipPlayer playerRelationship = new RelationshipPlayer();
			playerRelationship.playerID = firstParty.getUniqueId();
			playerRelationship.acquaintances.put(secondParty.getUniqueId(), status);
			playerRelationships.add(playerRelationship);
		}
	}

	@Override
	public void saveData(FileConfiguration config) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadData(FileConfiguration config) {
		// TODO Auto-generated method stub
		
	}
}
