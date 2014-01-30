import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;


public class TeamName extends PaintBallCritter {
	
	//Check to see if a mine can blow you up before you move
	
	private final Color firstColor;

	public TeamName(Color c) {
		super(c);
		firstColor = c;
		System.out.println("First Color: " + firstColor);
	}

	@Override
	public void makeMove(Location loc) {
		dropMine();
		Location startingLoc = getLocation();

		if (loc == getLocation())
			moveTo(selectMoveLocation(getEmptyLocations(getGrid())));
		else
			moveTo(loc);

		if(!startingLoc.equals(getLocation())) {
			TeflonRock rock = new TeflonRock(firstColor);
			rock.putSelfInGrid(getGrid(), startingLoc);
		}
		
	}

	private void dropMine() {
		ArrayList<Actor> actors = getActors();
		
		for(Actor a : actors) {
			if(!(a instanceof TeflonRock && a.getColor().equals(firstColor))) {
				Mine mine = new Mine();
				mine.putSelfInGrid(a.getGrid(), a.getLocation());
			}
		}
	}

	@Override
	public void setColor(Color c) {
		if(firstColor == null)
			super.setColor(c);
	}
	
	public ArrayList<Location> getEmptyLocations(Grid g) {
		ArrayList<Location> locs = new ArrayList<Location>();

		for(int row = 0; row < getGrid().getNumRows(); row++) {
			for(int column = 0; column < getGrid().getNumCols(); column++) {
				Location theLoc = new Location(row, column);

				if(getGrid().isValid(theLoc) && getGrid().get(theLoc) == null) {
					locs.add(theLoc);
				}
			}
		}

		return locs;
	}

}
