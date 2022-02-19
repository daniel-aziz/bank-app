package com.jb.bankapp.Beans;

import com.jb.bankapp.Beans.Enums.ClientType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.ArrayList;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Client {
    @Id
    private String id;

    @Indexed(unique = true)
    private String nationalId;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private ClientType clientType;

    @DBRef
    private ArrayList<Account> accounts;


}
