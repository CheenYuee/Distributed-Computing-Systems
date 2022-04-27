# Student Info

## 难易程度：* 易

## 待完成:

* 请在 DSPPCode.mapreduce.student_info.impl 中创建 StudentInfoMapperImpl 和 StudentInfoReducerImpl, 分别继承 StudentInfoMapper 和 StudentInfoReudcer, 实现抽象方法

## 题目描述:

* 某校统计了在校学生的性别和身高数据，现要求对这些数据进行处理以分别计算出男生身高的最大值与男生平均身高的差，女生身高的最小值与女生平均身高的差。

* 输入格式:

  数据保存在文件中，文件的每行由学生的序号、性别以及身高信息(单位为cm)组成。信息之间用逗号分隔。

  ```
  1,F,170
  2,M,178
  3,M,174
  4,F,165
  ```

* 输出: 请输出性别和对应差值，中间用制表符分隔。
    ```
  F	-2.5
  M	2.0
  ```