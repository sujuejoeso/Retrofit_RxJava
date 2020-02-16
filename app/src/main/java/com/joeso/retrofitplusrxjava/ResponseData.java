package com.joeso.retrofitplusrxjava;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseData implements Serializable {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private List<Employee> data;

    public List<Employee> getData() {
        return data;
    }
    public void setData(List<Employee> data) {
        this.data = data;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }




}
