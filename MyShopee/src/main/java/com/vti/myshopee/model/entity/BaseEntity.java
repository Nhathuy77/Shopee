package com.vti.myshopee.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Data
@MappedSuperclass // đánh dấu class này cũng là 1 phần trong các entity khác

public class BaseEntity {
    @Column(name = "CREATE_DATE")
    protected Date createDate;

    @Column(name = "CREATE_BY")
    protected String createBy;

    @Column(name = "UDPATE_DATE")
    protected Date updateDate;

    @Column(name = "UPDATE_BY")
    protected String updateBy;

    /**
     *
     */
    @PrePersist
    public void onPrePersist() {
        this.createDate = new Date();
        this.createBy = "Huy Create";
    }

    /**
     * Hàm này gọi tới Entity khi đc update
     */
    @PreUpdate
    public void onPreUpdate() {
        this.updateDate = new Date();
        this.createBy = "Huy Update";


//    /**
//     * dùng để thực hiện phép cộng
//     *
//     * @param a : số thứ 1
//     * @param b : số thứ 2
//     * @return Tổng 2 số a và b
//     */
//    public int add(int a, int b){
//        return a+b;
//    }

    }
}
