
package ticketreservation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Train 
{
    
    static final LinkedHashMap<Integer,ArrayList<ArrayList>> trainConfirmDetail=new LinkedHashMap<>();
    static final LinkedHashMap<Integer,ArrayList<ArrayList>> trainWaitingDetail=new LinkedHashMap<>();
    
    Chart chart=new Chart();
    Scanner scanner = new Scanner(System.in);    
    
    private int returnSeatNo(int trainNo)
    {
        int seat=-1;
        for(int seatIndex=0;seatIndex<chart.trainConfirmTicketChart[trainNo-1].length;seatIndex++)
        {
            if(chart.trainConfirmTicketChart[trainNo-1][seatIndex]==0)
            {
                seat=seatIndex;
                break;
            }
        }
        return seat+1;
    }
    
    private ArrayList returnTrainNumber(ArrayList passangerDetails)
    {
        ArrayList<Integer> trainNos=new ArrayList<>(); 
        if(passangerDetails.size()==7)
            trainNos.add((int)passangerDetails.get(2));
        trainNos.add((int)passangerDetails.get(1));
        return trainNos;
    }

    public void addPassangerDetails(ArrayList passangerDetails)
    {
        ArrayList trainNos=returnTrainNumber(passangerDetails); 
        int seat;
        if(trainNos.size()==1)
        {
            seat=returnSeatNo((int)trainNos.get(0));
            if(seat!=0 && seat<=8)
            {
                passangerDetails.add(seat);
                passangerDetails.add("Confirm");
                chart.trainConfirmTicketChart[(int)trainNos.get(0)-1][seat-1]=1; 
                trainConfirmDetail.get((int)trainNos.get(0)).add(passangerDetails);
            }
            else
            {
                passangerDetails.add(0);
                passangerDetails.add("Waiting"); 
                trainWaitingDetail.get((int)trainNos.get(0)).add(passangerDetails);
            }            
        }
        else
        {
            for(Object trainNo:  trainNos)
            {
                ArrayList passangerDetail=(ArrayList)passangerDetails.clone();
                if((int)trainNo==2)
                {
                    passangerDetail.remove(1);
                    passangerDetail.remove(4);
                    seat=returnSeatNo((int)trainNo); 
                    if(seat!=0 && seat<=8)
                        chart.trainConfirmTicketChart[(int)trainNo-1][seat-1]=1;
                }
                else
                {
                    passangerDetail.remove(2);
                    passangerDetail.remove(2);
                    seat=returnSeatNo((int)trainNo); 
                    if(seat!=0 && seat<=8)
                        chart.trainConfirmTicketChart[(int)trainNo-1][seat-1]=1;
                }
               
                if(seat!=0 && seat<=8)
                {   
                    passangerDetail.add(seat);
                    passangerDetail.add("Confirm");
                    trainConfirmDetail.get((int)trainNo).add(passangerDetail);
                }
                else
                {
                    passangerDetail.add(0);
                    passangerDetail.add("Waiting"); 
                    trainWaitingDetail.get((int)trainNo).add(passangerDetail);
                }
            }
        }
    }

    void viewDetail()
    {
        trainConfirmDetail.keySet().forEach(trainNo -> {
            trainConfirmDetail.get(trainNo).forEach(passangerDetail -> {
                System.out.println(passangerDetail);
            });
        });
        trainWaitingDetail.keySet().forEach(trainNo -> {
            trainWaitingDetail.get(trainNo).forEach(passangerDetail -> {
                System.out.println(passangerDetail);
            });
        });
    }
    
    
}
