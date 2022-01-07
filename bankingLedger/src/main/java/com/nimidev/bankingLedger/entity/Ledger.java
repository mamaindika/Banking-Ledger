package com.nimidev.bankingLedger.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
