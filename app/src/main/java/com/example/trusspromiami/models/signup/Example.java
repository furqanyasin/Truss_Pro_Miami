package com.example.trusspromiami.models.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

@SerializedName("status")
@Expose
private int status;
@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private Data data;

public int getStatus() {
return status;
}

public void setStatus(int status) {
this.status = status;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

}