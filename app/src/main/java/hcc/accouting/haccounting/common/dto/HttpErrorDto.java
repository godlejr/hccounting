package hcc.accouting.haccounting.common.dto;

public class HttpErrorDto {
    private int statusCode;
    private String message;

    public HttpErrorDto() {
    }

    public HttpErrorDto(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}
