package me.despical.purchasechecker.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Despical
 * <p>
 * Created at 30.04.2025
 */
@Getter
@Entity
@Table(name = "verifications")
@NoArgsConstructor
public class Verification {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spigot_id")
    private User user;

    @Column(name = "plugin_id")
    private Long pluginId;

    public Verification(User user, Long pluginId) {
        this.user = user;
        this.pluginId = pluginId;
    }
}
