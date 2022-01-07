package com.nimidev.bankingLedger.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Data
public class Account {
    @Id
    @ApiModelProperty(hidden=true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "accountNumber", unique = true)
    private String accountNumber;
    private String userId;
}
