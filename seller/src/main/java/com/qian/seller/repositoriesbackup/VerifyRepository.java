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
     * @return
     */
    @Query(value = "SELECT CONCAT_WS('|', order_id,outer_order_id,chan_id,chan_user_id,product_id,order_type,amount," +
            "DATE_FORMAT( create_at,'%Y-%m-%d %H:%i:%s')) FROM order_t WHERE order_status = 'success' AND chan_id = " +
            "?1 AND create_at >= ?2 AND create_at < ?3", nativeQuery = true)
    List<String> queryVerificationOrders(String chanId, Date start, Date end);
}
