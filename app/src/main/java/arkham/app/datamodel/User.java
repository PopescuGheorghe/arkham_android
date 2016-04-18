package arkham.app.datamodel;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class User {

    public boolean success;
    public UserData data;

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
     * @param success
     * The success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     *
     * @return
     * The data
     */
    public UserData getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(UserData data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
