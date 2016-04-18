package arkham.app.datamodel;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


public class Session implements Serializable {
    public boolean success;
    public SessionData data;

    @SerializedName("email")
    String mEmail;

    @SerializedName("password")
    String mPassword;

    public Session(String email, String password) {
        this.mEmail = email;
        this.mPassword = password;
    }

    /**
     *
     * @return
     * The success
     */
    public boolean isSuccess() {

        return success;
    }

    /**
     *
     * @return
     * The data
     */
    public SessionData getData() {

        return data;
    }
}
