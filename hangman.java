import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class hangman {
    public static void main(String[] args) throws FileNotFoundException
    {



        Scanner scanner = new Scanner(new File("C:/Users/ivaza/OneDrive/Desktop/hangman_java/hangman_input.txt")); //file input
        Scanner keyboard = new Scanner(System.in); //user input
        
        List<String> words = new ArrayList<>(); //loop through every line in file
        while(scanner.hasNext())
        {
            words.add(scanner.nextLine()); //add words to list
        }

        Random rand = new Random();

        String word = words.get(rand.nextInt(words.size())); //gives us random word from list
        System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>();

        printWordState(word, playerGuesses);
    
        int wrongCount= 0;
        while(true){
            printHangedMan(wrongCount);
            if(wrongCount >=6)
            {
                System.out.println("You lose!");
                System.out.println("the word was: " + word);
               break;
            }
        
            printWordState( word, playerGuesses);
            if(!getPlayerGuess(keyboard,word,playerGuesses)){
                wrongCount++;
            }
            if (printWordState(word, playerGuesses))
            {
                System.out.println("You win!");
                break;
            }
            System.out.println("Please enter your guess for the word:"); //user tries to guess entire word
            if(keyboard.nextLine().equals(word))
            {
                System.out.println("You win!");
                break;
            }
            else
            {
                System.out.println("not quite! Try again.");
            }
        }
        
    }
private static void printHangedMan( Integer wrongCount)
{
    System.out.println(" -------");
    System.out.println(" |     |");
    if(wrongCount >= 1)
    {
    System.out.println(" 0");
    }
    if(wrongCount >= 2)
    {
    System.out.print("\\ ");
    if(wrongCount >=3)
        {
            System.out.print("/");
        }
    else
        {
        System.out.println("");
        }
    }
    if(wrongCount >=4)
    {
    System.out.println(" |");
    }
    if(wrongCount >=5)
    {
        System.out.print("/ ");
        if(wrongCount >= 6)
        {
            System.out.print("\\");
        }
        else{
            System.out.println("");
        }
    }
System.out.println("");
System.out.println("");
}

private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses )
    {
        System.out.println("please enter a letter");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }


private static boolean printWordState(String word, List<Character> playerGuesses)
{
        int correctCount = 0;
        for(int i =0; i< word.length(); i++) //dashes for unguessed word
        {
            if(playerGuesses.contains(word.charAt(i)))
            {
                System.out.print(word.charAt(i));
                correctCount++;

            }
            else
            {
                System.out.print("-");
            }
        }
        System.out.println();
        return (word.length() == correctCount);
    }
}
    

