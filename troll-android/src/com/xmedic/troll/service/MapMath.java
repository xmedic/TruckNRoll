package com.xmedic.troll.service;

import android.graphics.Point;

public class MapMath {
	
	public final static int MAP_WIDTH = 2119;
	
	public final static int MAP_HEIGHT = 1619;

	public static Point toScrollPoint(Point point) {
		return new Point(((int)(MAP_WIDTH /2 - point.x)) * -1,
				((int)(MAP_HEIGHT /2 - point.y)) * -1);
	}
	
	public static Point toDrawPoint(Point point, int screenX, int screenY) {
		return new Point(point.x - screenX, point.y - screenY);
	}
}
