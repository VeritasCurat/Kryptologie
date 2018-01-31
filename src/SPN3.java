/* Urheber: grobelsj, 565530
 * 			kleinede, 564838
 */
package src;
import java.util.ArrayList;
import java.util.HashMap;

public class SPN3
{
    public static void main(String[] args) {
    	test();
		//System.out.println("Mittel: "+search_T(100,10));

//    	int[] maxkey = differentialattack(paare(20000, 4));
//    	System.out.println(maxkey[0]+" "+maxkey[1]);
    	search_T(10000, 1);

    }

    private static void test() {


//    	//roundkey
	    	int[] key = {3,10,9,4,13,6,3,15};
	    	for(int[] x: round_key(key, 5, 4)) {
	    		for(int y: x)System.out.print(y+" ");
	    		System.out.println();
	    	}
	    	
//	    //XOR
//	    	int[] x = {1,2,3};
//	    	int[] y = {0,1,2};
//	    	for(int z: XOR(x,y))System.out.println(z);
//    	//permutation
//    		int[] permutation= {0,4,8,12,1,5,9,13,2,6,10,14,3,7,11,15};
//    		int[] x = {4,5,13,1};
//    		permutation(permutation,x);
    	//binTodez
    		//System.out.println(binTodez(1011));
	    // verschluesselung
//	    	int[] x = {2,6,11,7};
//	    	for(int z: verschluesselung(x))System.out.println(z);
    	//for(int i=0; i<16; i++)	System.out.println(String.format("%05d", i));
    	//paare
//    		int[][][] paare = paare(1,4);
//    		for(int i=0; i<1; i++) {
//    			for(int k=0; k<4;k++) {
//    				System.out.print(paare[i][0][k]+" ");
//    			}
//    			System.out.println();
//    			for(int k=0; k<4;k++) {
//    				System.out.print(paare[i][1][k]+" ");
//    			}
//    			System.out.println();
//    		}
    	//linearattack


			//int[] key = linearattack(paare(2251, 4), 2251);
			//System.out.println(key[0] + " " + key[1]);


	}

    /**
     * sucht nach T (durchschnittliches t) bei anz_durchlauefem. Erweitert anzahl 
     * 
     */
//	public static int search_T(int inkrement_paare, int anz_durchlauefe) {
//		int summe_last_c_t = 0;
//		int last_c_t = 0;
//		for(int durchlauf=1; durchlauf<=anz_durchlauefe; durchlauf++) {
//			long timeStart=System.nanoTime();
//			long timeEnd;
//			int lastt = 0;
//			int t = 1;
//			int alpha[][] = new int[16][16];
//			int[][][] paare = null;
//			int laengsteSeq_13_3=0;
//			while(true) {
//				paare = newpaare(paare, t - lastt, 4, lastt);
//				alpha = differentialattack(paare, alpha, lastt);
//				//System.out.println(t+": "+beta[i][0]+" "+beta[i][1]);
//				lastt = t;
//				t += inkrement_paare;
//				timeEnd = System.nanoTime();
//				System.out.println("Verlaufszeit: " + ((timeEnd - timeStart) / 1000000000.0) + " sek.");
//				//maxkey
//				int[][] beta = new int[16][16];
//				int max =-1;
//				int[] maxkey = new int[2];
//				for(int l1=0; l1<16; l1++) {
//					for(int l2=0; l2<16; l2++) {
//						beta[l1][l2] = Math.abs(alpha[l1][l2] - t/2);
//						if(beta[l1][l2] > max) {
//							max = beta[l1][l2];
//							maxkey[0]=l1; maxkey[1]=l2;
//						}
//					}
//				}
//				System.out.println("maxkey: "+maxkey[0]+" "+maxkey[1]);
//				System.out.println("t: "+t);
//				if(maxkey[0] == 13 && maxkey[1] == 3) {last_c_t = t;laengsteSeq_13_3++;}
//				else {laengsteSeq_13_3=0;last_c_t=-1;};
//				//annahme: wenn [13,3] 20-mal nacheinander ausgegeben wird(
//				if(laengsteSeq_13_3>=20)break;
//			}
//			summe_last_c_t += last_c_t;
//			System.out.println("aktuellerDurchschnitt"+summe_last_c_t/durchlauf);
//			timeEnd = System.nanoTime();
//			System.out.println("Endzeit: " + ((timeEnd - timeStart) / 1000000000.0) + " sek.");
//		}
//		return summe_last_c_t/anz_durchlauefe;
//	}

    /**
     * wandelt integerarray mit dezimalzahlen in Sequenz von binaerzahlen dargestellt als String um
     * @param x
     * @return
     */
	public static String toBinString(int[] x) {
		String x_bin = "";
		for(int i: x) {
			String block_bin = String.valueOf(Integer.toBinaryString(i));
			while(block_bin.length()<4) block_bin = "0" + block_bin;
			x_bin+=block_bin;
		}
		return x_bin;
	}

    /**
     * linearattack mit folgender Modifikation: alpha wird nur durch neue Paare modifiziert und zuerueckgegeben
     * @param paare
     * @param t
     * @param alpha
     * @param startpos
     * @return alpha
     */
	public static int[][] differentialattack(int[][][] paare, int[][] gamma,int startpos) {
		int[] S_ = {6,2,1,3,9,10,7,14,12,5,11,15,13,4,0,8};		
		for(int q1=startpos; q1<paare.length;q1++) {
				//if(q1%10000 == 0)System.out.println(q1);
			for(int q2=startpos; q2<paare.length;q2++) {
				int[] y1 = paare[q1][1];
				int[] y2 = paare[q2][1];
				if(y1[2] == y2[2] && y1[3] == y2[3]) { 
					for(int l1=0; l1<16; l1++) {
						for(int l2=0; l2<16; l2++) {		
							int v1_4_1 = l1 ^ y1[0];
							int v1_4_2 = l2 ^ y1[1];
							int v2_4_1 = l1 ^ y2[0];
							int v2_4_2 = l2 ^ y2[1];
								//System.out.println(v1_4_1+" "+v1_4_2+" "+v2_4_1+" "+v2_4_2);
							int u1_4_1 = substitution(S_, v1_4_1);
							int u1_4_2 = substitution(S_, v1_4_2);
							int u2_4_1 = substitution(S_, v2_4_1);
							int u2_4_2 = substitution(S_, v2_4_2);
								//System.out.println(u1_4_1+" "+u1_4_2+" "+u2_4_1+" "+u2_4_2);
							int w1 = u1_4_1 ^ u2_4_1;
							int w2 = u1_4_2 ^ u2_4_2;
								//System.out.println(w1+" "+w2);
							if(w1 == 1 && w2 == 1)gamma[l1][l2]++;
						}
					}
				}
				
			}
		}
		return gamma;
//		int max =-1;
//		int[] maxkey=new int[2];
//		for(int l1=0; l1<16; l1++) {
//			for(int l2=0; l2<16; l2++) {	
//				//System.out.print(gamma[l1][l2]+" ");
//				if(gamma[l1][l2] > max) {maxkey[0]=l1; maxkey[1]=l2; max=gamma[l1][l2];}
//			}
//			//System.out.println();
//		}
//		//if(maxkey[0] == 13 && maxkey[1] == 3)System.out.println("[13][3]"+beta[13][3]+" maxkey:"+beta[maxkey[0]][maxkey[1]]);
//		return maxkey;
	}
	

    /**
     * sucht nach T (durchschnittliches t) bei anz_durchlauefem. Erweitert anzahl 
     * 
     */
	public static int search_T(int inkrement_paare, int anz_durchlauefe) {
		int summe_last_c_t = 0;
		int last_c_t = 0;
		for(int durchlauf=1; durchlauf<=anz_durchlauefe; durchlauf++) {
			long timeStart=System.nanoTime();
			long timeEnd;
			int lastt = 0;
			int t = 1;
			int alpha[][] = new int[16][16];
			int[][][] paare = null;
			int laengsteSeq_13_3=0;
			while(true) {
				paare = newpaare(paare, t - lastt, 4, lastt);
				alpha = differentialattack(paare, alpha, lastt);
				//System.out.println(t+": "+beta[i][0]+" "+beta[i][1]);
				lastt = t;
				t += inkrement_paare;
				timeEnd = System.nanoTime();
				System.out.println("Verlaufszeit: " + ((timeEnd - timeStart) / 1000000000.0) + " sek.");
				//maxkey
				int[][] beta = new int[16][16];
				int max =-1;
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
				System.out.println("maxkey: "+maxkey[0]+" "+maxkey[1]);
				System.out.println("t: "+t);
				if(maxkey[0] == 13 && maxkey[1] == 6) {last_c_t = t;laengsteSeq_13_3++;}
				else {laengsteSeq_13_3=0;last_c_t=-1;};
				//annahme: wenn [13,3] 20-mal nacheinander ausgegeben wird(
				if(laengsteSeq_13_3>=20)break;
			}
			summe_last_c_t += last_c_t;
			System.out.println("aktuellerDurchschnitt"+summe_last_c_t/durchlauf);
			timeEnd = System.nanoTime();
			System.out.println("Endzeit: " + ((timeEnd - timeStart) / 1000000000.0) + " sek.");
		}
		return summe_last_c_t/anz_durchlauefe;
	}

	public static int[] differentialattack_v(int[][][] paare) {
		int[][] gamma = new int[16][16];
		int[] S_ = {14,3,4,8,1,12,10,15,7,13,9,6,11,2,0,5};
		for(int q1=0; q1<paare.length;q1++) {
				//if(q1%10000 == 0)System.out.println(q1);
			for(int q2=0; q2<paare.length;q2++) {
				int[] y1 = paare[q1][1];
				int[] y2 = paare[q2][1];
				if(y1[0] == y2[0] && y1[2] == y2[2]) { 
					for(int l1=0; l1<16; l1++) {
						for(int l2=0; l2<16; l2++) {		
							int v1_4_2 = l1 ^ y1[1];
							int v1_4_4 = l2 ^ y1[3];
							int v2_4_2 = l1 ^ y2[1];
							int v2_4_4 = l2 ^ y2[3];
								//System.out.println(v1_4_1+" "+v1_4_2+" "+v2_4_1+" "+v2_4_2);
							int u1_4_2 = substitution(S_, v1_4_2);
							int u1_4_4 = substitution(S_, v1_4_4);
							int u2_4_2 = substitution(S_, v2_4_2);
							int u2_4_4 = substitution(S_, v2_4_4);
								//System.out.println(u1_4_1+" "+u1_4_2+" "+u2_4_1+" "+u2_4_2);
							int w1 = u1_4_2 ^ u2_4_2;
							int w2 = u1_4_4 ^ u2_4_4;
								//System.out.println(w1+" "+w2);
							if(w1 == 6 && w2 == 6)gamma[l1][l2]++;
						}
					}
				}
			}
		}
		int max =-1;
		int[] maxkey=new int[2];
		for(int l1=0; l1<16; l1++) {
			for(int l2=0; l2<16; l2++) {	
				//System.out.print(gamma[l1][l2]+" ");
				if(gamma[l1][l2] > max) {maxkey[0]=l1; maxkey[1]=l2; max=gamma[l1][l2];}
			}
			//System.out.println();
		}
		//if(maxkey[0] == 13 && maxkey[1] == 3)System.out.println("[13][3]"+beta[13][3]+" maxkey:"+beta[maxkey[0]][maxkey[1]]);
		return maxkey;
	}
	
    /**
     * erweitert alte Paar-Menge(Menge von Klartext-Kryptotext-Paaren) um anz Paare
     * @param paar
     * @param anz
     * @param laenge
     * @param startpos
     * @return
     */
	public static int[][][] newpaare(int[][][]paar ,int anz, int laenge,int startpos){
    	int anzahl=startpos+anz;
    	int[][][] paarmenge = new int[anzahl][2][laenge];
    	for (int i = 0; i <startpos ; i++) {
			paarmenge[i]=paar[i];
    	}


		for(int z=startpos; z<anzahl; z++) {
			for(int pos=0; pos<laenge; pos++) {
				paarmenge[z][0][pos] = ((int) (Math.random()*10000)) % 16;
			}
			paarmenge[z][1] = verschluesselung(paarmenge[z][0]);
//        	for(int x: paare[z][0])System.out.print(x+" ");
//        	System.out.println();
//        	for(int y: paare[z][1])System.out.print(y+" ");
//        	System.out.println();
		}
		return paarmenge;
	}

	/**
	 * erstellt klartext und berechnet dafuer kryptotexte
	 * @param anz
	 * @param laenge
	 * @return
	 */
    public static int[][][] paare(int anz, int laenge){
    	int[][][] paare = new int[anz][2][laenge];
    	for(int z=0; z<anz; z++) {
    		for(int pos=0; pos<laenge; pos++) {
    			paare[z][0][pos] = ((int) (Math.random()*10000)) % 16;
    		}
    		paare[z][1] = verschluesselung(paare[z][0]);
//        	for(int x: paare[z][0])System.out.print(x+" ");
//        	System.out.println();
//        	for(int y: paare[z][1])System.out.print(y+" ");
//        	System.out.println();
    	}
    	return paare;
    }
    
    /**
     * verschluesselt klartext (in form von int-array von hexdezimalzahlen (dargestellt als dezimalzahlen))
     * mithilfe des Algorithmus aus der Vorlesung (SPN)
     * @param x
     * @return
     */
    public static int[] verschluesselung(int[] x) {
    	int[] permutation= {0,4,8,12,1,5,9,13,2,6,10,14,3,7,11,15};
    	//int[] substitution = {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7}; //VL
   	    int[] substitution = {14,2,1,3,13,9,0,6,15,4,5,10,8,12,7,11}; //UE
    	int[] key = {3,10,9,4,13,6,3,15};
    	int[][] roundkey = round_key(key, 5, 4);
   	
    	int[] w_rm1 = new int[x.length];
    	w_rm1 = x;
    	for(int r = 0; r < 3; r++) {
    		int[] u_r = XOR(w_rm1, roundkey[r]);
    		int[] v_r = substitution(substitution, u_r);
    		w_rm1 = permutation(permutation, v_r);
    	}
    	int[] u_n = XOR(w_rm1,roundkey[3]);
    	int[] v_n = substitution(substitution,u_n);
    	return XOR(v_n, roundkey[4]);
    }


    /**
     * wandelt ein Sequenz von Zahlen (dargestellt als String) in ein 
     * @param eingabezahl
     * @return
     */
    public static int[] String_to_Array(String eingabezahl)
    {
        int[] eingabe_Zahl_als_Array_10=new int[eingabezahl.length()];
        for (int i = 0; i <eingabezahl.length() ; i++)
        {
            eingabe_Zahl_als_Array_10[i]=Character.digit(eingabezahl.charAt(i),16);
        }
        return eingabe_Zahl_als_Array_10;
    }
    
    private static int binTodez(int bin) {
    	int dez=0;
    	int stelle =0;
    	while(bin > 0) {
    		int rest = bin%10;
    		dez+=Math.pow(2, stelle)*rest;
    		bin/=10;		
    		stelle++;
    	}
    	return dez;
    }
    
    /**
     * 
     * @param permutation ist fuer binaerzahlen
     * @param eingabezahl ist hexadezimal
     * @return permuation als hex
     */
    public static int[] permutation(int[] permutation ,int[] eingabezahl)
    {
    	String eingabezahl_bin = "";
    	for(int x: eingabezahl){
    		String block= Integer.toBinaryString(x);
    		while(block.length() < 4)block = "0"+block;
    		eingabezahl_bin += block;
    	}   	
    		//System.out.println(eingabezahl_bin);
    	if(permutation.length != eingabezahl_bin.length())System.err.println("laenge von permutation ist entspricht nicht laenge von zahl!");
        String permutierte_Zahl_bin="";
        for (int i = 0; i <permutation.length ; i++)
        {
            permutierte_Zahl_bin+=eingabezahl_bin.charAt(permutation[i]);
        }
        	//System.out.print(permutierte_Zahl_bin);
        int[] permutierte_Zahl = new int[eingabezahl.length];
        for(int i=0; i<eingabezahl.length; i++) {
        	String block = permutierte_Zahl_bin.substring(i*4, (i*4) +4);
        	int block_i = Integer.valueOf(block);
        	permutierte_Zahl[i] = binTodez(block_i);
        }
        return permutierte_Zahl;
    }


    public static int[] XOR(int[]a, int[] b)
    {
    	if(a.length != b.length)System.err.println("XOR kann nicht angewandt werden, da Arrays unterschiedlich lang sind!");
        int[] tmpZahl=new int[a.length];
        for (int i = 0; i <a.length ; i++)
        {
            tmpZahl[i]=a[i]^b[i];
        }
        return tmpZahl;
    }

    public static int substitution(int[] S, int x) {
		return S[x];
	}
    
	public static int[] substitution(int[] S, int[] x) {
		int[] ergebnis = new int[x.length];
		for(int pos=0; pos<x.length; pos++) {
			ergebnis[pos]= S[x[pos]];
		}
		return ergebnis;
	}

	/**
	 * 
	 * @param K
	 * @param runden
	 * @param laenge
	 * @return
	 */
	public static int[][] round_key(int[] K, int runden, int laenge){
		int[][] rk = new int[runden][laenge];
		for(int runde=0; runde <= runden-1; runde++) {
			for(int pos=0; pos<laenge; pos++) {
				int pos_ = (pos + runde) % K.length;
				rk[runde][pos] = K[pos_];
			}
		}
		return rk;
	}


}
