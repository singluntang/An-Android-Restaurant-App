package website.programming.androideatit.Model;

import java.util.List;

/**
 * Created by cokel on 3/2/2018.
 */

public class Request {
    private String id;
    private String phone;
    private String name;
    private String address;
    private String total;
    private String status;
    private String Comment;
    private String paymentState;
    private String foods;

    public Request() {
    }

    public Request(String id, String phone, String name, String address, String total, String status, String comment, String paymentState, String foods) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.total = total;
        this.status = status;
        Comment = comment;
        this.paymentState = paymentState;
        this.foods = foods;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public String getFoods() {
        return foods;
    }

    public void setFoods(String foods) {
        this.foods = foods;
    }
}
