
package ticketreservation;

import java.util.ArrayList;
import java.util.Scanner;

public class Ticket 
{
    int pnr=1;
    Scanner scanner = new Scanner(System.in);
    Station station = new Station();
    Train train=new Train();
    Chart chart=new Chart();
    
    public void bookTicket()
    {
     
        ArrayList<Object> passangerDetails=new ArrayList<>();
        String orgin,destination;
        passangerDetails.add(pnr);
        System.out.println("Enter Boarding Point");
        orgin=scanner.nextLine(); 
        System.out.println("Enter Departure Point");
        destination=scanner.nextLine();
        while(orgin.equals(destination) || orgin.length()==0 || destination.length()==0)
        {
            if(orgin.equals(destination))
                System.out.println("Orgin and Destination can be Same");
            else
                if(orgin.length()==0)
                    System.out.println("Orgin is empty");
                else
                    System.out.println("Destination is empty");
            System.out.println("Enter Boarding Point");
            orgin=scanner.nextLine(); 
            System.out.println("Enter Departure Point");
            destination=scanner.nextLine();
        }
        station.checkStationDetails(orgin, destination , passangerDetails);
        printTicket(pnr); 
        pnr+=1;
    }
    
    public void cancelTicket()
    {
        System.out.println("Enter Your PNR No");
        int pnrNo=Integer.parseInt(scanner.nextLine()); 
        int waitingIndex=0; 
        ArrayList<ArrayList> cancelTicket=new ArrayList<>();
        for(int trainNo:train.trainConfirmDetail.keySet())
        {
            while(waitingIndex<train.trainWaitingDetail.get(trainNo).size())
            {
                
                if((int)train.trainWaitingDetail.get(trainNo).get(0).get(0)==pnrNo)
                {
                    train.trainWaitingDetail.get(trainNo).get(0).set(6,"Cancel");
                    cancelTicket.add(train.trainWaitingDetail.get(trainNo).get(0));
                    train.trainWaitingDetail.get(trainNo).remove(0);
                    waitingIndex--;
                }
                waitingIndex++;
            }
            
            int passangerIndex=0;
            while(passangerIndex<train.trainConfirmDetail.get(trainNo).size())
            {
                if((int)train.trainConfirmDetail.get(trainNo).get(passangerIndex).get(0)==pnrNo)
                {
                    ArrayList pasDetail=train.trainConfirmDetail.get(trainNo).get(passangerIndex); 
                    train.trainConfirmDetail.get(trainNo).get(0).set(6,"Cancel");
                    cancelTicket.add(train.trainConfirmDetail.get(trainNo).get(passangerIndex));
                    train.trainConfirmDetail.get(trainNo).remove(passangerIndex);
                    if(!train.trainWaitingDetail.get(trainNo).isEmpty())
                    {
                        train.trainWaitingDetail.get(trainNo).get(0).set(5, (int)pasDetail.get(5)); 
                        train.trainWaitingDetail.get(trainNo).get(0).set(6,"Confirm");
                        train.trainConfirmDetail.get(trainNo).add(passangerIndex,train.trainWaitingDetail.get(trainNo).get(0)); 
                        train.trainWaitingDetail.get(trainNo).remove(0); 
                    }  
                    else
                    {
                        chart.trainConfirmTicketChart[(int)pasDetail.get(1)-1][(int)pasDetail.get(5)-1]=0;
                        passangerIndex-=1;
                    }
                }
                passangerIndex+=1;
                
            }
            
        }
        cancelTicket.forEach(passangerList -> 
        {
            System.out.printf("From :%s\nTo :%s\nPNR Number is :%d\tTrain No is:%d\nPassanger Name is :%s\tSeat Number: %d\n****Status : %s ****\n",passangerList.get(2),passangerList.get(3),passangerList.get(0),passangerList.get(1),passangerList.get(4),passangerList.get(5),passangerList.get(6));
        });
        System.out.println("***** Cancel Ticket Successfully *****");
    }
    
    public void printTicket(int pnrNo)
    {
        train.trainConfirmDetail.keySet().forEach(trainNo -> 
        {
            train.trainConfirmDetail.get(trainNo).stream().filter(passangerDetail -> ((int)passangerDetail.get(0)==pnrNo)).forEachOrdered(passangerDetail -> {
                System.out.printf("From :%s\nTo :%s\nPNR Number is :%d\tTrain No is:%d\nPassanger Name is :%s\tSeat Number: %d\n****Status : %s****\n",passangerDetail.get(2),passangerDetail.get(3),passangerDetail.get(0),passangerDetail.get(1),passangerDetail.get(4),passangerDetail.get(5),passangerDetail.get(6));
            });
        });
        
        train.trainWaitingDetail.keySet().forEach(trainNo -> 
        {
            train.trainWaitingDetail.get(trainNo).stream().filter(passangerDetail -> ((int)passangerDetail.get(0)==pnrNo)).forEachOrdered(passangerDetail -> {
                System.out.printf("From :%s\nTo :%s\nPNR Number is :%d\tTrain No is:%d\nPassanger Name is :%s\tSeat Number: %d\n****Status : %s****\n",passangerDetail.get(2),passangerDetail.get(3),passangerDetail.get(0),passangerDetail.get(1),passangerDetail.get(4),passangerDetail.get(5),passangerDetail.get(6));
            });
        });
         
    }
    
}
