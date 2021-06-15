
package ticketreservation;

import java.util.ArrayList;
import java.util.Scanner;

public class Station
{   
    TicketReservation ticketReservation= new TicketReservation(); 
    Seat seat=new Seat();
    Chart chart=new Chart(); 
    Train train=new Train();
    Scanner scanner=new Scanner(System.in);
    
    private int[] returnRoute(String orgin, String destination,int seatCount)
    {
        String[] routeStation=new String[]{"ABCDE","XYC"};
        
        Integer from=null;
        Integer to=null;
        int f=0;
        for(int routeStationIndex=0;routeStationIndex<routeStation.length;routeStationIndex++)
        {
            int confirmListSize=train.trainConfirmDetail.get(routeStationIndex+1).size();
            int waitingListSize=train.trainWaitingDetail.get(routeStationIndex+1).size();
            if(routeStation[routeStationIndex].contains(orgin)) 
            {   
                if((ticketReservation.ticketCount+ticketReservation.extraTicket)-(confirmListSize+waitingListSize)>=seatCount)
                {
                    from=routeStationIndex+1;
                }
                else
                {
                    f+=1;
                }
            }
            if(routeStation[routeStationIndex].contains(destination))
            {
                if((ticketReservation.ticketCount+ticketReservation.extraTicket)-(confirmListSize+waitingListSize)>=seatCount)
                {
                    to=routeStationIndex+1;
                }
                else
                {
                    f+=1;
                }
            }
            if(from!=null && to!=null)
            {
                break;
            }
        }
        
        if(from==null || to==null)
        {
            if(f==0)
                System.out.println("Route is not Available");
            else
                System.out.println("Seat Not Available");
        }
        return new int[]{from,to};
        
    }
    
    public void checkStationDetails(String orgin, String destination, ArrayList passangerDetails)
    {
         
        System.out.println("Enter Seat Count");
        int seatCount=scanner.nextInt();
        try
        {
            int[] route=returnRoute(orgin, destination,seatCount); 
            passangerDetails.add(route[0]);
            passangerDetails.add(orgin);
            passangerDetails.add(destination); 
            if(route[0]!=route[1])
            {
                passangerDetails.add(1,route[1]);
                passangerDetails.add(4,"C");
            }
            seat.getPassangerDetails(passangerDetails,seatCount);
        }
        catch(Exception e)
        {
            System.out.println("Again Book With Corret Detais");
        }
    }
    
}
