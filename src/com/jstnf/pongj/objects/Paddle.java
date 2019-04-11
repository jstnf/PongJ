package com.jstnf.pongj.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.jstnf.pongj.main.Entity;
import com.jstnf.pongj.main.Handler;

public class Paddle extends GameObject
{
	private final int WIDTH = 10, HEIGHT = 50;
	private int velocity;
	protected int playerNum;
	protected boolean debug;

	public Paddle(Handler handler, boolean isPlayer2, boolean isCPU, boolean debug)
	{
		super(50, handler.getGame().HEIGHT / 2, Entity.PLAYER, handler);
		playerNum = 1;
		if (isPlayer2)
		{
			this.setX(handler.getGame().WIDTH - 50);
			this.setId(Entity.PLAYER2);
			playerNum = 2;
		}
		if (isCPU)
		{
			this.setId(Entity.CPU);
		}
		velocity = 0;
		this.debug = debug;
	}

	public void tick()
	{
		y += velocity;

		if (y > handler.getGame().HEIGHT - 75 - HEIGHT / 2)
		{
			velocity = 0;
			y = handler.getGame().HEIGHT - 75 - HEIGHT / 2;
		}
		else if (y < 50 + HEIGHT / 2)
		{
			velocity = 0;
			y = 50 + HEIGHT / 2;
		}
	}

	public void render(Graphics2D g2d)
	{
		// PADDLE ITSELF
		if (playerNum == 1)
		{
			g2d.setColor(Color.WHITE);
			g2d.fillRect(x - (WIDTH / 2), y - (HEIGHT / 2), WIDTH, HEIGHT);

			// COLLISION CHECK
			if (debug)
			{
				g2d.setColor(Color.ORANGE);
				g2d.fillRect(10, getUpperLim() - 10, 5, getLowerLim() - getUpperLim() + 20);
				g2d.setColor(Color.RED);
				g2d.fillRect(10, getUpperLim(), 5, getLowerLim() - getUpperLim());
			}
		}
		if (playerNum == 2)
		{
			// PADDLE ITSELF
			g2d.setColor(Color.WHITE);
			g2d.fillRect(x - (WIDTH / 2), y - (HEIGHT / 2), WIDTH, HEIGHT);

			// COLLISION CHECK
			if (debug)
			{
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.fillRect(15, getUpperLim() - 10, 5, getLowerLim() - getUpperLim() + 20);
				g2d.setColor(Color.GREEN);
				g2d.fillRect(15, getUpperLim(), 5, getLowerLim() - getUpperLim());
			}
		}
	}

	/**
	 * Move the paddle.
	 * 
	 * @param direction
	 *            true, UP, false, DOWN
	 */
	public void move(boolean direction)
	{
		if (direction)
		{
			velocity = -5;
		}
		else
		{
			velocity = 5;
		}
	}

	public void cpuMove(boolean direction)
	{
		switch (handler.getDifficulty())
		{
			case 1:
				if (direction)
				{
					y += -1;
				}
				else
				{
					y += 1;
				}
				break;
			case 2:
				if (direction)
				{
					velocity = -5;
				}
				else
				{
					velocity = 5;
				}
				break;
			case 3:
				break;
			default:
				break;
		}
	}

	public void stop()
	{
		velocity = 0;
	}

	public int getUpperLim()
	{
		return y - HEIGHT / 2;
	}

	public int getLowerLim()
	{
		return y + HEIGHT / 2;
	}

	public int getPlayerNum()
	{
		return playerNum;
	}
}
