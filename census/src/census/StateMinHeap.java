package census;


public class StateMinHeap extends Heap {
	private  State[] heap;
	private int maxSize;
	private int size;
	
	public StateMinHeap(int maxSize){
		this.maxSize = maxSize+1;
		size = 0;
		heap = new State[10];
		for(int i = 1; i<= maxSize+1; i++){
			heap[i] = new State(Double.MAX_VALUE,null);
		}
	}
	
	
	private void minHeapify(int pos){
		int left = left(pos);
		int right = right(pos);
		int smallest = pos;
		
		if(left <= size && heap[left].pcgrowth < heap[smallest].pcgrowth){
			smallest = left;
		}
		
		if(right <= size && heap[right].pcgrowth < heap[smallest].pcgrowth){
			smallest = right;
		} 
		
		
		if (smallest != pos){
			swap(smallest, pos);
			minHeapify(smallest);
		}
	}
	
	
	
	public void insert(State c){
		if(size == maxSize) extractMin();
		heap[++size] = c;
		
		int curr = size;
		
		
		while(curr>1  && (heap[curr].pcgrowth < heap[parent(curr)].pcgrowth)){
			
			swap(parent(curr),curr);
			curr = curr/2;
		}
		
		
	}
	
	public State extractMin(){
		State c = heap[1];
		swap(1,size);
		--size;
		minHeapify(1);
		return c;
	}


	protected void swap(int pos, int npos) {
		State temp = heap[pos];
		heap[pos] = heap[npos];
		heap[npos] = temp;
		
	}

	
	public void printState() {
		extractMin();
		for(int i = 1; i<=5; i++){
			System.out.printf("%s : %.2f\n",heap[i].state,heap[i].pcgrowth);
		}
		
	}
	
	
	
}
