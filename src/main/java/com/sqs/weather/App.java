package com.sqs.weather;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String[] nextDays = {"Tomorrow", "In Two days", "In Three days", "In four days"};
        String[] min = {"8", "8", "10", "9", "10"};
        String[] max = {"13", "14", "18", "24", "23"};
        System.out.print(String.format("|%13s|", " "));
        System.out.print(String.format("%3s|", "Min"));
        System.out.println(String.format("|%3s|", "Max"));
        for (int i = 1; i<5;i++){
            System.out.print(String.format("|%-13s|", nextDays[i-1]));
            System.out.println(String.format("%3s|", min[i-1]) + String.format("%3s|", max[i-1]));
   //          String.format("|%-20d|", 93)
        }
    }
}
