import java.util.ArrayList;
import java.util.Arrays;

public class BManPass {

	private ArrayList<String> letterArrayList; 
	private static String[] letterList = new String[] {"B","H","M","J","D","N","I","A","O","F","K","C","P","G","E","L"};
	private static Integer[] addB = new Integer[] {0,0,0,0,0,1,-1,1,-1,2,-1,1,-1,1,0,0,0,0,0,4};
	private static Integer[] addR = new Integer[] {0,0,0,0,0,1,0,0,0,2,-2,2,-2,2,-2,2,-2,2,-2,6};
	private static Integer[] addS = new Integer[] {0,0,1,-1,2,-2,2,-2,2,-2,2,-2,2,-2,2,-2,2,-2,2,0};
	private static Integer[] addSAlt = new Integer[] {0,0,1,-1,2,-2,2,-2,2,-2,2,-2,2,-2,2,-2,2,-1,1,2};
	private static Integer[] addSc = new Integer[] {1,-1,1,-1,2,-2,2,-2,2,-2,2,-2,2,-2,2,-2,2,-2,2,0};
	private static Integer[] addSc9 = new Integer[] {7,-7,7,-7,-2,3,-3,3,-3,4,-4,4,-4,4,-4,4,-4,4,-4,4};
	
	private ArrayList<String> pass;
	int currentS = 3;
	int currentB = 2;
	int currentR = 2;
	int currentSc = 15100;
	
	public static void main(String[] args) {
		BManPass bManPass = new BManPass();
		bManPass.loadLists();
		bManPass.getNewPass(19,5,3,10000);
		bManPass.printPass();				
	}
	
	private void loadLists() {
		letterArrayList = new ArrayList<String>(Arrays.asList(letterList));
		pass = new ArrayList<String>(Arrays.asList(new String[]{"H","I","D","J","O","I","J","D","J","G","C","P","C","P","P","C","P","C","P","A"}));
	}
	
	private void getNewPass(int sToAdd, int bToAdd, int rToAdd, int scToAdd) {
		addSs(sToAdd);
		addBs(bToAdd);
		addRs(rToAdd);
		addSc(scToAdd);
	}
	
	private void addSs(int sToAdd) {
		
		for (int i=0; i<sToAdd; i++) {
			switch (currentS) {
				case 0:
				case 15:
				case 31:
				case 47:
					System.out.println("use alt");
					updatePass(3);
					break;
				default:
					updatePass(2);
					System.out.println("use normal");
					break;
			}
			
			currentS++;
			printPass();
		}
	}
	
	private void addBs(int bToAdd) {
		for (int i=0; i<bToAdd; i++) {
			updatePass(0);
			System.out.println("add b");
			currentB++;
			printPass();			
		}
	}

	private void addRs(int rToAdd) {
		for (int i=0; i<rToAdd; i++) {
			updatePass(1);
			System.out.println("add r");
			currentR++;
			printPass();			
		}
	}
	
	private void addSc(int scToAdd) {
		System.out.println("add sc");
		for (int i=0; i<scToAdd; i+=100) {
			if ((currentSc%1000) == 900) {
				updatePass(5);
			} else {
				updatePass(4);
			}
						
			currentSc+=100;
			printPass();
		}
		printPass();
	}
	
	private void updatePass(int formulaFlag) {
		Integer[] formula;
		switch (formulaFlag) {
			case 0:
				formula = addB;
				break;
			case 1:
				formula = addR;
				break;
			case 2:
				formula = addS;
				break;
			case 3:	
				formula = addSAlt;
				break;
			case 4:
				formula = addSc;
				break;
			case 5:
				formula = addSc9;
				break;
			default:
				formula = addSc;
				break;
		}
		
		for (int i=0; i<pass.size(); i++) {
			String letter = pass.get(i);
			int value = letterArrayList.indexOf(letter);
			int change = formula[i];
			value = (value + change + 16) % 16;
			letter = letterArrayList.get(value);
			pass.set(i, letter);
			
		}
		
	}

	private void printPass() {
		System.out.println("S-" + currentS + " : B-" + currentB + " : R-" + currentR + " : S-+" + currentSc );
		System.out.println(pass.toString() + "\n");	
	}

}
