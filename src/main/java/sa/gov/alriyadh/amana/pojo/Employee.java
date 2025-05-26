package sa.gov.alriyadh.amana.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {


    @NotNull(message = "{REQUEST_NO_REQUIRED}")
    private Long requestNo;

    @NotNull(message = "{EMPLOYEE_CODE_REQUIRED}")
    private String employeeCode;

    private String employeeName;
    private String jobTitle;
    private Long dirCode;


}
