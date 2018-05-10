package Project;
import java.util.*;
import java.lang.*;
import java.lang.reflect.Array;
import java.io.*;
import java.util.Random;
class EmergencyVehicleDispatch
{
	static ArrayList<int[]> Available;  //availability list
    //calculating shortest path for vertices not yet visited
    static final int V=13;
    int MDist(int dist[], Boolean Set[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (Set[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    // function to print the solution
    void printSolution(int dist[], int n,ArrayList<Integer> flags,int src, int typ)
    {   Random rand = new Random();
        
        int  nb = rand.nextInt(10) + 1;
    	int min  = 999;
    	int minv = 0;
        int flag = 0;
       // Writer writer = null;
        
        for (int i = 0; i < V; i++)
        {
            	if(dist[i]<min)
            	{
            		
            		min = dist[i];
            		minv = i;
            	}
        }  
        flags.add(minv);
       
        while(flags.size()<V)
        {
        	min = 999;
        for (int i = 0; i < V; i++)
        {
        	  flag = 0;
            	if(dist[i]<min)// To find the closest node with requested emergency vehicle available.
            	{
            		for(int z = 0;z<flags.size();z++)
            		{
            			if (i == flags.get(z))
            			{
            				flag = 1;
            			}         			
            		}
            		if(flag==0)
            		{
            			min = dist[i];
                		minv = i;
            		}
            		
            	}
            }  
        flags.add(minv);
        }
       
        // the loops are to iterate through the availability list and find the vehicle to be dispatched from the shortest distance node
        for (int x = 0;x < flags.size();x++)
        {
        
        int l[] = Available.get(flags.get(x));
        if (l[typ]>=1)
        {   if(typ==1){
            
            System.out.println("Vehicle F"+ x+nb +" Available from "+"'"+flags.get(x)+"'"+"and is on it's way to destination"+" and distance is "+dist[flags.get(x)]+" miles");
            l[typ]--;
            break;}
            if(typ==2){
               
                System.out.println("Vehicle P"+ x+nb +" Available from "+"'"+flags.get(x)+"'"+"and is on it's way to destination"+" and distance is "+dist[flags.get(x)]+" miles");
                l[typ]--;
                break;
            }
            else{
                //PrintWriter writer = new PrintWriter("Vehicle_Allocation.txt", "UTF-8");
                System.out.println("Vehicle A"+ x+nb +" Available from "+"'"+flags.get(x)+"'"+"and is on it's way to destination"+" and distance is "+dist[flags.get(x)]+" miles");
                l[typ]--;
                break;
            }
        }
        else
        {
        	//System.out.println("Vehicle Not Availabe from "+flags.get(x));
        }
        
        }
        }
        
 
    //Dijkstra's algorithm
    void Dijkstra(int graph[][], int src,ArrayList<Integer> flagsguy,int typ)
    {
        int dist[] = new int[V]; 
        Boolean Set[] = new Boolean[V];
 
        // ISetting all othernodes except source to infinite
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            Set[i] = false;
        }
        dist[src] = 0;//the distance to source node is always 0
 
        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++)
        {
            int u = MDist(dist, Set);
            Set[u] = true;
            for (int v = 0; v < V; v++)
                if (!Set[v] && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
 
        //calling print method
        printSolution(dist, V,flagsguy,src,typ);
    }
    //switch case for input of zipcodes.
    int zip(int src){
        int zipp=-1;
        
        switch (src) {
            case 64131:  zipp = 0;
                     break;
            case 64132:  zipp = 1;
                     break;
            case 64133:  zipp = 2;
                     break;
            case 64134:  zipp = 3;
                     break;
            case 64135:  zipp = 4;
                     break;
            case 64136:  zipp = 5;
                     break;
            case 64137:  zipp = 6;
                     break;
            case 64138:  zipp = 7;
                     break;
            case 64139:  zipp = 8;
                     break;
            case 64140: zipp = 9;
                     break;
            case 64141: zipp = 10;
                     break;
            case 64142: zipp = 11;
                     break;
            case 64143: zipp = 12;
                     break;
            default: zipp = 900000000;
                     break;
        }
        return zipp;
    }
    // Driver method
    public static void main (String[] args)
    {
        /* Vehicle avaliablity list*/
        int src,typ,src1;
        Available = new ArrayList<int[]>();
        int i1[] = {0,0,2};
        Available.add(0,i1);
        int i2[] = {1,0,0};
        Available.add(1,i2);
        int i3[] = {1,1,1};
        Available.add(2,i3);
        int i4[] = {2,0,1};
        Available.add(3,i4);
        int i5[] = {1,0,0};
        Available.add(4,i5);
        int i6[] = {0,2,0};
        Available.add(5,i6);
        int i7[] = {0,1,0};
        Available.add(6,i7);
        int i8[] = {3,0,0};
        Available.add(7,i8);
        int i9[] = {0,1,1};
        Available.add(8,i9);
        int i10[] ={1,0,0};
        Available.add(9,i10);
        int i11[] ={1,0,1};
        Available.add(10,i11);
        int i12[] ={0,0,0};
        Available.add(11,i12);
        int i13[] ={0,0,3};
        Available.add(12,i13);
        ArrayList<Integer> flags = new ArrayList<Integer>();
        //The graph the zipcode nodes converted into multi dimentional array
       int graph[][] = new int[][]{
                                {0, 0, 0, 0, 6, 0, 0, 10, 8, 0, 0, 0, 0},
                                {0, 0, 7, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 7, 0, 0, 0, 9, 0, 0, 0, 6, 0, 0, 0},
                                {0, 11, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
                                {6, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 9, 2, 6, 0, 7, 0, 0, 0, 0, 1, 0},
                                {0, 0, 0, 0, 0, 7, 0, 0, 5, 0, 0, 0, 3},
                                {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {8, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0},
                                {0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 2, 4, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 9, 0},
                                {0, 0, 0, 0, 0, 1, 0, 0, 0, 4, 9, 0, 0},
                                {0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0},
                                };
        EmergencyVehicleDispatch t = new EmergencyVehicleDispatch();
        Scanner in = new Scanner(System.in);
        while(true)
        {
        System.out.println("\n\nenter Source zipcode of avaliable \n1.64131 2.64132 3.64133 4.64134 5.64135 6.64136 \n7.64137 8.64138 9.64139 10.64140 11.64141 12.64142 13.64143");
        src=in.nextInt();
        src1=t.zip(src);
        System.out.println("enter type fire:1 Police:2 Ambulance:3");
        typ=in.nextInt()-1;
        t.Dijkstra(graph,src1,flags,typ);
        }
    }
}