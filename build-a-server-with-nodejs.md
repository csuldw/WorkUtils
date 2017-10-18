默认已经安装好`nodejs`和`npm`，下面使用`express`来搭建web服务器。


```
nodejs v6.9.3
npm v3.10.10
```

#### 安装express插件

```
npm install express
```

#### 新建Nodejs项目

```
express test-server
```

#### 安装依赖

```
cd test-server && npm install
```

#### 修改端口号

进入bin目录，修改www文件中的`port`变量(大概是第15行)，将其改为`3333`，如下：

```
var port = normalizePort(process.env.PORT || '3333');
```

#### 启动服务器

```
npm start
```

#### 查看结果

进入浏览器输入 http://localhost:3333 查看结果。
