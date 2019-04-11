package com.jstnf.pongj.objects;

import java.awt.Graphics2D;

import com.jstnf.pongj.main.Entity;
import com.jstnf.pongj.main.Handler;

public abstract class GameObject {

	protected int x, y;
	protected Entity id;
	protected Handler handler;

	public GameObject(int x, int y, Entity id, Handler handler) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}

	public abstract void tick();

	public abstract void render(Graphics2D g2d);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setId(Entity id) {
		this.id = id;
	}

	public Entity getId() {
		return id;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
