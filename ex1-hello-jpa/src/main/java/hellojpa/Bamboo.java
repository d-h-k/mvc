package hellojpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Optional;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "BAMBOO_TABLE") // user라는 테이블에 저장되도록
public class Bamboo {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;


    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private Long di;

    private String name;


    private Integer age1;
    private int age2;
    private long age3;


    @Enumerated(EnumType.STRING)
    private RoleType roleType1;


    @Enumerated(EnumType.ORDINAL)
    private RoleType roleType2;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date1")
    private ZonedDateTime createdDate1;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date2")
    private ZonedDateTime createdDate2;


    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastModifiedDate;

    @Lob
    private String description;


    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;


    //1:1 관계의 판다 이름을 알고싶으면
    /*
    @JsonProperty("panda_name")
    public String getCompanyName() {
        return Optional.ofNullable(panda)
                .map(PandaDto::getName)
                .map(ZooDtoReq::getName)
                .orElse(null);
    }
    */


    //
    /*
    @JsonProperty("totalPrice")
    public BigDecimal getTotalContractPrice() {
        return purchases.stream()
                .filter(bamboo -> !bamboo.getIsDeleted()) // 필터로 삭제된건지 하나씩 체크한다음에
                .map(purchase -> BigDecimals.of(bamboo.getPricePerOne())  // 개당가격을
                        .multiply(BigDecimal.valueOf(Optional.ofNullable(bamboo.getCount()).orElse(0))) // 개당가격 * 갯수 = 총합계
                        .val())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    */

}
