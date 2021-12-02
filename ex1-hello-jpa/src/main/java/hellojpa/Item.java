package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity(name = "item_table")
@Table(name = "item_table", catalog = , schema = , uniqueConstraints = )
public class Item {


    @Id
    @GeneratedValue
    private Long id;

    private String productName;
    //상품명

    private String present_image;
    // 대표이미지
    private Set<String> images;
    // 상세이미지

    private String briefDescription;
    //요약설명 - (상품 상세정보 상단에 출력됩니다. Facebook 다이내믹 광고시 필수) SEO


    private



}

/*
있어도 되고 없어도 되는 항목

모바일 보기
- 모바일 상세설명 - 이 기능을 사용하면 모바일 기기로 접속시 기본 상세설명 대신 모바일 상세설명이 노출됩니다.
상세설명과 동일 / 사용하기


- 기본하단 공통 사용 / 사용안함



 */


/*

재고

재고관리여부 - 재고관리 안함 / 재고관리 함
재고번호(SKU) - 이 기능을 사용하면 모바일 기기로 접속시 기본 상세설명 대신 모바일 상세설명이 노출됩니다.

 */


/*
상품 옵션


 */


