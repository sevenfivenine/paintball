import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;


public class TeamName extends PaintBallCritter {
	
	//Test

	private final Color firstColor;

	public TeamName(Color c) {
		super(c);
		firstColor = c;
		System.out.println("First Color: " + firstColor);
	}

	@Override
	public void makeMove(Location loc) {
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
