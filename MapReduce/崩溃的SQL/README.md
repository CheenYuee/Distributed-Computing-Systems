# 崩溃的SQL

## 难易程度：** 中

## 待完成
- 请在DSPPCode.mapreduce.crashed_sql.impl中创建SQLMapperImpl，继承SQLMapper，实现抽象方法
- 请在DSPPCode.mapreduce.crashed_sql.impl中创建SQLReducerImpl，继承SQLReducer，实现抽象方法

## 题目描述：

- DASE店铺在开业一周年之际，决定通过以下活动来回馈新老用户，即在过去一年内订单金额前5的订单可以享受八折优惠。Tom是一名SQL开发人员，他为了从历史订单表中找出总金额前五的订单，很轻松地就写出了相应的SQL语句
**SELECT id,UserName,SUM(Price) total FROM orders ORDER BY total DESC LIMIT 5;**
但海量的订单导致该SQL查询在单机数据库中执行一段时间后就崩溃而无法得到结果，Tom只好求助作为大数据开发工程师的你，希望你能帮他实现该SQL语句的功能。
请你通过MapReduce程序来实现上述SQL语句的功能。
  * 输入格式：每行表示一条订单记录，第一列到最后一列分别表示订单Id，用户名，购买产品，单价(int)，购买数量(int)，订单日期，每列之间用制表符分隔。

    ```
    Id UserName Product Price Number Date
    u00001 rookie	文化衫  45   20    2021-6-11
    u00003 lucy	贺卡  15   35    2021-9-08
    u00027 wang	钢笔  27   10    2021-3-22
    u00102 anni	鼠标  108   1    2021-5-27
    u00004 lucy	水杯  98   1    2021-11-09
    u00005 rookie	教材  40   2    2021-9-01
    u00019 lucy	文化衫  45   4    2021-10-03
    u00110 song	教材  55   1    2021-9-01
    u00111 shy	教材  35   1    2021-9-02
    u00311 shy	本子  20   2    2021-9-02
    ```

  * 输出格式: 请输出订单Id，UserName，total，每列之间用制表符分隔
     ```
    u00001	rookie	900
    u00003	lucy	525
    u00027	wang	270
    u00019	lucy	180
    u00102	anni	108
     ```

