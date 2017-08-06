package com.lyesoussaiden.allure.relationship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerRelationships {
	public class RelationshipPlayer {
		UUID playerID;
		Map<UUID, RelationshipStatus> acquaintances = new HashMap<UUID, RelationshipStatus>();
	}
	public List<RelationshipPlayer> playerRelationships = new ArrayList<RelationshipPlayer>();
	
	public RelationshipStatus getRelationship (UUID firstParty, UUID secondParty) {
		RelationshipStatus status = RelationshipStatus.NONE;
		for(RelationshipPlayer player : playerRelationships) {
			if(player.playerID == firstParty) {
				status = player.acquaintances.get(secondParty);
				break;
			}
		}
		return status;
	}
}
