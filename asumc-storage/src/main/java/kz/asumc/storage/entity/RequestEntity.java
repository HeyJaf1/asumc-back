package kz.asumc.storage.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "requests")
public class RequestEntity extends BaseEntity {
    
    @Column(name = "request_number", unique = true, nullable = false)
    private String requestNumber;
    
    @Column(name = "client_id", nullable = false)
    private Long clientId;
    
    @Column(name = "client_name")
    private String clientName;
    
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    
    @Column(name = "description", length = 1000)
    private String description;
    
    public enum RequestStatus {
        DRAFT, NEW, IN_PROGRESS, COMPLETED, REJECTED, CANCELLED
    }
    
    // Геттеры и сеттеры
    public String getRequestNumber() { return requestNumber; }
    public void setRequestNumber(String requestNumber) { this.requestNumber = requestNumber; }
    
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    
    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}