package com.example.knittingback.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Embeddable
public class Client_ItemEntityKey implements Serializable {
    @Column(name = "client_ID")
    private long client_id;
    @Column(name = "item_ID")
    private long item_id;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
