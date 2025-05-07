package me.despical.purchasechecker.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author Despical
 * <p>
 * Created at 2.05.2025
 */
@Getter
@Setter
@Entity
@Table(name = "errors")
public class VerificationError {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "message")
    private String message;

    @Column(name = "timestamp")
    @Getter(AccessLevel.PRIVATE)
    private LocalDateTime timestamp;

    public String getErrorTime() {
        return timestamp.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}
