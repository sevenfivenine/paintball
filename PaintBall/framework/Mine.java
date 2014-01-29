/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 */



import info.gridworld.actor.Actor;
import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>Rock</code> is an actor that does nothing. It is commonly used to
 * block other actors from moving. <br />
 * The API of this class is testable on the AP CS A and AB exams.
 */

public class Mine extends Actor
{


	private static final Color DEFAULT_COLOR = Color.BLACK;
	private int myAge;

	/**
	 * Constructs a black rock.
	 */
	public Mine()
	{
		setColor(DEFAULT_COLOR);
		myAge =0;
	}

	public void setColor(Color newColor)
	{

	}
	public void act()
	{
		if(myAge == 2)
		{
			ArrayList <Actor> neighbors = getGrid().getNeighbors(getLocation());
			for(int i =0; i < neighbors.size(); i++)
			{
				neighbors.get(i).removeSelfFromGrid();
			}
			removeSelfFromGrid();
		}
		myAge++;
	}
}

