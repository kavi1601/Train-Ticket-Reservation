
package ticketreservation;

import java.util.ArrayList;
import java.util.Scanner;

public class TicketReservation 
{
    final int noOfTrains=2;
    final int ticketCount=8;
    final int extraTicket=2;
    static Train train=new Train(); 
    static Chart chart=new Chart();

    TicketReservation()
    {
        
        for(int trainCount=0;trainCount<noOfTrains;trainCount++)
        {
            train.trainConfirmDetail.put(trainCount+1, new ArrayList<>());
            train.trainWaitingDetail.put(trainCount+1, new ArrayList<>()); 
            chart.trainConfirmTicketChart[trainCount]=chart.chartFillTicketCount(ticketCount);
        }
    }
    
    public static void main(String[] args) 
    {
        Scanner scanner=new Scanner(System.in); 
        Ticket ticket=new Ticket();
        boolean end=false;
        while(!end)
        {
            System.out.println("1:Ticket 2:Chart 3:Station 4:Exit\n");
            int choice=Integer.parseInt(scanner.nextLine());
            switch(choice)
            {
                case 1 -> ticket.bookTicket(); 
                
                case 2 -> ticket.cancelTicket(); 
                
                case 3 -> chart.viewChart();
                
                case 4 -> System.exit(0);
                
                case 5-> train.viewDetail();
                
                default -> {break;}
            }
        }
    }
    
}

   

