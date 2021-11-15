package se1_projekt.commands;

import se1_projekt.controller.EingabeDialog;
import se1_projekt.strategies.IStrategy;


public class Analyze implements ICommand {

    //Context (Strategy Pattern)


    static final String UNKNOWN = "Unknown Command!";
    private static IStrategy strategie;

    public static void setStrategie(final IStrategy STRATEGIE){
        strategie = STRATEGIE;
    }

    public void execute() {
        String[] com = EingabeDialog.strings;
        switch(com.length){
            case 2:
                try {
                    int i = Integer.parseInt(com[1]);
                    strategie.analyzeById(i);
                }catch(NumberFormatException e){
                    System.out.println(UNKNOWN);
                }
                break;
            case 3:
                if(com[1].equals("-") && com[2].equals("all")) strategie.analyzeAll();
                break;
            case 4:
                if(com[2].equals("-") && com[3].equals("details")){
                        try{
                        int i = Integer.parseInt(com[1]);
                        strategie.analyzeDetail(i);
                        }catch(NumberFormatException e){
                            System.out.println(UNKNOWN);
                        }
                }
                else System.out.println(UNKNOWN);
                break;
            case 6:
                if(com[2].equals("-") && com[3].equals("details") && com[4].equals("-") && com[5].equals("hints")){
                    try{
                    int i = Integer.parseInt(com[1]);
                    strategie.analyzeHints(i);
                    }catch(NumberFormatException e){
                        System.out.println(UNKNOWN);
                    }
                }
                break;
            default:
                System.out.println(UNKNOWN);
        }
    }
}
