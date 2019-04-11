package com.jstnf.pongj.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.jstnf.pongj.main.Entity;
import com.jstnf.pongj.main.Handler;

public class Ball extends GameObject
{
	private final int INCREASE_SPEED_AFTER_THIS_MANY_HITS = 10;
	private int velocity, angle, yVelocity, hits;
	private double yScale, yTimer;
	private boolean xDirection; // true, goes left, false, goes right (xDirection)
	private boolean debug;
	public final int WIDTH = 10, HEIGHT = 10;

	public Ball(int initVelocity, int initAngle, boolean initDirection, Handler handler, boolean debug)
	{
		super(handler.getGame().WIDTH / 2, handler.getGame().HEIGHT / 2, Entity.BALL, handler);
		velocity = initVelocity;
		angle = initAngle;
		xDirection = initDirection;
		yTimer = 0;
		hits = 0;
		yVelocity = 1;
		this.debug = debug;
	}

	@Override
	public void tick()
	{

		yVelocity = hits / INCREASE_SPEED_AFTER_THIS_MANY_HITS + 1;

		if (xDirection)
		{
			x -= velocity;
			if (x < 0)
				xDirection = !xDirection;
		}
		else
		{
			x += velocity;
			if (x > handler.getGame().WIDTH)
				xDirection = !xDirection;
		}

		yScale = 1.0 * (angle / 45.0);

		if (yScale < 0)
		{
			yTimer += yScale;
			if (yTimer < -1.0)
			{
				y -= yVelocity;
				yTimer += 1;
			}
			checkTopWallCollision();
		}
		else
		{
			yTimer += yScale;
			if (yTimer > 1.0)
			{
				y += yVelocity;
				yTimer -= 1;
			}
			checkBottomWallCollision();
		}

	}

	@Override
	public void render(Graphics2D g2d)
	{
		// BALL ITSELF
		g2d.setColor(Color.WHITE);
		g2d.fillRect(x - WIDTH / 2, y - WIDTH / 2, WIDTH, HEIGHT);

		if (debug)
		{
			// DEBUG TEXT
			g2d.drawString("x: " + x + " y: " + y + " velocity: " + velocity + " angle: " + angle, 10, 10);
			g2d.drawString("hits: " + hits + " yVelocity: " + yVelocity, 10, 20);

			// COLLISION CHECK
			g2d.setColor(Color.YELLOW);
			g2d.fillRect(5, y - (HEIGHT / 2), 5, HEIGHT);
			g2d.setColor(Color.MAGENTA);
			g2d.fillRect(5, y, 5, 1);
		}
	}

	public boolean checkPlayerCollision(Paddle paddle)
	{
		if (y < paddle.getLowerLim() + HEIGHT - 1 && y > paddle.getUpperLim() - HEIGHT + 1)
		{
			hits++;
			return true;
		}
		return false;
	}

	public boolean checkCPUCollision(Paddle paddle)
	{
		if (y < paddle.getLowerLim() + HEIGHT - 1 && y > paddle.getUpperLim() - HEIGHT + 1)
		{
			hits++;
			return true;
		}
		return false;
	}

	public void reverseOnPaddleHit()
	{
		xDirection = !xDirection;
		angle = (int) (Math.random() * 180 - 90);
		System.out.println("Angle randomized.");
		yTimer = 0;
	}

	private void checkTopWallCollision()
	{
		if (y < 50 + HEIGHT / 2)
		{
			System.out.println("Top wall hit.");
			y = 49 + HEIGHT / 2;
			angle *= -1;
			yTimer = 0;
		}
	}

	private void checkBottomWallCollision()
	{
		if (y > handler.getGame().HEIGHT - 75 - HEIGHT / 2)
		{
			System.out.println("Bottom wall hit.");
			y = handler.getGame().HEIGHT - 74 - HEIGHT / 2;
			angle *= -1;
			yTimer = 0;
		}
	}
}
