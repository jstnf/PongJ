package com.jstnf.pongj.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.jstnf.pongj.objects.GameObject;
import com.jstnf.pongj.objects.Paddle;

public class KeyInput extends KeyAdapter
{
	private Handler handler;

	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++)
		{
			GameObject obj = handler.object.get(i);
			if (obj instanceof Paddle)
			{
				switch (((Paddle) obj).getPlayerNum())
				{
					case 1:
						if (key == KeyEvent.VK_W)
						{
							((Paddle) obj).move(true);
						}
						else if (key == KeyEvent.VK_S)
						{
							((Paddle) obj).move(false);
						}
						break;
					case 2:
						if (key == KeyEvent.VK_UP)
						{
							((Paddle) obj).move(true);
						}
						else if (key == KeyEvent.VK_DOWN)
						{
							((Paddle) obj).move(false);
						}
						break;
				}
			}
		}
	}

	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++)
		{
			GameObject obj = handler.object.get(i);
			if (obj instanceof Paddle)
			{
				switch (((Paddle) obj).getPlayerNum())
				{
					case 1:
						if (key == KeyEvent.VK_W || key == KeyEvent.VK_S)
						{
							((Paddle) obj).stop();
						}
						break;
					case 2:
						if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP)
						{
							((Paddle) obj).stop();
						}
						break;
				}
			}
		}
	}
}
