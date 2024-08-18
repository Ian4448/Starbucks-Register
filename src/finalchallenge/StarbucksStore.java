/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalchallenge;

import java.util.ArrayList;

public class StarbucksStore {
    private int code;
    private String location;
    private int hourOpen;
    private int hourClosed;
    private ArrayList<SalesTransaction> dailySales;

    public StarbucksStore(int code, String location, int hourOpen, int hourClosed, ArrayList<SalesTransaction> dailySales) {
        this.code = code;
        this.location = location;
        this.hourOpen = hourOpen;
        this.hourClosed = hourClosed;
        this.dailySales = new ArrayList<SalesTransaction>(dailySales);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getHourOpen() {
        return hourOpen;
    }

    public void setHourOpen(int hourOpen) {
        this.hourOpen = hourOpen;
    }

    public int getHourClosed() {
        return hourClosed;
    }

    public void setHourClosed(int hourClosed) {
        this.hourClosed = hourClosed;
    }

    public ArrayList<SalesTransaction> getDailySales() {
        return dailySales;
    }

    public void setDailySales(ArrayList<SalesTransaction> dailySales) {
        this.dailySales = dailySales;
    }
    
    public double accumulateDailySales()
    {
        double dailyGrandTotal = 0; 
        
        for (int i = 0; i < getDailySales().size(); i++) {
            dailyGrandTotal += getDailySales().get(i).getAmountPaid();
        }
        
        return dailyGrandTotal;
    }

    @Override
    public String toString() {
        return "StarbucksStore{" +
                "code=" + code +
                ", location='" + location + '\'' +
                ", hourOpen=" + hourOpen +
                ", hourClosed=" + hourClosed +
                ", dailySales=" + dailySales +
                '}';
    }
}
