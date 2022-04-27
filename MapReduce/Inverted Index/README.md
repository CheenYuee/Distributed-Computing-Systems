# Inverted Index

## 难易程度:  ** 中

## 待完成
- 新建DSPPCode.mapreduce.inverted_index.impl文件夹
- 请在DSPPCode.mapreduce.inverted_index.impl中创建InvertedIndexMapperImpl, 继承InvertedIndexMapper, 实现抽象方法。
- 请在DSPPCode.mapreduce.inverted_index.impl中创建InvertedIndexReducerImpl, 继承InvertedIndexReducer, 实现抽象方法。

## 题目描述
倒排索引是 Elasticsearch 中非常重要的索引结构，是从文档单词到文档 ID 的过程。
倒排索引源于实际应用中需要根据属性的值来查找记录。这种索引表中的每一项都包括一个属性值和具有该属性值的各记录的地址。
由于不是由记录来确定属性值，而是由属性值来确定记录的位置，因而称为倒排索引(inverted index)
现实中，倒排索引主要应用于搜索引擎中，用于构建单词到文档的索引，从而能够快速的通过用户的输入查找相关的网页。

本题目需要实现构建倒排索引的过程。具体来说，给定一组英文文档，使用空格进行分词（文档中不包含标点符号），将所有单词转换为小写，并排除停用词（stop word）后，
建立单词的倒排索引(输出key为单词，value为以文件名和单词出现次数组成的字符串，不同文件之间用";"分割
，详见样例)。


## 样例
### 输入

```
//输入由多个文件的文本内容构成，下面列举了两个文件的文本内容 
//www.bbc.comnewsworld-asia-china-60615280
Ukraine invasion Can China do more to stop Russia's war in Ukraine
//www.bbc.comnewsworld-europe-60506682
Ukraine maps Ukraine says Russian ceasefire offer immoral
// stopwords.txt
can
and
to
in
```

### 输出

```
//输出格式为 单词 文件名1:次数1;文件名2:次数2;
Ukraine www.bbc.comnewsworld-asia-china-60615280:2;www.bbc.comnewsworld-europe-60506682:2  
invasion    www.bbc.comnewsworld-asia-china-60615280::1;
China   www.bbc.comnewsworld-asia-china-60615280::1;
do  www.bbc.comnewsworld-asia-china-60615280::1;
more    www.bbc.comnewsworld-asia-china-60615280::1;
stop    www.bbc.comnewsworld-asia-china-60615280::1;
Russia's    www.bbc.comnewsworld-asia-china-60615280::1;
war www.bbc.comnewsworld-asia-china-60615280::1;
maps	www.bbc.comnewsworld-europe-60506682:1;  
says	www.bbc.comnewsworld-europe-60506682:1;  
Russian	www.bbc.comnewsworld-europe-60506682:1;  
ceasefire	www.bbc.comnewsworld-europe-60506682:1;
offer   www.bbc.comnewsworld-europe-60506682:1;
immoral www.bbc.comnewsworld-europe-60506682:1;
```