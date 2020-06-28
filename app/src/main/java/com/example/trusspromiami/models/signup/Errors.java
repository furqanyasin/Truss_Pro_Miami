package com.example.trusspromiami.models.signup;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Errors {

@SerializedName("terms_agrement")
@Expose
private List<String> termsAgrement = null;
@SerializedName("email")
@Expose
private List<String> email = null;
@SerializedName("password")
@Expose
private List<String> password = null;
@SerializedName("retype_password")
@Expose
private List<String> retypePassword = null;

public List<String> getTermsAgrement() {
return termsAgrement;
}

public void setTermsAgrement(List<String> termsAgrement) {
this.termsAgrement = termsAgrement;
}

public List<String> getEmail() {
return email;
}

public void setEmail(List<String> email) {
this.email = email;
}

public List<String> getPassword() {
return password;
}

public void setPassword(List<String> password) {
this.password = password;
}

public List<String> getRetypePassword() {
return retypePassword;
}

public void setRetypePassword(List<String> retypePassword) {
this.retypePassword = retypePassword;
}

}