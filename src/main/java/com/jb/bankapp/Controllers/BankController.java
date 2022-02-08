package com.jb.bankapp.Controllers;

import com.jb.bankapp.Beans.Bank;
import com.jb.bankapp.Services.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/bank")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @GetMapping("all")
    public List<Bank> getAllBanks() {
        return bankService.getAllBanks();
    }
}
