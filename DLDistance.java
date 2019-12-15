
public class DLDistance {

		// Computes the Damerau-Levenstein distance of s1 and s2
		public static int computeDistance(String s1, String s2) {
			
			if ((s1 == null) && (s2 == null)) 
				return 0;
			
			if ((s1 == null && s2 != null ) || (s2 == null && s1 != null)) {
				return Integer.MAX_VALUE;
			}
			
			int d[][] = new int[s1.length() + 1][s2.length() + 1];
			
			// fill the first row and column of the distance matrix
			for (int i = 0; i <= s1.length(); i++) {
				d[i][0] = i;
			}
			
			for (int i = 0; i <= s2.length(); i++) {
				d[0][i] = i;
			}
			
			// fill the second row and column
			// here transposition operations plays no role 
			for (int i = 1; i <= s1.length(); i++) {
				if (s1.charAt(i - 1) == s2.charAt(0)) {
					d[i][1] = d[i - 1][0];
				} else {
					d[i][1] = 1 + min3(d[i - 1][1], d[i][0], d[i - 1][0]);
				}
				 
			}
			
			for (int i = 1; i <= s2.length(); i++) {
				if (s1.charAt(0) == s2.charAt(i - 1)) {
					d[1][i] = d[0][i - 1];
				} else {
					d[1][i] = 1 + min3(d[0][i], d[1][i - 1], d[0][i - 1]);
				}
				
			}
			
			// filling out the rest of the matrix
			for (int i = 2; i <= s1.length(); i++) {
				for (int j = 2; j <= s2.length(); j++) {
					if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
						d[i][j] = d[i - 1][j - 1];
					} else {
						if (d[i][j] == d[i - 2][j - 2]) {
							d[i][j] = 1 + min4(d[i - 2][j - 2], d[i - 1][j], d[i][j - 1], d[i - 1][j - 1]);
						} else {
							d[i][j] = 1 +  min3(d[i - 1][j], d[i][j - 1], d[i - 1][j - 1]);
						}
					}	
				}
			}
			
			printMatrix(d, s1.length(), s2.length());
			return d[s1.length()][s2.length()];
			
		}
		
		private static int min3(int a, int b, int c) {
			if (a < b) {
				if (a < c)
					return a;
				else 
					return c;
			} else {
				if (b < c)
					return b;
				else 
					return c;
			}
		}
		 
		
		private static int min4(int a, int b, int c, int d) {
			int tmpMin = min3(a, b, c);
			if (d < tmpMin) 
				return d;
			else 
				return tmpMin;
		}
		
		private static void printMatrix(int[][] d, int n, int m) {
			//System.out.println(m + " " + n);
			for (int i = 0; i <= n; i++) {
				for (int j = 0; j <= m; j++) {
					System.out.print(d[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		public static void main(String [] args) {
			System.out.println(computeDistance("jedan dva", "jedan tri tri tri dva"));
		}
}
 