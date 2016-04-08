# java_hive

# 准备数据 #
```  
hive> create table hive_udaf_double(id double, v1 double, v2 double, name string) row format delimited fields terminated by '/t';
OK
Time taken: 0.668 seconds


$ sudo vim /soft/hive_udaf_double.txt
1001/t98.5/t90/tName1001
1002/t90.6/t88.2/tName1002
1003/t89.3/t80.5/tName1003
1004/t99.5/t89.6/tName1004


hive> load data local inpath '/soft/hive_udaf_double.txt' into table hive_udaf_double;
Loading data to table default.hive_udaf_double
OK
Time taken: 0.778 seconds


hive> select * from hive_udaf_double;
OK
1001.0  NULL    NULL    tName1001
1002.0  NULL    NULL    tName1002
1003.0  NULL    NULL    tName1003
1004.0  NULL    NULL    tName1004
Time taken: 0.152 seconds, Fetched: 4 row(s)


```  




# Hive UDF #

```  
hive> add jar /soft/java_hive-0.0.0.1.jar;
Added [/soft/java_hive-0.0.0.1.jar] to class path
Added resources: [/soft/java_hive-0.0.0.1.jar]


hive> create temporary function HelloString as 'com.highill.practice.hive.udf.HelloUDF';
OK
Time taken: 0.293 seconds

hive> show functions;

hive> select HelloString(name) from hive_udaf_double;
OK
Hello, tName1001
Hello, tName1002
Hello, tName1003
Hello, tName1004
Time taken: 0.155 seconds, Fetched: 4 row(s)


hive> select hellostring(id) from hive_udaf_double where id > 1002;
OK
Hello, 1003.0
Hello, 1004.0
Time taken: 0.13 seconds, Fetched: 2 row(s)


hive> select hellostring(name) from hive_udaf_double where id > 1000 limit 3;
OK
Hello, tName1001
Hello, tName1002
Hello, tName1003
Time taken: 0.181 seconds, Fetched: 3 row(s)



hive> drop temporary function hellostring;
OK
Time taken: 0.005 seconds


hive> delete jar /soft/java_hive-0.0.0.1.jar;
Deleted [/soft/java_hive-0.0.0.1.jar] from class path

```  



# Hive UDAF #

```  



hive> add jar /soft/java_hive-0.0.0.1.jar;
Added [/soft/java_hive-0.0.0.1.jar] to class path
Added resources: [/soft/java_hive-0.0.0.1.jar]

hive> create temporary function sumLong as 'com.highill.practice.hive.udaf.SumUDAF';
OK
Time taken: 3.871 seconds

hive> show functions;


hive> select sumLong(id) from hive_udaf_double;
FAILED: NoMatchingMethodException No matching method for class com.highill.practice.hive.udaf.SumUDAF with (double). Possible choices:

# https://cwiki.apache.org/confluence/display/Hive/DeveloperGuide#DeveloperGuide-UDFsandUDAFs
# UDAF写法可能需要改变(也可能代码有错误)


```  


