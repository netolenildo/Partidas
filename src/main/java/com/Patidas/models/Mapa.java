package com.Patidas.models;

public class Mapa {

	public static final int MIRAGE = 1;
	
	public static final int INFERNO = 2;

	public static final int VERTIGO = 3;
	
	public static final int DUST_2 = 4;
	
	public static final int NUKE = 5;
	
	public static final int OVERPASS = 6;
	
	public static final int TRAIN = 7;
	
	public String getNome(int mapa) {
		switch (mapa) {
			case MIRAGE:
				return "Mirage";
			case INFERNO:
				return "Inferno";
			case VERTIGO:
				return "Vertigo";
			case DUST_2:
				return "Dust 2";
			case NUKE:
				return "Nuke";
			case OVERPASS:
				return "Overpass";
			case TRAIN:
				return "Train";
		}
		return null;
	}
	
}
