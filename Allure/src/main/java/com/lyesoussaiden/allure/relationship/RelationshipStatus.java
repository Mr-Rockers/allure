package com.lyesoussaiden.allure.relationship;

public enum RelationshipStatus  {
UNAVAILABLE(0),
ACQUAINTANCE(1),
TRUSTED(2);

private final int val;

RelationshipStatus(int val) {
	this.val = val;
}

public int toInt() {
	return this.val;
}

static RelationshipStatus fromInt(int val) {
	switch(val){
	case 0:
		return RelationshipStatus.UNAVAILABLE;
	case 1:
		return RelationshipStatus.ACQUAINTANCE;
	case 2:
		return RelationshipStatus.TRUSTED;
	default:
		return RelationshipStatus.UNAVAILABLE;
	}
}

}
