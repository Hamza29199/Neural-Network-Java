package arkangel;

import java.util.*;


public class Neuron_Object {

	static double leakiness_param = 0.08; //the value whch gets multiplied with the input value < 0 in 'leaky' ReLU function

	double output; //what any neuron produces after processing an input
	
	ArrayList<Connections> synapses; //synapses are the connections between neurons
	
	public Neuron_Object() {
		
		output =0;
		
		synapses = new ArrayList<Connections>();
	}
	
	public Neuron_Object(double output) {
		
		this.output = output;
		
		synapses = new ArrayList<Connections>(); //synpases are connections b/w neurons
	}
	
	
	//this method allow a neuron to multiply all incoming inputs
	//with corresponding weights and sum it all up to produce its own output
	
	public void output_neuron_guess() {
		
		double sum =0;
		
		for(int i=0; i < synapses.size(); i++) {
			
			//synapses is an ArrayList of Connection objects
			
			Connections c = synapses.get(i);
			
			//initializing the neuron "from" where the connection starts and
			//the neuron "to" which it goes
			Neuron_Object from = c.from;
			Neuron_Object to = c.to;
			
			//if the current neuron object is a "to" neuron i.e. connection going to it,
			//then the weight of that connection times its input (output of the "from neuron")
			//gets added to the sum i.e. which is the final output of a "to" neuron
			if(this == to) {
				
				sum+=(c.weight * from.output);
				
				
			}
			
		}
		
		//we need the output restricted between -1 and 1 so we use a non-linear function
		// to "squash" any output
		output = tanh_func(sum);
	}
	
	
public void hidden_neuron_guess() {
		
		double sum =0;
		
		for(int i=0; i < synapses.size(); i++) {
			
			//synapses is an ArrayList of Connection objects
			
			Connections c = synapses.get(i);
			
			//initializing the neuron "from" where the connection starts and
			//the neuron "to" which it goes
			Neuron_Object from = c.from;
			Neuron_Object to = c.to;
			
			//if the current neuron object is a "to" neuron i.e. connection going to it,
			//then the weight of that connection times its input (output of the "from neuron")
			//gets added to the sum i.e. which is the final output of a "to" neuron
			if(this == to) {
				
				sum+=(c.weight * from.output);
				
				
			}
			
		}
		
		
		output = leaky_relu_func(sum);
	}


	double tanh_func(double y) {
		
		return (( Math.exp(y) - Math.exp(-y))/( Math.exp(y) + Math.exp(-y)));
	}
	
	
	
	static double leaky_relu_func(double y) {
		
		return y >=0 ? y : y*leakiness_param;
	}
	
	
	//returning the array of connections between neurons
	ArrayList<Connections> getConnections() {
		
		return synapses;
		
	}
	
	
	void addConnections(Connections conn) {
		
		synapses.add(conn); //adding a new connection
		
	}

}
