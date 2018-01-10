/* Urheber: grobelsj, 565530
 * 			kleinede, 564838
 */

import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.midi.Synthesizer;
import javax.xml.bind.SchemaOutputResolver;

public class SPN2
{
    public static void main(String[] args) {
        //testus();
    	//test();
		System.out.println("Mittel: "+search_T(100,10));

    }

    private static void test() {


//    	//roundkey
//	    	int[] key = {3,10,9,4,13,6,3,15};
//	    	for(int[] x: round_key(key, 5, 4)) {
//	    		for(int y: x)System.out.println(y);
//	    	}
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
				alpha = linearattack_mod(paare, t, alpha, lastt);
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
				if(maxkey[0] == 13 && maxkey[1] == 3) {last_c_t = t;laengsteSeq_13_3++;}
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
	

	/**
	 * sucht t mithilfe von binaersuche
	 * @return
	 */
    public static int search_t() {
    	int t = 3001;
    	int untere_grenze = 1000;
    	int obere_grenze = t;
    	int key1 = 13; int key3 = 3;
    	//finde obere grenze
    	while(true) {
    		int[] a = linearattack(paare(t, 4), t);
    		if(a[0] == key1 && a[1] == key3)break;
    		else t*=1.5;
    	}
    	System.out.println(t);
    	while(obere_grenze != untere_grenze) {
    		int[] a = linearattack(paare(obere_grenze, 4), obere_grenze);
    		if(a[0] == key1 && a[1] == key3);
    	}
    	return 0;
    }


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
	 * linearattack mit S^-1 aus Vorlesung
	 * @param paare
	 * @param t
	 * @return
	 */
    public static int[] linearattack_v(int[][][] paare, int t) {
		int[][] alpha = new int[16][16];
		int[][] beta = new int[16][16];
		int[] S_ = {14,3,4,8,1,12,10,15,7,13,9,6,11,2,0,5};
		for(int l1=0; l1<16; l1++) {
			for(int l2=0; l2<16; l2++) {	
				alpha[l1][l2]=0;
			}
		}
		for(int p=0; p<paare.length;p++) {
			for(int q=0; q<paare.length;q++) {
				int[] x = paare[p][0];
				int[] y = paare[q][1];
				for(int l1=0; l1<16; l1++) {
					for(int l2=0; l2<16; l2++) {
						//x in bin
							String x_bin ="";
							for(int i: x) {
								String block_bin = String.valueOf(Integer.toBinaryString(i));
								while(block_bin.length()<4) block_bin = "0" + block_bin;
								x_bin+=block_bin;
							}
//							for(int i: x)System.out.print(i);
//							System.out.println(" "+x_bin);
						int[] v_4_2 = {l1 ^ y[1]};
						int[] v_4_4 = {l2 ^ y[3]};
						int u_4_2= substitution(S_, v_4_2)[0];
						int u_4_4 = substitution(S_, v_4_4)[0];
						//u_4_1 in bin
							String u_4_2_bin ="";
								String block_bin = String.valueOf(Integer.toBinaryString(u_4_2));
								while(block_bin.length()<4) block_bin = "0" + block_bin;	
								u_4_2_bin = block_bin;
						//u_4_3 in bin
							String u_4_4_bin ="";
								block_bin = String.valueOf(Integer.toBinaryString(u_4_4));
								while(block_bin.length()<4) block_bin = "0" + block_bin;
								u_4_4_bin = block_bin;
						
						int x_5 = Character.digit(x_bin.charAt(4), 10);
						int x_7 = Character.digit(x_bin.charAt(5), 10);
						int x_8 = Character.digit(x_bin.charAt(7), 10);
						
						int u_4_6 = Character.digit(u_4_2_bin.charAt(1), 10);
						int u_4_8 = Character.digit(u_4_2_bin.charAt(3), 10);
						int u_4_14 = Character.digit(u_4_4_bin.charAt(1), 10);
						int u_4_16 = Character.digit(u_4_4_bin.charAt(3), 10);

						if((x_5 ^ x_7 ^ x_8 ^ u_4_6 ^ u_4_8 ^ u_4_14 ^ u_4_16) == 0) alpha[l1][l2]++;
					}
				}	
			}
		}
		for(int p=0; p<16;p++) {
			for(int q=0; q<16;q++) {
				System.out.print(alpha[p][q]+" ");
			}
			System.out.println();
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
		return maxkey;
	}


    /**
     * linearattack mit folgender Modifikation: alpha wird nur durch neue Paare modifiziert und zuerueckgegeben
     * @param paare
     * @param t
     * @param alpha
     * @param startpos
     * @return alpha
     */
	public static int[][] linearattack_mod(int[][][] paare, int t,int[][] alpha,int startpos) {
		int[][] beta = new int[16][16];
		int[] S_ = {15,3,2,6,1,9,5,11,0,14,8,13,4,7,10,12};
		for(int p=startpos; p<paare.length;p++) {
			for(int q=startpos; q<paare.length;q++) {
				int[] x = paare[p][0];
				int[] y = paare[q][1];
				for(int l1=0; l1<15; l1++) {
					for(int l2=0; l2<15; l2++) {
						//x in bin
						String x_bin = toBinString(x);
						int[] v_4_1 = {l1 ^ y[0]};
						int[] v_4_3 = {l2 ^ y[2]};
						int[] u_4_1 = substitution(S_, v_4_1);
						int[] u_4_3 = substitution(S_, v_4_3);
						//u_4_1 in bin
						String u_4_1_bin = toBinString(u_4_1);
						//u_4_3 in bin
						String u_4_3_bin = toBinString(u_4_3);
						int x_16 = (int) Character.digit(x_bin.charAt(15), 10);
						int u_4_1_1 = (int) Character.digit(u_4_1_bin.charAt(0), 10);
						int u_4_3_9 = (int) Character.digit(u_4_3_bin.charAt(0), 10);

						if((x_16 ^ u_4_1_1 ^ u_4_3_9) == 0) alpha[l1][l2]++;
					}
				}
			}
		}
		//if(maxkey[0] == 13 && maxkey[1] == 3)System.out.println("[13][3]"+beta[13][3]+" maxkey:"+beta[maxkey[0]][maxkey[1]]);
		return alpha;
	}


    /**
     * rechnet implementiert den Algorithmus aus der Vorlesung mit S^-1 aus Uebung
     * @param paare
     * @param t
     * @return
     */
    public static int[] linearattack(int[][][] paare, int t) {
		int[][] alpha = new int[16][16];
		int[][] beta = new int[16][16];
		int[] S_ = {15,3,2,6,1,9,5,11,0,14,8,13,4,7,10,12};
		for(int l1=0; l1<16; l1++) {
			for(int l2=0; l2<16; l2++) {	
				alpha[l1][l2]=0;
			}
		}

		for(int p=0; p<paare.length;p++) {
			for(int q=0; q<paare.length;q++) {
				int[] x = paare[p][0];
				int[] y = paare[q][1];
				for(int l1=0; l1<15; l1++) {
					for(int l2=0; l2<15; l2++) {
						//x in bin
							String x_bin ="";
							for(int i: x) {
								String block_bin = String.valueOf(Integer.toBinaryString(i));
								while(block_bin.length()<4) block_bin = "0" + block_bin;
								x_bin+=block_bin;
							}
//							for(int i: x)System.out.print(i);
//							System.out.println(" "+x_bin);
						int[] v_4_1 = {l1 ^ y[0]};
						int[] v_4_3 = {l2 ^ y[2]};
						int u_4_1 = substitution(S_, v_4_1)[0];
						int u_4_3 = substitution(S_, v_4_3)[0];
						//u_4_1 in bin
							String u_4_1_bin ="";
								String block_bin = String.valueOf(Integer.toBinaryString(u_4_1));
								while(block_bin.length()<4) block_bin = "0" + block_bin;	
								u_4_1_bin = block_bin;
						//u_4_3 in bin
							String u_4_3_bin ="";
								block_bin = String.valueOf(Integer.toBinaryString(u_4_3));
								while(block_bin.length()<4) block_bin = "0" + block_bin;
								u_4_3_bin = block_bin;
						
						//System.out.println(x_bin+" "+u_4_1_bin+" "+u_4_3_bin);
						int x_16 = Character.digit(x_bin.charAt(15), 10);
						int u_4_1_1 = Character.digit(u_4_1_bin.charAt(0), 10);
						int u_4_3_9 = Character.digit(u_4_3_bin.charAt(0), 10);
						//System.out.println("BIT:" +x_16+" "+u_4_1_1+" "+u_4_3_9);

						if((x_16 ^ u_4_1_1 ^ u_4_3_9) == 0) alpha[l1][l2]++;
					}
				}	
			}
			if(p%200==0) {
				System.out.println("p ist bei:"+ p);
			}
		}
//		for(int p=0; p<16;p++) {
//			for(int q=0; q<16;q++) {
//				System.out.print(alpha[p][q]+" ");
//			}
//			System.out.println();
//		}
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
   	    int[] substitution = {8,4,2,1,12,6,3,13,10,5,14,7,15,11,9,0}; //UE
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
