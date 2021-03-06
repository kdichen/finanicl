package com.qian.seller.repositoriesbackup;

import com.qian.entity.VerificationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author : ChenQian
 * @date : 2019/4/16 9:45
 */

public interface VerifyRepository extends JpaRepository<VerificationOrder, String>,
        JpaSpecificationExecutor<VerificationOrder> {

    /**
     * 查询某段时间[start,end)内的某个渠道chanId的对账数据
     *
     * @param chanId 渠道ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 对账数据
     */
    @Query(value = "SELECT CONCAT_WS('|', order_id,outer_order_id,chan_id,chan_user_id,product_id,order_type,amount," +
            "DATE_FORMAT( create_at,'%Y-%m-%d %H:%i:%s')) FROM order_t WHERE order_status = 'success' AND chan_id = " +
            "?1 AND create_at >= ?2 AND create_at < ?3", nativeQuery = true)
    List<String> queryVerificationOrders(String chanId, Date start, Date end);

    /**
     * 查询长款
     *
     * @param chanId 渠道ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 多余的订单
     */
    @Query(value = "SELECT t.`order_id` FROM order_t t LEFT JOIN verification_order v ON t.`chan_id` = ?1 AND t" +
            ".`outer_order_id` = v.`order_id` WHERE v.`order_id` IS NULL AND t.create_at >= ?2 AND t.create_at < ?3",
            nativeQuery = true)
    List<String> queryExcessOrders(String chanId, Date start, Date end);

    /**
     * 查询漏单
     *
     * @param chanId 渠道ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 缺少的订单
     */
    @Query(value = "SELECT v.`order_id` FROM verification_order v LEFT JOIN order_t t ON t.`chan_id` = ?1 AND v" +
            ".`outer_order_id` = t.`order_id` WHERE t.`order_id` IS NULL AND v.create_at >= ?2 AND v.create_at < ?3",
            nativeQuery = true)
    List<String> queryMissOrders(String chanId, Date start, Date end);

    /**
     * 查询不一致的订单
     *
     * @param chanId 渠道ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 不一致的订单
     */
    @Query(value = "SELECT t.order_id FROM order_t t JOIN verification_order v ON t.`chan_id` = ?1 AND t" +
            ".`outer_order_id` = v.`order_id` WHERE CONCAT_WS('|',t.chan_id,t.chan_user_id,t.product_id,t.order_type," +
            "t.amount,DATE_FORMAT( t.create_at,'%Y-%m-%d %H:%i:%s')) != CONCAT_WS('|',v.chan_id,v.chan_user_id,v" +
            ".product_id,v.order_type,v.amount,DATE_FORMAT( v.create_at,'%Y-%m-%d %H:%i:%s')) AND t.create_at >= ?2 " +
            "AND t.create_at < ?3", nativeQuery = true)
    List<String> queryDifferentOrders(String chanId, Date start, Date end);
}
