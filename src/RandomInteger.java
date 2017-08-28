import java.util.Random;

/** Generate 10 random integers in the range 0..99. */
public final class RandomInteger {
  
  public int getNum(int size){
    //note a single Random object is reused here
    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(26)+(byte)'a';
	return randomInt;
  }
}