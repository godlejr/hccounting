package hcc.accouting.haccounting.common.dto;

public class SmsMessageDto {

    private String cardCompany;
    private String cardNumber;
    private String cardOwner;
    private String cardAccess;
    private String price;
    private String date;
    private String time;
    private String usage;

    public SmsMessageDto(String cardCompany, String cardNumber, String cardOwner, String cardAccess, String price, String date, String time, String usage) {
        this.cardCompany = cardCompany;
        this.cardNumber = cardNumber;
        this.cardOwner = cardOwner;
        this.cardAccess = cardAccess;
        this.price = price;
        this.date = date;
        this.time = time;
        this.usage = usage;
    }

    public SmsMessageDto() {

    }

    public String getCardCompany() {
        return cardCompany;
    }

    public void setCardCompany(String cardCompany) {
        this.cardCompany = cardCompany;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    public String getCardAccess() {
        return cardAccess;
    }

    public void setCardAccess(String cardAccess) {
        this.cardAccess = cardAccess;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return "SmsMessageDto{" +
                "cardCompany='" + cardCompany + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardOwner='" + cardOwner + '\'' +
                ", cardAccess='" + cardAccess + '\'' +
                ", price='" + price + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", usage='" + usage + '\'' +
                '}';
    }
}
