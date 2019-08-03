package hanabi.driver;

import java.util.ArrayList;

import hanabi.game.AbstractPlayer;

public class Move {
	
	private MoveType moveType;
	private AbstractPlayer targetPlayer;
	private ArrayList<Integer> targetHandIndices;
	
	public Move(MoveType moveType, AbstractPlayer targetPlayer, ArrayList<Integer> targetHandIndices) {
		super();
		this.moveType = moveType;
		this.targetPlayer = targetPlayer;
		this.targetHandIndices = targetHandIndices;
	}

	public MoveType getMoveType() {
		return moveType;
	}

	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}

	public AbstractPlayer getTargetPlayer() {
		return targetPlayer;
	}

	public void setTargetPlayer(AbstractPlayer targetPlayer) {
		this.targetPlayer = targetPlayer;
	}

	public ArrayList<Integer> getTargetHandIndices() {
		return targetHandIndices;
	}

	public void setTargetHandIndices(ArrayList<Integer> targetHandIndices) {
		this.targetHandIndices = targetHandIndices;
	}
	
}
