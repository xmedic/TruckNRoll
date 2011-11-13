package com.xmedic.troll.service;

import android.graphics.Point;

public class MapMath {
	
	public enum MapType {
		NORMAL,
		PREVIEW
	}
	
	public final static int MAP_WIDTH = 2119;
	
	public final static int MAP_HEIGHT = 1619;
	
	public final static int PREVIEW_MAP_WIDTH = 636;
	
	public final static int PREVIEW_MAP_HEIGHT = 486;

	public static Point toScrollPoint(Point point, MapType type) {
		return new Point(((int)((type == MapType.NORMAL ? MAP_WIDTH :PREVIEW_MAP_WIDTH) /2 - point.x)) * -1,
				((int)((type == MapType.NORMAL ? MAP_HEIGHT :PREVIEW_MAP_HEIGHT) /2 - point.y)) * -1);
	}
	
	public static Point toDrawPoint(Point point, int screenX, int screenY) {
		return new Point(point.x - screenX, point.y - screenY);
	}
}
