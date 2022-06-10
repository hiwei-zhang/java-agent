# java-agent
使用java agent实现一些功能
# 方法mock返回值
mock.json中添加mock数据
格式：
{
  "com.hiwei.test.DemoService":{
    "add":"return 123;",
    "delete":"return \"mock delete\";",
    "getUser": "com.hiwei.test.User user2 = new com.hiwei.test.User();return user2;"
  }
}
{
  "全限定类名":{
    "方法名":"mock代码"
  }
}