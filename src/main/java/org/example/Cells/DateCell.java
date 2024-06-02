package org.example.Cells;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCell implements Cell<DateCell , Date>{
    private Date date;

    public DateCell(String a){
        SimpleDateFormat format = new SimpleDateFormat("\\d{1,2}/\\d{1,2}/\\d{4}");
        try {
            date = format.parse(a);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compare(DateCell cell1) {
        int comparesion = date.compareTo(cell1.getValue());
        if(comparesion > 0){
            return 1;
        }
        else if(comparesion < 0){
            return -1;
        }
        return 0;
    }

    public Date getValue(){
        return date;
    }


}
