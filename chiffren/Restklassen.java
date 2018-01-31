package chiffren;
import java.math.BigDecimal;




public class Restklassen {

static int n=26;

public Restklassen(int k){
	n=k;
}

public static void main(String[] args) {
// TODO Auto-generated method stub
	test();

}

public static void modulAndern(int m){
	n = m;
}



private static void test(){
	//System.out.println("Test Inverses");
	Restklassen test = new Restklassen(2);
	//System.out.println(test.inverses(53));

	//System.out.println("Test Matrixaddition & -Multiplikation");
	int[][] a = {{1,0},
	     {0,0}};
	int[][] b = {{2,0},
	     {3,0}};
	int[][] c = {{1,2},
	         {3,4},
	             {5,6}};
	int[][] d = {{1,2,3},
	         {4,5,6}};
	
	
	//printMatrix(matrixaddition(a,b));
	//printMatrix(matrixmultiplikation(c,d));
	
	int[][] det1 = {{0,1,2},{3,2,1},{1,1,0}};
	int[][] det3 = {{3,7,3,0},{0,2,-1,1},{5,4,3,2},{6,6,4,-1}};
	int[][] det2 = {{1,3,5},{-1,2,0},{4,2,-3}};
	//System.out.println(determinante(det3));
	
	double[][] inv1 = {{2,-1,0},{1,2,-2},{0,-1,1}};
	//System.out.println(determinante(inv1));
	printMatrix(inverseMatrix(inv1));
	
}

private static BigDecimal[][] tauscheZeile(BigDecimal[][] a, int i, int j){
	BigDecimal[] z = a[i];
	a[i] = a[j];
	a[j] = z;
	return a;
}

private static BigDecimal[][] multpliziereSkalar(BigDecimal[][] a, BigDecimal skalar, int zeile){
	for(int i=0; i<a[0].length; i++){
		a[zeile][i]=a[zeile][i].multiply(skalar);
	}	
	return a;
}

private static BigDecimal[][] subtrahiereZeile(BigDecimal[][] a, int i, int j){
	for(int k=0; k<a[0].length; k++){
		a[i][k].subtract(a[j][k]);
	}
	return a;
}


public static double[][] inverseMatrix(double[][] a){
		BigDecimal backup[][] = new BigDecimal[a.length][a.length];
		for(int q=0; q<a.length; q++){for(int u=0; u<a.length; u++){backup[q][u].valueOf(a[q][u]);}}
		
	if(determinante(a)==0)System.out.println("Matrix besitzt keine Inverse!");
	else{
		BigDecimal[][] neu = new BigDecimal[backup.length][backup.length];
		for(int i=0; i<neu.length;i++){neu[i][i]=BigDecimal.ONE;}
		
		//Gau�-Algorithmus
		for(int n=0; n<backup.length; n++){
			//Man w�hlt die erste Spalte von links, in der mindestens ein von Null verschiedener Wert steht.
			int s;
			s2:
			for(s=n; s<backup[0].length; s++){
				for(int z=n; z<backup.length; z++){
					if(backup[z][s] != BigDecimal.ZERO)break s2;
				}
			}
			//Ist die oberste Zahl der gew�hlten Spalte eine Null, so vertauscht man die erste Zeile mit einer anderen Zeile, in der in dieser Spalte keine Null steht.
			if(backup[n][s] == BigDecimal.ZERO){
				int z;
				for(z=n; z<backup.length; z++){
					if(backup[n][s] != BigDecimal.ZERO)break;
				}
				backup = tauscheZeile(backup, n, z);
				neu = tauscheZeile(neu, n, z);
			}
			//Man dividiert die erste Zeile durch das nun oberste Element der gew�hlten Spalte.
			BigDecimal skalar = (BigDecimal.ONE.divide(backup[n][s]));
			backup = multpliziereSkalar(backup, skalar, n); 
			neu = multpliziereSkalar(neu, skalar, n);
			//Man subtrahiert entsprechende Vielfache der ersten Zeile von den darunterliegenden Zeilen mit dem Ziel, dass das erste Element jeder Zeile (au�er der ersten) Null wird.
			for(int z=n+1; z<a.length; z++){
				skalar = backup[n][n].divide(backup[z][n]);
				if(backup[z][n]== BigDecimal.ZERO || Double.isInfinite(skalar.doubleValue()))continue;
				backup = subtrahiereZeile(multpliziereSkalar(backup, skalar, z), z, n);
				neu = subtrahiereZeile(multpliziereSkalar(neu, skalar, z), z, n);
			}	
		}
		//System.out.println("2 Schritt");
			//bringe die eintraege ueber der hauptdiagonale auch auf null
			for(int s=backup.length-1; s>0; s--){
				for(int z=backup.length-2; z>=0; z--){
					if(backup[z][s] != BigDecimal.ZERO){
						BigDecimal skalar = backup[s][s].multiply(backup[z][s]);
						if(backup[z][s]==BigDecimal.ZERO || Double.isInfinite(skalar.doubleValue()))continue;
						
						//aus irgendeinem Grund wird z Zeile ueberschrieben => schlechte loesung (backup)
							BigDecimal[] zeile_a_s = new BigDecimal[backup.length];
							for(int i=0; i<zeile_a_s.length; i++)zeile_a_s[i] = backup[s][i]; 
							BigDecimal[] zeile_n_s = new BigDecimal[neu.length];
							for(int i=0; i<zeile_n_s.length; i++)zeile_n_s[i] = neu[s][i];
							
						
							backup = subtrahiereZeile(multpliziereSkalar(backup, skalar, s), z, s);
						neu = subtrahiereZeile(multpliziereSkalar(neu, skalar, s), z, s);
						
						backup[s] = zeile_a_s;
							neu[s] = zeile_n_s;
					}
				}
			}
			double[][] neu_double = new double[neu.length][neu.length];
			for(int z=0; z<neu.length; z++){
				for(int s=0; s<neu.length; s++){
					neu_double[z][s] = neu[z][s].doubleValue();
				}	
			}
			return neu_double;
	}
	return null;
}



public static double determinante(double[][] a){
	if(a.length != a[0].length) System.err.println("Matrix nicht quadratisch!");
	else if(a.length == 2 && a[0].length == 2) {
		return (a[0][0] * a[1][1]) - (a[0][1] * a[1][0]);  
	}
	else {
		int sum = 0;
		for(int i=0; i < a[0].length; i++){
			//erstelle Submatrix
			int zeile_ = 0, spalte_ = 0;
			double[][] a_ = new double[a.length-1][a.length-1];
			for(int zeile=0; zeile < a.length; zeile++){
				if(zeile == 0)continue;
				for(int spalte=0; spalte < a.length; spalte++){
					if(spalte == i){continue;}
					else {
						a_[zeile_][spalte_] = a[zeile][spalte];;
					}
					spalte_++;
				}
				spalte_ = 0;
				zeile_++;
			}
			sum += (int) Math.pow(-1,(1+i+1))*a[0][i]*determinante(a_);
			//addition(sum, (multiplikation((int) Math.pow(-1,(1+i+1)), multiplikation(a[0][i], determinante(a_)))));
		}
		a = a;
		return sum;
	}
	return 0;
}

public static double multiplikation(double a, double b){
	return (a*b)%n;
}

public static double addition(double a, double b){
	return (a+b)%n;
}

public static void printMatrix(double[][] a){
  for(int z=0; z<a.length; z++){
   for(int s=0; s<a[0].length; s++){
   System.out.print(a[z][s]+" ");
   }
   System.out.println("");
  }
}

public static double[][] matrixaddition(double[][] a, double[][] b){
   int zeilen = 0;
   if(b.length == a.length){zeilen = a.length;}
   else System.err.println("Anzahl der Zeilen unterschiedlich!");

   int spalten = 0;
   if(b[0].length == a[0].length)spalten = a.length;
   else System.err.println("Anzahl der Spalten unterschiedlich!");

   double[][] rueckgabe = new double[zeilen][spalten];
   		for(int z=0; z<zeilen; z++){
   		for(int s=0; s<spalten; s++){
   			rueckgabe[z][s] = addition(a[z][s], b[z][s]);
   		}
   }

   return rueckgabe;
}

public static double[][] matrixmultiplikation(double[][] a, double[][] b){
   int zeilen = 0;
   int spalten = 0;
   int anz_summanden = 0;
   //Kontrolle: #Spalten(a) == #Zeilen(b)

   if(a[0].length == b.length){anz_summanden = a[0].length; spalten = b[0].length; zeilen = a.length;}
   else System.err.println("Matrixgr��en unterschiedlich!");

   double[][] rueckgabe = new double[zeilen][spalten];

   for(int z=0; z<zeilen; z++){
	   for(int s=0; s<spalten; s++){
		   for(int s_sum=0; s_sum<anz_summanden; s_sum++){
			  rueckgabe[z][s] =  addition(rueckgabe[z][s], multiplikation(a[z][s_sum], b[s_sum][s]));
		   }
	   }
   }
   return rueckgabe;
}

public int inverses(int a, int m){
	int i=0;
	int b=m;
	
	int r_im1 =Integer.MAX_VALUE;
	int r_i=Integer.MAX_VALUE;
	int r_ip1=Integer.MAX_VALUE;
	
	int d_ip1=Integer.MAX_VALUE;
	int d_i=Integer.MAX_VALUE;
	
	int p_im2=Integer.MAX_VALUE;
	int p_im1=Integer.MAX_VALUE;
	int p_i=Integer.MAX_VALUE;
	
	int q_im2=Integer.MAX_VALUE;
	int q_im1=Integer.MAX_VALUE;
	int q_i=Integer.MAX_VALUE;
	
	do{
		if(i==0){
			p_i=1; q_i=0;
		}
		else if(i==1){
			p_im1 = p_i; q_im1 = q_i;
			p_i=0; q_i=1;
			
			r_im1 = b;
			r_i = a;
			d_i = d_ip1;
			d_ip1 = (int)(r_im1/r_i);//maxFactor(r_im1,r_i);
			r_ip1 = r_im1-d_ip1*r_i;
		}
		else if (i>1){
			r_im1 = r_i;
			r_i = r_ip1;
			d_i = d_ip1;
			d_ip1 = (int)(r_im1/r_i);//maxFactor(r_im1,r_i);
			r_ip1 = r_im1-d_ip1*r_i;
			
			p_im2 = p_im1; q_im2 = q_im1;
			p_im1 = p_i; q_im1 = q_i;
			p_i=p_im2 - d_i*p_im1; q_i=q_im2 - d_i*q_im1;
		}
		i++;
		/*
		System.out.println("r_im1: "+r_im1+", "+"r_i: "+r_i+", "+"r_ip1: "+r_ip1);
		System.out.println("d_ip1: "+d_ip1+", "+"d_i: "+d_i);
		System.out.println("q_im2: "+q_im2+", "+"q_im1: "+q_im1+", "+"q_i: "+q_i);
		System.out.println("p_im2: "+p_im2+", "+"p_im1: "+p_im1+", "+"p_i: "+p_i);
		System.out.println("\n");
		*/
		if(r_i < 1)return Integer.MIN_VALUE;
	
	}while(r_i != 1);
	//Test
	if(p_i*b + q_i*a != 1){
		System.out.println("FEHLER");
	};
	while(q_i < 0)q_i += b;
		return q_i;
	}
}

