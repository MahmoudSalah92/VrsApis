package sa.gov.alriyadh.amana.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "VRS_ROLES", schema = "VRS")
public class VrsRole {
    @Id
    @Column(name = "ROLE_SERIAL", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleSerial;

    @Size(max = 200)
    @NotNull
    @Column(name = "ROLE_DESC", nullable = false, length = 200)
    private String roleDesc;

    @NotNull
    @Column(name = "IS_ACTIVE", nullable = false)
    private Long isActive;

}