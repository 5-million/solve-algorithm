import java.util.Scanner;

/**
 * D3
 * 5215 - 햄버거 다이어트
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT
 */
public class Solution {

	static class Ingredient {
		int taste;
		int calorie;
		
		public Ingredient(int taste, int calorie) {
			this.taste = taste;
			this.calorie = calorie;
		}
	}
	
	private static int ingredientCnt, maxCalorie;
	private static Ingredient[] ingredients;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int tc = 1; tc<=t; tc++) {
			ingredientCnt = sc.nextInt();
			maxCalorie = sc.nextInt();
			
			ingredients = new Ingredient[ingredientCnt];
			for (int i=0; i<ingredientCnt; i++) {
				ingredients[i] = new Ingredient(sc.nextInt(), sc.nextInt());
			}
			
			int answer = rec(0, 0, 0);
			System.out.printf("#%d %d\n", tc, answer);
		}
	}
	
	private static int rec(int idx, int totalCalorie, int score) {	
		if (idx == ingredientCnt) return score;
		
		int ret = score;
		int c = ingredients[idx].calorie + totalCalorie;
		if (c <= maxCalorie) {
			ret = Math.max(ret, rec(idx + 1, c, score + ingredients[idx].taste));
		}
		ret = Math.max(ret, rec(idx + 1, totalCalorie, score));
		
		return ret;
	}
}
