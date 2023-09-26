package com.example.knittingback.entity;

import com.example.knittingback.model.Client;
import com.example.knittingback.model.Item;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client_item")

public class Client_ItemEntity {
    @EmbeddedId
    Client_ItemEntityKey id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @MapsId(value = "client_id")
    ClientEntity client;
    //@MapsId means that we tie those fields to a part of the key, and they’re the foreign keys of a many-to-one relationship.

    @ManyToOne
    @JoinColumn(name = "item_id")
    @MapsId(value = "item_id")
    ItemEntity item;

    @ManyToOne
    @JoinColumn(name = "order_entity_id")
    OrderEntity order;
}
