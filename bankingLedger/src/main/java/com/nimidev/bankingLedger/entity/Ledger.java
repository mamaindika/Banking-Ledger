package com.nimidev.bankingLedger.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nimidev.bankingLedger.types.TransactionType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Ledger {
    @Id
    @ApiModelProperty(hidden=true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", unique = true)
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date date;
    private String details;
    private String accountNo;
    private String transactionType;
    private int amount;
    private Long balance;
}
