
package ticketreservation;

import java.util.ArrayList;
import java.util.Scanner;

public class Seat 
{
    Train train=new Train();
    Chart chart=new Chart();
    Scanner scanner=new Scanner(System.in);
    public void getPassangerDetails(ArrayList passangerDetails,int seat)
    {
        
        for(int seatCount=0;seatCount<seat;seatCount++)
        {
            ArrayList passangerDetail=(ArrayList)passangerDetails.clone();
            System.out.println("Enter passanger Name");
            String passangerName=scanner.next(); 
            passangerDetail.add(passangerName); 
            train.addPassangerDetails(passangerDetail);
        }
        System.out.println("***** Book Ticket Successfully *****");
    }
}
