package edu.wong.entities;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 表名：t_storage
 */
@Table(name = "t_storage")
@ToString
public class Storage implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 总库存
     */
    private Long total;

    /**
     * 已用库存
     */
    private Long used;

    /**
     * 剩余库存
     */
    private Long residue;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return productId - 用户ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置用户ID
     *
     * @param productId 用户ID
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取总库存
     *
     * @return total - 总库存
     */
    public Long getTotal() {
        return total;
    }

    /**
     * 设置总库存
     *
     * @param total 总库存
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * 获取已用库存
     *
     * @return used - 已用库存
     */
    public Long getUsed() {
        return used;
    }

    /**
     * 设置已用库存
     *
     * @param used 已用库存
     */
    public void setUsed(Long used) {
        this.used = used;
    }

    /**
     * 获取剩余库存
     *
     * @return residue - 剩余库存
     */
    public Long getResidue() {
        return residue;
    }

    /**
     * 设置剩余库存
     *
     * @param residue 剩余库存
     */
    public void setResidue(Long residue) {
        this.residue = residue;
    }
}