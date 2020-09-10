package com.example.fuseCanteen.Model.order;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by aishwarya on 9/7/20.
 */

@Entity
@Table(name = "scheduledorders")
public class scheduledorders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private orders orders;

    private LocalDateTime scheduledTime;

    public com.example.fuseCanteen.Model.order.orders getOrders() {
        return orders;
    }

    public void setOrders(com.example.fuseCanteen.Model.order.orders orders) {
        this.orders = orders;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
