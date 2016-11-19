import java.util.ArrayList;
import java.util.Arrays;

public class BombermanPassword {

	private ArrayList<String> letterArrayList; 
	private static String[] letterList = new String[] {"B","H","M","J","D","N","I","A","O","F","K","C","P","G","E","L"};
	private static Integer[] addBombs = new Integer[] {0,0,0,0,0,1,-1,1,-1,2,-1,1,-1,1,0,0,0,0,0,4};
	private static Integer[] addRange = new Integer[] {0,0,0,0,0,1,0,0,0,2,-2,2,-2,2,-2,2,-2,2,-2,6};
	private static Integer[] addStage = new Integer[] {0,0,1,-1,2,-2,2,-2,2,-2,2,-2,2,-2,2,-2,2,-2,2,0};
	private static Integer[] addStageAlt = new Integer[] {0,0,1,-1,2,-2,2,-2,2,-2,2,-2,2,-2,2,-2,2,-1,1,2};
	private static Integer[] addScore = new Integer[] {1,-1,1,-1,2,-2,2,-2,2,-2,2,-2,2,-2,2,-2,2,-2,2,0};
	private static Integer[] addScore9 = new Integer[] {7,-7,7,-7,-2,3,-3,3,-3,4,-4,4,-4,4,-4,4,-4,4,-4,4};
	
	private ArrayList<String> password;
	int currentStage = 3;
	int currentBombs = 2;
	int currentRange = 2;
	int currentScore = 15100;
	
	public static void main(String[] args) {
		BombermanPassword bombermanPassword = new BombermanPassword();
		bombermanPassword.loadLists();
		bombermanPassword.getNewPass(19,5,3,10000);
		bombermanPassword.printPass();				
	}
	
	private void loadLists() {
		letterArrayList = new ArrayList<String>(Arrays.asList(letterList));
		password = new ArrayList<String>(Arrays.asList(new String[]{"H","I","D","J","O","I","J","D","J","G","C","P","C","P","P","C","P","C","P","A"}));
	}
	
	private void getNewPass(int sToAdd, int bToAdd, int rToAdd, int scToAdd) {
		addStages(sToAdd);
		addBombs(bToAdd);
		addRanges(rToAdd);
		addScores(scToAdd);
	}
	
	private void addStages(int stagesToAdd) {
		
		for (int i=0; i<stagesToAdd; i++) {
			switch (currentStage) {
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
			
			currentStage++;
			printPass();
		}
	}
	
	private void addBombs(int bombsToAdd) {
		for (int i=0; i<bombsToAdd; i++) {
			updatePass(0);
			System.out.println("add bomb");
			currentBombs++;
			printPass();			
		}
	}

	private void addRanges(int rangesToAdd) {
		for (int i=0; i<rangesToAdd; i++) {
			updatePass(1);
			System.out.println("add range");
			currentRange++;
			printPass();			
		}
	}
	
	private void addScores(int scoreToAdd) {
		System.out.println("add score");
		for (int i=0; i<scoreToAdd; i+=100) {
			if ((currentScore%1000) == 900) {
				updatePass(5);
			} else {
				updatePass(4);
			}
						
			currentScore+=100;
			printPass();
		}
		printPass();
	}
	
	private void updatePass(int formulaFlag) {
		Integer[] formula;
		switch (formulaFlag) {
			case 0:
				formula = addBombs;
				break;
			case 1:
				formula = addRange;
				break;
			case 2:
				formula = addStage;
				break;
			case 3:	
				formula = addStageAlt;
				break;
			case 4:
				formula = addScore;
				break;
			case 5:
				formula = addScore9;
				break;
			default:
				formula = addScore;
				break;
		}
		
		for (int i=0; i<password.size(); i++) {
			String letter = password.get(i);
			int value = letterArrayList.indexOf(letter);
			int change = formula[i];
			value = (value + change + 16) % 16;
			letter = letterArrayList.get(value);
			password.set(i, letter);
			
		}
		
	}

	private void printPass() {
		System.out.println("Stage-" + currentStage + " : Bombs-" + currentBombs + " : Range-" + currentRange + " : Score-+" + currentScore );
		System.out.println(password.toString() + "\n");	
	}

}
