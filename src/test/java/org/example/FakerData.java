package org.example;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FakerData {

@Test
    public void fake() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, 2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YY");
        System.out.println(simpleDateFormat.format(calendar.getTime()));
    }
}
