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

public class PlayerRelationshipHandler implements IAllureIO{
	
	private List<RelationshipPlayer> playerRelationships;
	
	public PlayerRelationshipHandler(AllureIO allureIO) {
		allureIO.registerConfigObject(this);
		playerRelationships = new ArrayList<RelationshipPlayer>();
	}
	
	public class RelationshipPlayer {
		UUID playerID;
		Map<UUID, RelationshipStatus> acquaintances;
		public RelationshipPlayer() {
			acquaintances = new HashMap<UUID, RelationshipStatus>();
		}
		public RelationshipPlayer(Player player) {
			playerID = player.getUniqueId();
			acquaintances = new HashMap<UUID, RelationshipStatus>();
		}
	}
	
	public RelationshipStatus getRelationship (RelationshipPlayer rPlayer, Player secondParty) {
		UUID secondPartyUUID = secondParty.getUniqueId();
		if(rPlayer.acquaintances.containsKey(secondPartyUUID)) {
			return rPlayer.acquaintances.get(secondPartyUUID);
		}
		return RelationshipStatus.NONE;
	}
	
	public boolean setRelationship (RelationshipPlayer rPlayer, Player secondParty, RelationshipStatus status, boolean doNotSetLower) {
		if( (getRelationship(rPlayer, secondParty).ordinal() > status.ordinal() && doNotSetLower) || !doNotSetLower) {
			rPlayer.acquaintances.put(secondParty.getUniqueId(), status);
			return true;
		}
		return false;
	}
	
	public RelationshipPlayer getRelationshipPlayer(Player player, boolean autoCreate) {
		//Get Player UUID and store to a variable (in-case Minecraft does an expensive operation whenever .getUniqueID() is called.)
		UUID playerUUID = player.getUniqueId();
		
		//Loop through playerRelationships to see if the player has an existing RelationshipPlayer.
		for(RelationshipPlayer rPlayer: playerRelationships) {
			//If the player is found in playerRelationships, return the RelationshipPlayer object.
			if(rPlayer.playerID == playerUUID) {
				return rPlayer;
			}
		}
		
		//If autoCreate is set to true, automatically create new RelationshipPlayer and attach it to playerRelationships on the
		//occasion that a previous RelationshipPlayer has not been found.
		if(autoCreate) {
			RelationshipPlayer rPlayer = new RelationshipPlayer(player);
			playerRelationships.add(rPlayer);
			return rPlayer;
		}
		else {
			return null;
		}
		
	}
	
	//Returns a list of affected players.
	public List<Player> announceRelationship (Player source, int radius, RelationshipStatus status) {
		List<Player> affectedPlayers = new ArrayList<Player>();
		for (Player recipient : source.getWorld().getPlayers()) {
			if(recipient.getLocation().distance(source.getLocation()) <= radius) {
				setRelationship(getRelationshipPlayer(recipient, true), source, status, true);
				affectedPlayers.add(recipient);
			}
		}
		return affectedPlayers;
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
