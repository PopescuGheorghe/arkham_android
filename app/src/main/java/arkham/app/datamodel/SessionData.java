package arkham.app.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SessionData implements Serializable {

    @SerializedName("auth_token")
    @Expose
    private String authToken;

    /**
     *
     * @return
     * The authToken
     */
    public String getAuthToken() {
        return authToken;
    }

/**
 *
 * @param authToken
 * The auth_token
 */
}
