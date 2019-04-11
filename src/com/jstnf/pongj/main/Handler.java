package com.jstnf.pongj.main;

import java.awt.Graphics2D;
import java.util.LinkedList;

import com.jstnf.pongj.objects.Ball;
import com.jstnf.pongj.objects.GameObject;
import com.jstnf.pongj.objects.Paddle;

public class Handler
{
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	private Pong game;
	private Paddle player, player2;
	private Ball ball;
	private int difficulty;
	private final boolean DEBUG = true;

	public Handler(Pong game)
	{
		this.game = game;
		ball = new Ball(6, 0, true, this, DEBUG);
		addObject(ball);
		player = new Paddle(this, false, false, DEBUG);
		player2 = new Paddle(this, true, true, DEBUG);
		addObject(player);
		addObject(player2);
		difficulty = 2;
	}

	public void tick()
	{
		for (GameObject obj : object)
		{
			obj.tick();

			if (obj.getId() == Entity.BALL)
			{
				if (obj.getX() > 50 && obj.getX() < 56)
				{
					System.out.println("Checking collision on Player 1.");
					if (((Ball) obj).checkPlayerCollision(player))
					{
						System.out.println("HIT PLAYER 1!");
						((Ball) obj).reverseOnPaddleHit();
					}
				}
				if (obj.getX() > getGame().WIDTH - 68 && obj.getX() < getGame().WIDTH - 62)
				{
					System.out.println("Checking collision on Player 2.");
					if (((Ball) obj).checkCPUCollision(player2))
					{
						System.out.println("HIT PLAYER 2!");
						((Ball) obj).reverseOnPaddleHit();
					}
				}
			}

			if (obj.getId() == Entity.PLAYER)
			{

			}

			if (obj.getId() == Entity.CPU)
			{
				difficultyTick((Paddle) obj, ball);
			}
		}
	}

	public void render(Graphics2D g)
	{
		for (GameObject obj : object)
		{
			obj.render(g);
		}
	}

	public void setDifficulty(int newDifficulty)
	{
		this.difficulty = newDifficulty;
	}

	public void addObject(GameObject object)
	{
		this.object.add(object);
	}

	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}

	public Pong getGame()
	{
		return game;
	}

	public Ball getBall()
	{
		return ball;
	}

	public int getDifficulty()
	{
		return difficulty;
	}

	// targetSwitch true if target is Player 1, false if target is Player 2
	public void difficultyTick(Paddle target, Ball ball)
	{
		if (difficulty == 0)
			return;

		if (difficulty == 3)
		{
			// EXPERT DIFFICUTLY BEHAVIOR HERE
			return;
		}

		if (ball.getY() < target.getUpperLim())
		{
			target.cpuMove(true);
		}
		else if (ball.getY() > target.getLowerLim())
		{
			target.cpuMove(false);
		}
	}
}
