package com.jb.bankapp.Beans;

import com.jb.bankapp.Beans.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Account {
    @Id
    private String id;

    @Indexed(unique = true)
    private long accountNumber;
    private double currentAmount = 0;
    private Status status;

    private Client client;

}
