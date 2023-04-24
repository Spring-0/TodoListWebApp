package me.dev.TodoListWebApp.service;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class TodoService {


    public Date verifyDate(Date date){
        // TODO: Verify date for out of range values
        return null;
    }


    /**
     * Method used to parse string date into Date object
     *
     * @param date date as a string
     * @return Date
     */
    public Date getDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{
            return simpleDateFormat.parse(date);
        } catch (ParseException e){
            e.printStackTrace();
        }

        return null;

    }
}
