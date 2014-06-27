import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;
	class assignment7q2{
		
		
		public static void main (String[] args) {
			Runnable p0,p1,p2,p3,p4;
			
			Semaphore chopStick[] = new Semaphore[5];
				
				for (int j=0;j<5;j++){
					chopStick[j]= new Semaphore(1);
				}
					
			p0=new Philosopher(0,chopStick);
			p1=new Philosopher(1,chopStick);
			p2=new Philosopher(2,chopStick);
			p3=new Philosopher(3,chopStick);
			p4=new Philosopher(4,chopStick);
			
			new Thread(p0).start();
			new Thread(p1).start();
			new Thread(p2).start();
			new Thread(p3).start();
			new Thread(p4).start();	
	
	
		}
	
	}


	class Philosopher implements Runnable{
		
		Semaphore chopStick [];//chopsticks
		private int phil;//philosopher id
		
		public Philosopher(int p, Semaphore c[]){
			phil=p;
			chopStick=c;
			
			
		}
		public void run(){
			while(phil<4){				
							
				try {
					chopStick[phil].acquire();	//get left chopstick
					chopStick[(phil+1)].acquire();//get right chopstick
				} catch (InterruptedException e) {}					
				
				eat(phil);			
				chopStick[phil].release();	//put down left chopstick
				chopStick[(phil+1)].release();//put down right chopstick
				think(phil);			
			
		
			}
			
			while(phil==4){
								
				try {
					chopStick[0].acquire();//get right chopstick
					chopStick[4].acquire();	//get left chopstick
				} catch (InterruptedException e1) {}			
					
				eat(phil);				
				chopStick[0].release();//put down right chopstick			
				chopStick[4].release();	//put down left chopstick
				think(phil);		
				
			}
		
		}
		
		private void think(int phil){
		System.out.println ("Philosophor "+phil+" is thinking");
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){}
	}
	
	private void eat(int phil){
		System.out.println ("Philosophor "+phil+" is eating");
			try{
				Thread.sleep(500);
			}catch(InterruptedException e){}
	}
}
