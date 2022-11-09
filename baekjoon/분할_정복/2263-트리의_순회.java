import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * G2
 * 2263 - 트리의 순회
 * https://www.acmicpc.net/problem/2263
 */
public class Main {
	
	private static int[] preorder, inorder, postorder;
	private static int idx;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		preorder = new int[n];
		inorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		postorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		dfs(0, n - 1, 0, n - 1);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int num : preorder) {
			bw.write(num + " ");
		}
		bw.flush();
	}

	private static void dfs(int iol, int ior, int pol, int por) {
		if (iol <= ior && pol <= por) {
			preorder[idx++] = postorder[por];
			
			int rootIndex = getIndex(inorder, postorder[por]);
			
			dfs(iol, rootIndex - 1, pol, pol + rootIndex - iol - 1);
			dfs(rootIndex + 1, ior, pol + rootIndex - iol, por - 1);
		}
	}

	private static int getIndex(int[] strArr, int target) {
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i] == target) {
				return i;
			}
		}
		return -1;
	}
}
