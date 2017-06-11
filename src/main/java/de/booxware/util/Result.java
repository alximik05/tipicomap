package de.booxware.util;

/**
 * Created by stas on 11/06/17.
 */
public class Result {

    private boolean success;
    private String description;

    public Result(boolean success, String description) {
        this.success = success;
        this.description = description;
    }

    public Result() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
