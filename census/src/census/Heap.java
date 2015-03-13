package census;


public class Heap {
	private  City[] heap;
	private int maxSize;
	private int size;
	
	
	
	protected Boolean isLeaf(int pos){
		if(pos > size/2 && pos <=size){
			return true;
			
		}
		return false;
	}
	
	
	protected int parent(int pos){
		return pos/2;
	}
	
	protected int left(int pos){
		return 2*pos;
	}
	
	protected int right(int pos){
		return 2*pos+1;
	}
	
	
	
	public int size(){
		return size;
	}
	
	
}
