import javax.sound.midi.Soundbank;

public class WordMatch {

    private String word;

    public WordMatch(String word){

        this.word = word;

    }

    public int scoreGuess(String guess){

//      coreGuess finds the number of times that guess occurs as
//      a substring of secret and then multiplies that number by the square of the length of guess
        String secret = getWord();

        if(guess.isEmpty()){

            throw new IllegalArgumentException(
                    "Your guess cannot be an empty String!"
            );

        } else if (guess.length() > secret.length()){

            throw new IllegalArgumentException(
                    "Your guess cannot exceed the number of characters in the Secret word"
            );

        }

        int guessLengthSquared = (int) Math.pow(guess.length(), 2);
        int maxIndex = secret.length() - guess.length();
        int instances = 0;

        for(int i = 0; i <= maxIndex; i++){

            String testSubString = secret.substring(i, i + guess.length());

            if(i < maxIndex){

                if(testSubString.equals(guess) ){

                    instances++;

                }

            } else {

                String finalSubString = secret.substring(maxIndex);

                if(finalSubString.equals(guess)){

                    instances++;

                }

            }

        }

        return instances * guessLengthSquared;
    }

    public String findBetterGuess(String guess1, String guess2){

        int guess1Value = scoreGuess(guess1);
        int guess2Value = scoreGuess(guess2);
        String result = null;

        if(guess1Value > guess2Value){

            result = guess1;

        } else if (guess2Value > guess1Value){

            result = guess2;

        } else { // two are equal

            if(guess1.compareTo(guess2) > 0){ // compareTo finds the alphabetical values (ChatGPT), compares each indivudual character left to right

                result = guess1; // this means the first non-similar character of guess1 is greater than guess 2 therefore alphabetically larger

            } else if (guess2.compareTo(guess1) > 0){

                result = guess2; // vice versa

            }

        }

        return result;
    }

    public String getWord(){

        return word;

    }

}
