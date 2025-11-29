package org.example.pojos;

public class BookingDates {

    //booking dates class
/*        "bookingdates" : {
            "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
    },*/

    String checkin;
    String checkout;

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}
