package census;


public class MaxHeap extends Heap {
	private  City[] heap;
	private int maxSize;
	private int size;
	
	public MaxHeap(int maxSize){
		this.maxSize = maxSize+1;
		heap = new City[10];
		size = 0;
	
		for(int i = 1; i<= maxSize+1; i++){
			heap[i] = new City(-10000.0,null);
		}
	}
	
	
	private void maxHeapify(int pos){
		int left = left(pos);
		int right = right(pos);
		int largest = pos;
		
		if(left <= size && heap[left].pcgrowth > heap[largest].pcgrowth){
			largest = left;
		}
		
		if(right <= size && heap[right].pcgrowth > heap[largest].pcgrowth){
			largest = right;
		} 
		
		
		if (largest != pos){
			swap(largest, pos);
			maxHeapify(largest);
		}
	}
	
	
	
	public void insert(City c){
		if(size == maxSize) extractMax();
		heap[++size] = c;
		int curr = size;
		while(curr>1 && (heap[curr].pcgrowth > heap[parent(curr)].pcgrowth)){
			swap(parent(curr),curr);
			curr = parent(curr);
		}
	}
	
	public City extractMax(){
		City c = heap[1];
		swap(1,size);
		size--;
		maxHeapify(1);
		return c;
	}
	protected void swap(int pos, int npos) {
		City temp = heap[pos];
		heap[pos] = heap[npos];
		heap[npos] = temp;
		
	}

	public void printCity() {
		extractMax();
		for(int i = 1; i<=5; i++){
			System.out.printf("%s : %.2f\n",heap[i].city,heap[i].pcgrowth);
		}
		
	}
	
	
	
}
