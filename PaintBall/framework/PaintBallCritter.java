
import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.util.ArrayList;
import java.awt.Color;

public class PaintBallCritter extends Actor
{

	public PaintBallCritter(Color c)
	{
		setColor(c);
	}
	/**
	 * A PaintBallCritter acts by getting a list of other actors, processing that list, getting locations to move to,
	 * selecting one of them, and moving to the selected location. This method is final and can not be overriden
	 */
	final public void act()
	{
		if (getGrid() == null)
			return;
		ArrayList<Actor> actors = getActors();
		processActors(actors);
		ArrayList<Location> moveLocs = getMoveLocations();
		Location loc = selectMoveLocation(moveLocs);
		makeMove(loc);
	}
	/**
	 * Gets the actors for processing. Implemented to return the actors that occupy neighboring grid locations.
	 * This method is final and can not be overriden
	 * Postcondition: The state of all actors is unchanged.
	 * @return a list of actors that this PaintBallCritter wishes to process
	 */
	final ArrayList<Actor> getActors()
	{
		return getGrid().getNeighbors(getLocation());
	}

	/**
	 * Processes the elements of actors. New actors may be added to empty locations.
	 * Implemented to “eat” (i.e., remove) selected actors that are not rocks or PaintBallCritters.
	 * Override this method in subclasses to process actors in a different way. This method is private and cannot be overriden
	 * Postcondition: (1) The state of all actors in the grid other than this PaintBallCritter and the
	 * elements of actors is unchanged. (2) The location of this PaintBallCritter is unchanged.
	 * @param actors the actors to be processed
	 */
	final void processActors(ArrayList<Actor> actors)
	{
		for (Actor a : actors)
		{

			a.setColor(getColor());
		}
	}
	/**
	 * Gets a list of possible locations for the next move. These locations must be valid in the grid of this PaintBallCritter.
	 * Implemented to return the empty neighboring locations. Override this method in subclasses to look
	 * elsewhere for move locations.
	 * Postcondition: The state of all actors is unchanged.
	 * @return a list of possible locations for the next move
	 */
	public ArrayList<Location> getMoveLocations()
	{
		return getGrid().getEmptyAdjacentLocations(getLocation());
	}
	/**
	 * Selects the location for the next move. Implemented to randomly pick one of the possible locations,
	 * or to return the current location if locs has size 0. Override this method in subclasses that
	 * have another mechanism for selecting the next move location.
	 * Postcondition: (1) The returned location is an element of locs, this PaintBallCritter’s current location, or null.
	 * (2) The state of all actors is unchanged.
	 * @param locs the possible locations for the next move
	 * @return the location that was selected for the next move
	 */
	public Location selectMoveLocation(ArrayList<Location> locs)
	{
		int n = locs.size();
		if (n == 0)
			return getLocation();
		int r = (int) (Math.random() * n);
		return locs.get(r);
	}

	/**
	 * Moves this PaintBallCritter to the given location loc, or removes this PaintBallCritter from its grid if loc is null.
	 * An actor may be added to the old location. If there is a different actor at location loc, that actor is
	 * removed from the grid. Override this method in subclasses that want to carry out other actions
	 * (for example, turning this PaintBallCritter or adding an occupant in its previous location).
	 * Postcondition: (1) getLocation() == loc.
	 * (2) The state of all actors other than those at the old and new locations is unchanged.
	 * @param loc the location to move to
	 */
	public void makeMove(Location loc)
	{
		if (loc == null)
			removeSelfFromGrid();
		else
			moveTo(loc);
	}
}