package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    /**
     * 插入订单数据
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 根据订单号查询订单
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);

    /**
     * 分页查询订单
     * @param ordersPageQueryDTO
     * @return
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    Orders getById(Long id);

    /**
     * 查询订单状态个数
     * @param status
     * @return
     */
    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);

    /**
     * 通过订单状态和是否超时获取订单列表
     * @param status
     * @param time
     * @return
     */
    @Select("select * from orders where status = #{status} and order_time = #{time}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime time);

    /**
     * 根据map统计营业额
     * @param map
     * @return
     */
    Double sumByMap(Map map);

    /**
     * 根据map统计订单数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);

    /**
     * 查询销量排名top10
     * @param begin
     * @param end
     * @return
     */
    List<GoodsSalesDTO> getSalesTop10(LocalDateTime begin, LocalDateTime end);

}
