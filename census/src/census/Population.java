package census;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Population {
	private class Sum {
		public double Sum2010;
		public double Sum2012;
		Sum(double sum2010, double sum2012){
			
			Sum2010 = sum2010;
			Sum2012 = sum2012;
		}
	}
	private String filename;
	public int filesize=0;
	
	private MinHeap maxpop;
	private MaxHeap minpop;
	private HashMap<String,Sum> cumulative;
	
	public Population(String filename){
		
		this.filename = filename;
		
		maxpop = new MinHeap(5);
		minpop = new MaxHeap(5);
		cumulative = new HashMap<String,Sum>();
		parse();
	}
		private void parse() {
			String linebuf;
			double y2010=0;
			double y2012=0;
			double diff = 0;
			double s2010=0, s2012=0;
			double pc;
			
			String city;
			String state;
		try(BufferedReader reader = new BufferedReader( new FileReader(filename))){
			linebuf=reader.readLine();
			while(reader.ready()){
				
				linebuf=reader.readLine();
				
				String[] values = linebuf.split(",");
				y2010 = Integer.parseInt(values[2]);
				y2012 = Integer.parseInt(values[4]);
				city = values[0].replace("\"", "");
				state = values[1].replace("\"", "");
				if(!cumulative.containsKey(state)){
					s2010 = 0;
					s2012 = 0;
				}
				s2010 += y2010;
				s2012 += y2012;
				cumulative.put(state, new Sum(s2010,s2012));
				
				if(y2010 >= 50000){
					
					filesize++;
					diff = y2012 - y2010;
					
					pc = (diff/y2010)*100.0;
					
					City c = new City(pc,city);
					
						maxpop.insert(c);
						minpop.insert(c);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		public static void main(String[] args){
			String filename = args[0];

			Population p = new Population (filename);
			System.out.println("Highest Cumulative population : ");
			p.highestpopulation();
			System.out.println();
			System.out.println("Shrinking Population: ");
			p.shrinkpopulation();
			System.out.println();
			System.out.println("Highest Growth in Population:");
			p.highestgrowth();
		}

		private void highestgrowth() {
			maxpop.printCity();
			
		}
		private void shrinkpopulation() {
			minpop.printCity();
			
		}
		private void highestpopulation() {
			StateMinHeap highcumulative = new StateMinHeap(5);
			for(Map.Entry<String, Sum> entry : cumulative.entrySet() ){
				Sum s = entry.getValue();
				String state = entry.getKey();
				double growth = ((s.Sum2012-s.Sum2010)/s.Sum2010)*100.0;
				State cumPop = new State(growth,state);
				highcumulative.insert(cumPop);
				
			}
			
			highcumulative.printState();
			
		}
	
}
