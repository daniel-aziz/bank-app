package com.jb.bankapp.Beans;

import com.jb.bankapp.Beans.Enums.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


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

}
