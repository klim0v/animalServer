package com.company;


/**
 * Created by c-0k on 27.12.2016.
 */
public class Valid {

    static public boolean valid(String s){
        //int i;
        try {
//            if((s.toLowerCase().contains("e") || s.toLowerCase().contains("."))) {
//                double d = Double.parseDouble(s); //вещественный тип
//                i=0;
//            }
//             else {
                long l = Long.parseLong(s); //целый тип
                //i=1;
                return true;
//            }
        }
        catch(NumberFormatException nfe) {
            //строчный тип
            //i=2;
        }
        return false;
    }

}
