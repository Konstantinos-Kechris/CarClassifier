//Kexris Kwnstantinos 3150071
//Nikos Koulos 3150079

import java.io.File;
import java.util.Scanner;
import java.util.*;
import java.util.StringTokenizer;

public class NaiveBayesClassifier {

	public static void main(String [] args) {
	
		System.out.println("Welcome to our program!");
		System.out.println("Please read the report.pdf before you proceed!");
		System.out.println("********Bayes Training Procedure********");
		
		int counter = 0;
		int i = 0;
		int count = 0;
		
		try {

            Scanner input = new Scanner(System.in);

            File file = new File("train.txt");

            input = new Scanner(file);

            while (input.hasNextLine()) {

				String line = input.nextLine();
				counter++;	
					
			}

            input.close();

        } catch (Exception ex) {
		
            ex.printStackTrace();
			
        }

		String[] bying = new String[counter];
		String[] maint = new String[counter];
		int[] doors = new int [counter];
		int[] persons = new int [counter];
		String[] lug_boot = new String [counter];
		String[] safety = new String [counter];
		String[] Class = new String[counter];
		
		
		for (int k = 0; k < counter; k++) {
		
			bying[k] = null;
			maint[k] = null;
			doors[k] = 0;
			persons[k] = 0;
			lug_boot[k] = null;
			safety[k] = null;
			Class[k] = null;
		
		}

		try {

            Scanner input = new Scanner(System.in);

            File file = new File("train.txt");

            input = new Scanner(file);


            while (input.hasNextLine()) {
				String line = input.nextLine();
				StringTokenizer token1 = new StringTokenizer(line, ",");
				count = 0;
				while(token1.hasMoreTokens()) {
				
					String line1 = token1.nextToken();
					
					if (count == 0) {
					
						bying[i] = line1;
					
					} else if (count == 1) {
					
						maint[i] = line1;
					
					} else if (count == 2) {
						
						if(line1.equals("5more")) {
							
							doors[i] = 5;
						
						}else {
						
							doors[i] = Integer.parseInt(line1);
							
						}
					
					} else if (count == 3) { 
						
						if(line1.equals("more")) {
						
							persons[i] = 5;
						
						}else {
						
							persons[i] = Integer.parseInt(line1);
							
						}
					
					} else if (count == 4) {
					
						lug_boot[i] = line1;
					
					} else if (count == 5) {
					
						safety[i] = line1;
					
					} else {
					
						Class[i] = line1;
					
					}
					
					count++;
				}

				i++;	
				
			}

            input.close();

        } catch (Exception ex) {
		
            ex.printStackTrace();
			
        }
		
		System.out.println("Data set consists of " + counter + " cars");
		System.out.println("********Training complete********");
			
		double PC1 = 0;
		double PC2 = 0;
		double PC3 = 0;
		double PC4 = 0;
		
		for (int j = 0; j < counter; j++) {
		
			if (Class[j].equals("unacc")) {
			
				PC1++;
			
			} else if(Class[j].equals("acc")) {
			
				PC2++;
			
			} else if (Class[j].equals("good")) {
			
				PC3++;
			
			} else {
			
				PC4++;
			
			}
		
		}
		
		double RatePC1 =  PC1/counter;
		double RatePC2 =  PC2/counter;
		double RatePC3 =  PC3/counter;
		double RatePC4 =  PC4/counter;
		
		System.out.println("Now give us the attributes of the car to classify it!");
		
		Scanner scan= new Scanner(System.in);

		String Bying = scan.nextLine();
		String Maint = scan.nextLine();
		String Lug_boot = scan.nextLine();
		String Safety = scan.nextLine();
		int Doors = scan.nextInt();
		int Persons = scan.nextInt();
		
		System.out.println("Wait a second while we are processing the cars attributes");
		
		double c1;
		double c2;
		double c3;
		double c4;
		double c5;
		double c6;
		double P = 0;
		String category = null; 
		double Rate; 
		double [] Rates = new double[4]; 
		
		for (int l = 0; l < 4; l++) {
			
			c1 = 1.0;
			c2 = 1.0;
			c3 = 1.0;
			c4 = 1.0;
			c5 = 1.0;
			c6 = 1.0;
			Rate = 1.0;
			
			if (l == 0) {
			
				category = "unacc";
				P = PC1;
				Rate = RatePC1;
			
			} else if (l == 1) {
			
				category = "acc";
				P = PC2;
				Rate = RatePC2;
			
			} else if (l == 2) {
		
				category = "good";
				P = PC3;
				Rate = RatePC3;
			
			} else {
			
				category = "vgood";
				P = PC4;
				Rate = RatePC4;
			
			}
			
			for (int j = 0; j < counter; j++) {
			
				if (bying[j] == Bying && Class[j].equals(category)) {
				
					c1++;
				
				}
			
			}
			
			Rate = Rate * (c1/(P + 4));
			
			for (int j = 0; j < counter; j++) {
			
				if (maint[j] == Maint && Class[j].equals(category)) {
				
					c2++;
				
				}
			
			}
			
			Rate = Rate * (c2/(P + 4));
			
			for (int j = 0; j < counter; j++) {
			
				if (lug_boot[j] == Lug_boot && Class[j].equals(category)) {
				
					c3++;
				
				}
			
			}
			
			Rate = Rate * (c3/(P + 4));
			
			for (int j = 0; j < counter; j++) {
			
				if (safety[j] == Safety && Class[j].equals(category)) {
				
					c4++;
				
				}
			
			}
			
			Rate = Rate * (c4/(P + 3));
			
			for (int j = 0; j < counter; j++) {
			
				if (doors[j] == Doors && Class[j].equals(category)) {
				
					c5++;
				
				}
			
			}
			
			Rate = Rate * (c5/(P + 3));
			
			for (int j = 0; j < counter; j++) {
			
				if (persons[j] == Persons && Class[j].equals(category)) {
				
					c6++;
				
				}
			
			}
			
			Rate = Rate * (c6/(P + 3));
			Rates[l] = Rate;
			
		}
		
		double max = 0.0;
		int pos = 0;
		for (int j = 0; j < 4; j++) {
			
			if (Rates[j] > max) {
				
				max = Rates[j];
				pos = j;
			
			}
		
		}
		
		System.out.println("Car belongs to " + (pos+1) + "th category!");

	}
}