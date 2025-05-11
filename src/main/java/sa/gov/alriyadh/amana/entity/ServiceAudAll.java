package sa.gov.alriyadh.amana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "SERVICE_AUD_ALL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceAudAll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERVICE_SERIAL")
    private BigDecimal serviceSerial;
    @Column(name = "SERVICE_NAME")
    private String serviceName;
    @Column(name = "APP_CODE")
    private String appCode;
    @Column(name = "CLIENT_IP")
    private String clientIp;
    @Column(name = "PINPUT")
    private String pinput;
    @Column(name = "POUTPUT")
    private String poutput;
    @Column(name = "EXECUTE_DATE")
    private Date executeDate;
    @Column(name = "STATUS_CODE")
    private BigDecimal statusCode;
    @Column(name = "STATUS_DESC")
    private String statusDesc;
    @Column(name = "FULL_STATUS_DESC")
    private String fullStatusDesc;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "SERV_ID")
    private BigDecimal servId;
    @Column(name = "SERVER_IP")
    private String serverIp;
    @Column(name = "F5_IP")
    private String f5Ip;
    @Column(name = "SOURCE_TYPE")
    private String sourceType;
    @Column(name = "CALL_DATE")
    private Date callDate;
    @Column(name = "BEFORE_CALL_DATE")
    private Date beforeCallDate;
    @Column(name = "AFTER_CALL_DATE")
    private Date afterCallDate;
    @Column(name = "USER_CODE")
    private String userCode;
    @Column(name = "DIR_CODE")
    private BigDecimal dirCode;
    @Column(name = "PROCESS_ID")
    private String processId;
}
