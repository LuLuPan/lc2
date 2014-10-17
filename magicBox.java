public class Solution { 
    private void subsets(int n, int start, int steps, ArrayList<Integer> path, HashMap<HashSet<Integer>, Integer> indicies) {
    	if (steps == n + 1) {
    	    if (!indicies.containsKey(path))
    	    	indicies.put(new HashSet<Integer>(path), 0);
    	    return;
    	}
    	
    	subsets(n, start + 1, steps + 1, path, indicies);
    	
    	path.add(start);
    	subsets(n, start + 1, steps + 1, path, indicies);
    	path.remove(path.size() - 1);
    }
    
    private void generateIndicies(int n, HashMap<HashSet<Integer>, Integer> indicies) {
    	ArrayList<Integer> path = new ArrayList<Integer>();
    	subsets(n, 0, 0, path, indicies);	
    }
    
    private boolean isHope(HashSet<Integer> set, String line) {
    	char target = line.charAt(0);
    	if (set.contains(0)) {
    		target = line.charAt(0) == 'T' ? 'P' : 'T';
    	}
    		
    	for (int i = 1; i < line.length(); i++) {
    		if (set.contains(i)) {
    			if (target == line.charAt(i))
    				return false;
    		} else {
    			if (target != line.charAt(i))
    				return false;
    		}
    	}
    	return true;
    }
    
    public void MagicBox() throws IOException {
    	BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
    	int m = 5;
    	int n = 3;
    	
    	HashMap<HashSet<Integer>, Integer> indices = new HashMap<HashSet<Integer>, Integer>();
    	generateIndicies(n / 2, indices);
    	HashSet<Integer> empty = new HashSet<Integer>();
    	indices.remove(empty);
    	int max = 0;

    	String[] input = {"TPP", "PTP", "TPP", "PTP", "TPT"};
    	for (String line : input) {
    		for (HashSet<Integer> indexList : indices.keySet()) {
    		    if (isHope(indexList, line)) {
    		    	indices.put(indexList, indices.get(indexList) + 1);
    		    	max = Math.max(max, indices.get(indexList));
    		    }
    		}
    	}
    	
    	System.out.println(max);
    }
}
    