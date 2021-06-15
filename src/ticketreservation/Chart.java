
package ticketreservation;

public class Chart 
{
    static final int[][] trainConfirmTicketChart=new int[2][8];

    public int[] chartFillTicketCount(int confirmTickets)
    {
        int[] chartDetails=new int[confirmTickets]; 
        for(int confirmTicketCount=0;confirmTicketCount<confirmTickets;confirmTicketCount++)
        {
            chartDetails[confirmTicketCount]=0;
        }
        return chartDetails;
    }
    
    public void viewChart()
    {
        for(int trainNo=0;trainNo<trainConfirmTicketChart.length;trainNo++)
        {
            for(int seat=0;seat<trainConfirmTicketChart[trainNo].length;seat++)
            {
                System.out.printf("%d ", trainConfirmTicketChart[trainNo][seat]);
            }
            System.out.println("");
        }
    }
}
