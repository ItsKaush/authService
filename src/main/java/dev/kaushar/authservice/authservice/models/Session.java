package dev.kaushar.authservice.authservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Session extends BaseModel{
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus status;
    private String token;
    private String deviceId;
    private Date expiredAt;
    private String ipAddress;
    @ManyToOne
    private User user;
}
