package chiffren;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Primzahltests {

	public static void main(String[] args) {
	System.out.println("PFT: ");
		ArrayList<Integer> PFT  = berechnePFT_n(221);
		for(int i: PFT)System.out.println(i);
	System.out.println("MRT: ");
		ArrayList<Integer> MRT  = berechen_MRT(221);
		for(int i: MRT)System.out.println(i);
	System.out.println("J: ");
		ArrayList<Integer> J  = J(221);
		for(int i: J)System.out.println(i);
	}

	//Hilfsfkt)
		//TODO: testen
	    public static ArrayList<Integer> reverse(ArrayList<Integer> e){
	    	ArrayList<Integer> r = new ArrayList<Integer>();
	        for(int p=e.size()-1; p>=0; p--){
	        	r.add(e.get(p));
	        }
	    	return r;
	    }

	    //TODO: testn
	    public static ArrayList<Integer> bin(int y){
	    	ArrayList<Integer> e = new ArrayList<Integer>(); 
	       while(y > 0){
	          if(y%2 ==0){e.add(0); y /= 2;}
	          else {e.add(01); y--; y /= 2;}
	       }
	       e = reverse(e);
	       return e;
	    }

	    //TODO: testn
	    static int HornerPot(int a, int n, int m){
		   int z =a;
	       ArrayList<Integer> e = bin(a); 
	       for(int i = e.size()-2; i>=0; i--){
	       	 z = (z*z)* (int) Math.pow(a, e.get(i)) % m; 
	       }
	       return(z);
	    }
	   
	    static int ggT(int zahl1, int zahl2) {
	    	if(zahl2 == 0 || zahl1 == 0)return 0;
	    	if(zahl2 == 1 || zahl1 == 1)return 1;

		   while (zahl2 != 0) {
		     if (zahl1 > zahl2) {
		       zahl1 = zahl1 - zahl2;
		     } else {
		       zahl2 = zahl2 - zahl1;
		     }
		   }
		   return zahl1;
		 }
	   
	    static ArrayList<Integer> z_nStern(int n){
	       ArrayList<Integer> Z_nStern =  new ArrayList<Integer>();
	       for(int i=0; i<=n-1; i++){
	    	   int ggT = ggT(i,n);
	    	   if(ggT == 1) Z_nStern.add(i);
	       }
	       return Z_nStern;
	   }
	 
	// P^FT_n = {a aus Z_n*: a^n-1 ==n 1}

	    static ArrayList<Integer> berechnePFT_n(int n){
		    ArrayList<Integer>Z_nStern = z_nStern(n);
			ArrayList<Integer> PFT_n =  new ArrayList<Integer>(); 
			for(int a:  Z_nStern){
				 if(Math.pow(a, n-1)%n == 1)PFT_n.add(a);
		    }
		return PFT_n;
	}
		  
	

	//P^MRT_n = {a aus Z_n*: a^n-1 == 1}
	
	    static ArrayList<Integer> berechen_MRT(int n){
			ArrayList<Integer> PMRT = new ArrayList<Integer>();
			ArrayList<Integer> Z_nStern = z_nStern(n);
				for(int a: Z_nStern){
					if(MRT(a,10)) PMRT.add(a);	
				}
			return PMRT;
	
	    }
		
	

	    static boolean MRT(int n,  int k){
			if(n==2)return true;
			else if(n < 2) return false;
			ArrayList<Integer> e = bin(n-1);
			for(int j=0; j<k; j++){
				int a = (int) (Math.random()*(n-1));
				int z = a;
				for(int i=e.size()-1; i>0; i--){
					int y = z;
					z = (z*z) % n;
					if(z%n == 1 && y%n != 1 && y%n != n-1)return false;
					if(e.get(i) == 1) z=(z*a)%n;
				}
				if(z%n != 1) return false;
			}
			return true;
	    }


	static int[] finde_u_m(int n){
		int[] res= new int[2];
		for(int m=1; m<n-1; m++) {
			for(int u=1; u<n-1; u++) {
				if(n-1 == Math.pow(2, m)*u % n){
					res[0]= u;
					res[1] = m;
					return res;
				} 
			}
		}
		return null;	 
	}

	static int finde_j(int u,  int m,  int n, ArrayList<Integer> Z){
		int max=1;
		for(int i=1; i<m; i++){
			for(int a: Z){
				if(Math.pow(a, Math.pow(2, i)*u) % n == n-1)max =i;
			}
		}
		return max;
	}


	//4) J_n = {a aus Z_n* | a^((2^j)*u) ==n +-1}
	static ArrayList<Integer> J(int n){
		ArrayList<Integer> Z_nStern = z_nStern(n);
		ArrayList<Integer> J = new ArrayList<Integer>();
		int[] um= finde_u_m(n); int u=um[0];int m = um[1];
		int j = finde_j(u, m, n, Z_nStern);
		for(int a: Z_nStern){
				int l = (int) (Math.pow(a, Math.pow(2, j)*u) %n);
				if(l==1 || l==n-1) J.add(a);
		}
	    return J;
	}


}
//n ungerade
//j = max{0<=i<=m | es ex. a aus Z_n*: a^(2^i)*u ==n -1}
//n-1 = 2^m*u,  u ungerade
//J_n = {a aus Z_n* | a^(2^i)*u ==n -1}

//a)n = 221, 
//berechne P^FT_,  P^MRT_n und J_n


