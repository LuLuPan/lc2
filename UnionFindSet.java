import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class UnionFindSet {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int totalCases = Integer.parseInt(br.readLine());
		
		WeightedUnionFindSet uf = null;
		String[] pair = null;
		while (totalCases > 0) {
			pair = br.readLine().split("\\s+");
			// based on 1, not 0
			uf = new WeightedUnionFindSet(Integer.parseInt(pair[0]) + 1);
			// construct UF
			int pairNum = Integer.parseInt(pair[1]);
			while (pairNum > 1) {
				pair = br.readLine().split("\\s+");
				uf.union(Integer.parseInt(pair[0]), Integer.parseInt(pair[1]));
				pairNum--;
			}
			
			pair = br.readLine().split("\\s+");
			if (uf.find(Integer.parseInt(pair[0])) != uf.find(Integer.parseInt(pair[1])))
					out.println("Not connected");
			else
				    out.println("Connected");
			
			totalCases--;
		}
		out.flush();
	}
}


/*
 * Time complexity
 * WeightedUFS: Construct O(N)
 *             find, union: O(logN)
 * WeightedUFS with Path compression:
 *              O(N) + O(~1)
 */
class WeightedUnionFindSet {
	private int[] id;
	private int[] size;
	private int count;
	
	public WeightedUnionFindSet(int N) {
		this.id = new int[N];
		this.size = new int[N];
		this.count = N;
		for (int i = 0; i < N; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}
	
	/* 
	 * find and return the parent id
	 * 
	 *    A
	 *       \
	 *        B
	 *          \
	 *           C
	 *   =>
	 *    A
	 *   / \
	 * C     B        
	 */
	public int find(int p) {
		while (p != id[p]) {
			// path compression
			id[p] = id[id[p]];
			// traversal towards parent
			p = id[p];
		}
		
		return p;
	}
	
	/*
	 * Compare two union into one
	 * if they are not connected
	 */
	public void union(int p, int q) {
		int pp = find(p);
		int pq = find(q);
		if (pp == pq) return;
		
		// combine two union with weighted
		// small tree combined into large tree
		// to min depth
		if (size[pp] > size[pq]) {
			id[pq] = pp;
			size[pp] += size[pq];
		}
		else {
			id[pp] = pq;
			size[pq] += size[pp];
		}
		
		// shrink unions size
		count--;
	}
	
	public int count() {
		return this.count;
	}
}
