package com.jb.bankapp.Controllers;

import com.jb.bankapp.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientRepository clientRepository;



}
