package kz.asumc.dto;

public class RequestDto {
    private String requestNumber;
    private String clientName;
    private String description;

    public RequestDto() {}

    public RequestDto(String requestNumber, String clientName, String description) {
        this.requestNumber = requestNumber;
        this.clientName = clientName;
        this.description = description;
    }

    public String getRequestNumber() { return requestNumber; }
    public void setRequestNumber(String requestNumber) { this.requestNumber = requestNumber; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}