package arkham.app.datamodel;

import java.util.ArrayList;
import java.util.List;

public class APIError {

    private boolean success;
    private List<String> errors = new ArrayList<String>();

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
     * The errors
     */
    public List<String> getErrors() {

        return errors;
    }

    /**
     *
     * @param errors
     * The errors
     */
    public void setErrors(List<String> errors) {

        this.errors = errors;
    }

}