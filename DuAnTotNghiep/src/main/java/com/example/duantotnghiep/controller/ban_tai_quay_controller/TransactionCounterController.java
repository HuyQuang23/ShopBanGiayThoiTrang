package com.example.duantotnghiep.controller.ban_tai_quay_controller;

import com.example.duantotnghiep.request.TransactionRequest;
import com.example.duantotnghiep.request.TransactionVnPayRequest;
import com.example.duantotnghiep.response.MessageResponse;
import com.example.duantotnghiep.response.TransactionResponse;
import com.example.duantotnghiep.service.ban_tai_quay_service.impl.TransactionCounterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/v1/transaction/")
public class TransactionCounterController {

    @Autowired
    private TransactionCounterServiceImpl transactionService;

    @GetMapping("show")
    public ResponseEntity<List<TransactionResponse>> findAllTran(@RequestParam(name = "id") UUID id) {
        return new ResponseEntity<>(transactionService.findAllTran(id), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createTransaction(
            @RequestParam(name = "idHoaDon") UUID idHoaDon,
            @RequestParam(name = "id") UUID id,
            @RequestBody TransactionRequest transactionRequest
    ) {
        return new ResponseEntity<>(transactionService.createTransaction(idHoaDon, id, transactionRequest), HttpStatus.CREATED);
    }

    @PostMapping("create-vnpay")
    public ResponseEntity<MessageResponse> createTransactionVnPay(
            @RequestParam(name = "idHoaDon") UUID idHoaDon,
            @RequestParam(name = "id") UUID id,
            @RequestParam("vnp_Amount") BigDecimal vnpAmount
    ) {
        return new ResponseEntity<>(transactionService.cashVnPay(idHoaDon, id, vnpAmount), HttpStatus.CREATED);
    }

}
