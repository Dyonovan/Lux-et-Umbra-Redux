package com.teambrmodding.luxetumbra.api.font;

public class Test {

	public static TrueTypeFont arial = FontLoader.loadSystemFont("Arial", 16f, false);
	public static float[] white = {1f,1f,1f,1f};
	
	
	public static void doTest(){
		FontHelper.drawString("Hello World", 10, 10, arial, 1f, 1f, white);
	}
}
