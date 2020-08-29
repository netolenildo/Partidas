package com.Patidas.models;

public class Posicao {

	public static final int AWPER = 1;
	
	public static final int RIFLER = 2;
	
	public static final int ENTRY_FRAGGER = 3;
	
	public static final int SUPPORT = 4;
	
	public static final int LUCKER = 5;
	
	public String getNome(int posicao) {
		switch (posicao) {
			case AWPER:
				return "Awper";
			case RIFLER:
				return "Rifler";
			case ENTRY_FRAGGER:
				return "Entry Fragger";
			case SUPPORT:
				return "Support";
			case LUCKER:
				return "Lucker";
			}
			return null;
	}
}
