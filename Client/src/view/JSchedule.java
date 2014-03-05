package view;
   
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
   
 
 
 
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
 
import model.Communication;
import model.User;
import model.schedule.ScheduleHandler;
import controller.Workflow;
   
   
/**
 * Creates the GUI part of the schedule
 *  
 * @author Benjamin Wijk
 * @version 2014-03-03
 */
public class JSchedule extends JPanel implements Observer {
    protected LinkedList<JPanel> plannedWorkList = new LinkedList<JPanel>();
    protected LinkedList<JPanel> plannedActualList = new LinkedList<JPanel>();
    protected LinkedList<JTextArea> scheduleList = new LinkedList<JTextArea>();
     
    public JSchedule(){
    }
     
    public void update(Observable o, Object arg) {
        if (o instanceof Communication) {
            LinkedList<Object> argsList = (LinkedList<Object>) arg;
            if(argsList.get(0).equals("GetUser") || argsList.get(0).equals("login")){
                fillPanels();
            }
        }            
    }
    
    /**
     * Initializes Swing objects and builds the schedule GUI
     */
    public void initBuild(){  
        JFrame frame = new JFrame(); //TEMPORÄR
        frame.setPreferredSize(new Dimension(500,500));
           
        JPanel pane = new JPanel(new GridBagLayout());
        JPanel days[] = createPanels(1);
        JPanel dates[] = createPanels(1);
        JPanel planContainer[] = createPanels(2);
           
        GridBagConstraints c = new GridBagConstraints();
           
        JLabel displayWeek = createJLabel("Vecka 9"); //STATISKT VÄRDE ATM ÄNDRA!
           
        JButton nextWeek = new JButton ("Next");
        JButton prevWeek = new JButton ("Previous");
           
        //Grid-Setup start ----------------------
    //  nextWeek.setPreferredSize(new Dimension(85, 30));
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(prevWeek, c);
           
        //Visar currentWeek i top-mid OBS STATISKT VÄRDE ATM!!!!!!!!!!!!!!!!!!!!!!!!!!
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 3;
        c.gridy = 0;
        pane.add(displayWeek, c);
           
        //prevWeek.setPreferredSize(new Dimension(85, 30));
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 6;
        c.gridy = 0;
        pane.add(nextWeek, c);
           
        for(int i = 0; i<=6 ; i++){
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = i;
            c.gridy = 2;
            pane.add(days[i],c);
            days[i].add(dates[i],c);
            days[i].add(planContainer[i],c);
        }
        c.anchor = GridBagConstraints.CENTER;
        //Grid-Setup end
           
       
        //TEMP-KOD--------------
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.pack();
        frame.setVisible(true);
        //TEMP-KOD  -----------
    }
       
    /**
     *  Fills the JPanels with the planned times, as well as stamped time.  
     */
    public void fillPanels(){  
        
    	removeAll();
    	
    	
        ArrayList<Integer> checkIn = null; //Beh�ver separata listor f�r att f�renkla loopar.
        ArrayList<Integer> checkOut = null;
        ArrayList<Integer> plannedTime = null;
           
        //TESTV�RDEN START, ScheduleHandler ger f�r tillf�llet bara 1 par tider, vill pr�va med minst 2.
        ArrayList<Integer> checkInList = new ArrayList<Integer>();
        checkInList.add(60);
        checkInList.add(140);
        ArrayList<Integer> checkOutList = new ArrayList<Integer>();
        checkOutList.add(100);
        checkOutList.add(180);
           
        ArrayList<Integer> scheduledTimeList = new ArrayList<Integer>();
        scheduledTimeList.add(20);
        scheduledTimeList.add(80);
        scheduledTimeList.add(120);
        scheduledTimeList.add(160);
        scheduledTimeList.add(180);
        scheduledTimeList.add(200);
           
        ArrayList<ArrayList<Integer>> totalList = new ArrayList<ArrayList<Integer>>();
           
        totalList.add(checkInList);
        totalList.add(checkOutList);
        totalList.add(scheduledTimeList);
        //TESTV�RDEN SLUT --------------------------------------------------------------------
           
           
           
         
        // ScheduleHandler Handler = Workflow.getInstance().scheduleHandler();
        
         
 
           
        LinkedList<JTextArea> dayText = new LinkedList<JTextArea>();
        LinkedList<String> dayName = new LinkedList<String>();
        dayName.add("Monday"); dayName.add("Tuesday"); dayName.add("Wednesday"); dayName.add("Thursay"); dayName.add("Friday"); dayName.add("Saturday"); dayName.add("Sunday");  
           
           
        for(int i = 0; i<=6; i++){ //F�r varje dag,
            //totalList = ScheduleHandler.scheduleToDays(i);   
            ArrayList<Integer> tempFill;
            int listNumber = 0;
             
            Iterator<ArrayList<Integer>> it = totalList.iterator(); //"Retrieving" various lists, ska nog bytas ut mot for-each loop.
               while(it.hasNext()){
                listNumber++;
                tempFill = it.next();
                if(listNumber == 1) //Kom inte p� n�gon b�ttre metod �n if-satser, bl�.
                    checkIn = tempFill;
                if(listNumber == 2)
                    checkOut = tempFill;
                if(listNumber == 3)
                    plannedTime = tempFill;
               }
                  
               for(int x = 0; x<=checkIn.size(); x++){ //F�r varje incheckning.
                   //skapa knapp med start/slutv�rde? m.h.a. checkIn/Out.get(x)?
               }
               //Liknande for-loop f�r start/sluttid, anv�nder dock .get(x) och .get(x+1) som v�rden
        
         
           
           
        StringBuilder s = new StringBuilder(5000);
           
        if(plannedTime.size()%2 == 0){
            //System.out.println("Planerad arbetstid:");
               
            for(int x=0; x<=plannedTime.size()/2; x+=2){
                s.append(toHours(plannedTime.get(x)));
                s.append(" - ");
                s.append(toHours(plannedTime.get(x+1)));
                s.append("\n");
                //System.out.println(plannedTime.get(x) + "-" + plannedTime.get(x+1));
            }
        }
        else
            s.append("Error: plannedTime not even\n");
               
           
        s.append("\nStamped times: \n");
        if(checkIn.size() == checkOut.size()){
                for(int x=0; x<=checkIn.size()-1; x++){
                    s.append(toHours(checkIn.get(x)));
                    s.append(" - ");
                    s.append(toHours(checkOut.get(x)));
                    s.append("\n");
                    //System.out.println(checkIn.get(x) + "-" + checkOut.get(x));
                }
        }
        else
            System.out.println("Error: size of checkIn does not match the size of checkOut.");
           
           
        JTextArea textArea = new JTextArea(dayName.get(i) +"\nPlanned time:\n" + s.toString());
        dayText.add(textArea);
        }
           
         
         
        for(JTextArea temp: dayText)
            add(temp);
        updateUI(); 
       
    }
           
    public String toHours(int x){
        String [] s = {""+x/60, ""+x%60};
           
        if(x/60 < 10)
            s[0] = "0" + x/60;
        if(x%60 < 10)
            s[1] = "0" + x%60;
           
        return s[0] +":" + s[1];
    }
       
       
    public JLabel createJLabel(String text){
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setText(text);
        label.setForeground(Color.BLACK);
           
        return label;
    }
       
       
    public JPanel[] createPanels(int x){
        JPanel [] panes = new JPanel[7];
        switch(x){
           
            case 1:
                for(int i=0; i<=6 ; i++){
                    panes[i] = new JPanel();
                    panes[i].setLayout(new BoxLayout(panes[i], BoxLayout.PAGE_AXIS));
                    panes[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                       
                }
                break;
               
            case 2:
                for(int i=0; i<=6 ; i++){
                    panes[i] = new JPanel();
                    JPanel planned[] = new JPanel[2];
                    for(int a=0; a<=1; a++){
                        planned[a] = new JPanel();
                        planned[a].setLayout(new BoxLayout(planned[a], BoxLayout.PAGE_AXIS));
                        planned[a].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                           
                        panes[i].add(planned[a]);
                           
                        if(a == 0)
                            plannedWorkList.add(planned[a]);
                        else
                            plannedActualList.add(planned[a]);
                    }
                       
                }
           
        }
        return panes;
    }
 
    
   
   
       
       
}

