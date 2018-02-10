package src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SPN {

	public static void main(String[] args) {
		test();
	}
	
	private static void test() {
		for(String s: round_key("123"))System.out.println(s);
		int[] permuation = {0,1,3,2};
		System.out.println(permutation(permuation, "1234"));
		System.out.println(1 ^ 2);
		System.out.println(zufallszahl(8, 2));
		erzeuge_Klartext_Kryptotext_Paare(10, 8);
	}
	
	/**
	 * gibt die durch 'begin' und 'end' definierte Ziffernfolge der Zahl x zurueck
	 * @param begin
	 * @param end
	 * @param x
	 * @return
	 */
	public static String ziffernfolge(int begin, int end, String x) {
		return x.substring(begin-1, end);
	}
	
	/**
	 * wendete die (durch eine Array von Indezes angegebene) Permutation auf Zahl x an
	 * @param P
	 * @param x
	 * @return
	 */
	public static String permutation(int[] P, String x) {
		int length = String.valueOf(x).length();
		String x_ ="";
		for(int i=0; i<length; i++) {
			x_ +=  x.charAt(P[i]);
		}
		return x_;
	}
	
	public static String substitution(HashMap<String, String> hs, String x) {
		String x_ = "";
		for(int pos=0; pos<x.length(); pos++) {
			x_ += hs.get(x.substring(pos, pos));
		}
		return x_;
	}

	public static String[] round_key(String K){
		String[] rk = new String[K.length()+1];
		for(int runde=1; runde <= K.length()+1; runde++) {
			String runde_s ="";
			for(int pos=0; pos<K.length(); pos++) {
				int pos_ = (pos + runde) % (K.length());
				runde_s += K.charAt(pos_);
			}
			rk[runde-1]=runde_s;
		}
		return rk;
	}
	
	public static String XOR(String a, String b) {
		String x = "";
		for(int i=0; i<a.length(); i++) {
			int a_i = Integer.getInteger(a.substring(i, i));
			int b_i = Integer.getInteger(a.substring(i, i));
			x += String.valueOf(a_i ^ b_i);
		}
		return x;
	}
	
	public static String[][] erzeuge_Klartext_Kryptotext_Paare(int anz, int laenge_klartext) {
		String [][] paare = new String[anz][2];
		//Substitution
			HashMap<String, String> hs = new HashMap<>(); 
			hs.put("16", "0"); hs.put("3", "1"); hs.put("2", "2"); hs.put("6", "3"); 
			hs.put("1", "4"); hs.put("9", "5"); hs.put("5", "6"); hs.put("11", "7"); 
			hs.put("0", "8"); hs.put("14", "9"); hs.put("8", "10"); hs.put("13", "11"); 
			hs.put("4", "12"); hs.put("7", "13"); hs.put("10", "14"); hs.put("12", "15"); 
		//Permutation
			int[] P = new int[16];
			P[0] = 0; P[1] = 4; P[2] = 8; P[3] = 12;
			P[4] = 1; P[5] = 5; P[6] = 9; P[7] = 13;
			P[8] = 2; P[9] = 6; P[10] = 10; P[11] = 14;
			P[12] = 3; P[13] = 7; P[14] = 11; P[15] = 15;
		//roung key 
			String[] roundkey = round_key(""); //TODO: Was ist der key? zufaellig?
		for(int p=0; p<anz; p++) {
			paare[p][0] = zufallszahl(16, 2);
			paare[p][1] = verschluessele(hs, P, 16, paare[p][0], roundkey);
		}
		return paare;
	}
	
	public static String zufallszahl(int laenge, int zahlensystem) {
		String zufallszahl = "";
		for(int pos=0; pos<laenge; pos++) {
			zufallszahl += String.valueOf((int)(Math.random()*1000) % zahlensystem);
		}
		return zufallszahl;
	}

	public static String verschluessele(HashMap<String, String> S, int[] P, int N, String x, String[] roundkey) {
		String w_rm1 = x;
		for(int n=1; n<N; n++) {
			String u_r = XOR(w_rm1, roundkey[n]);
			String v_r = substitution(S, u_r);
			w_rm1 = permutation(P, v_r);
		}
		String u_n = XOR(w_rm1, roundkey[N]);
		String v_r = substitution(S, u_n);
		return XOR(v_r, roundkey[N+1]);
	}
	
	public static void LINEARATTACK(String[] X, String[] Y) {
		int t = X.length;
		int[][] alpha = new int[16][16];
		int[][] beta = new int[16][16];
		HashMap<String, String> hs = new HashMap<>(); 
		hs.put("0", "16"); hs.put("1", "3"); hs.put("2", "2"); hs.put("3", "6"); 
		hs.put("4", "1"); hs.put("5", "9"); hs.put("6", "5"); hs.put("7", "11"); 
		hs.put("8", "0"); hs.put("9", "14"); hs.put("10", "8"); hs.put("11", "13"); 
		hs.put("12", "4"); hs.put("13", "7"); hs.put("14", "10"); hs.put("15", "12"); 
		for(int l1=0; l1<16; l1++) {
			for(int l2=0; l2<16; l2++) {	
				alpha[l1][l2]=0;
			}
		}
		for(String x: X) {
			for(String y: Y) {
				for(int l1=0; l1<16; l1++) {
					for(int l2=0; l2<16; l2++) {
						String l1_s = String.valueOf(l1);
						String l2_s = String.valueOf(l1);
						String v_4_1 = XOR(l1_s, y.substring(1, 4));
						String v_4_3 = XOR(l2_s, y.substring(9, 12));
						String u_4_1 = substitution(hs, v_4_1);
						String u_4_3 = substitution(hs, v_4_3);
							String x_bin = Integer.toBinaryString(Integer.parseInt(x));
							String u_4_1_bin = Integer.toBinaryString(Integer.parseInt(u_4_1));
							String u_4_3_bin = Integer.toBinaryString(Integer.parseInt(u_4_3));
						if((Integer.valueOf(x_bin.charAt(15)) ^ Integer.valueOf(u_4_1_bin.charAt(1)) ^ Integer.valueOf(u_4_3_bin.charAt(9))) == 0)alpha[l1][l2]++;
					}
				}
			}
		}
		int max = -1;
		int[] maxkey = new int[2];
		for(int l1=0; l1<16; l1++) {
			for(int l2=0; l2<16; l2++) {
				beta[l1][l2] = Math.abs(alpha[l1][l2] - t/2);
				if(beta[l1][l2] > max) {
					max = beta[l1][l2];
					maxkey[0]=l1; maxkey[1]=l2;
				}
			}
		}
		System.out.println(maxkey[0]+" "+maxkey[1]);
	}

}
