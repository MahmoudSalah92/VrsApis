package sa.gov.alriyadh.amana.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceAudAllDTO {

    private BigDecimal serviceSerial;
    private String serviceName;
    private String appCode;
    private String clientIp;
    private String pinput;
    private String poutput;
    private Date executeDate;
    private BigDecimal statusCode;
    private String statusDesc;
    private String fullStatusDesc;
    private String note;
    private BigDecimal servId;
    private String serverIp;
    private String f5Ip;
    private String sourceType;
    private Date callDate;
    private Date beforeCallDate;
    private Date afterCallDate;
    private String userCode;
    private BigDecimal dirCode;
    private String processId;
}
