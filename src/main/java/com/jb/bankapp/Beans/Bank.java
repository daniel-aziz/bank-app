package com.jb.bankapp.Beans;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Bank {

    @Id
    private String id;

    @Indexed(unique = true)
    private int branchCode;

    private String name;
    private String city;

    @DBRef
    private ArrayList<Client> clients;

}
