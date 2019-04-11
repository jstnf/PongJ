package com.jstnf.pongj.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Pong extends Canvas implements Runnable
{
	private static final long serialVersionUID = -6091534922098305805L;
	public final int WIDTH = 750, HEIGHT = 500;
	private Thread thread;
	private boolean running;
	private Handler handler;

	public Pong()
	{
		handler = new Handler(this);
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH, HEIGHT, "PongJ", this);
	}

	public static void main(String[] args)
	{
		new Pong();
	}

	public synchronized void start()
	{
		if (!running)
		{
			thread = new Thread(this);
			thread.start();
			running = true;
		}
		Assets.init();
	}

	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1)
			{
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	public void tick()
	{
		handler.tick();
	}

	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.fillRect(0, 48, WIDTH, 2);
		g.fillRect(0, HEIGHT - 75, WIDTH, 2);

		handler.render((Graphics2D) g);

		g.dispose();
		bs.show();
	}
}
