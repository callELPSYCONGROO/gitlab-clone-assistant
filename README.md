#Gitlab Clone Assistant

Gitlab下载助手

> 正常流程跑完程序没有问题，本来想看看怎么做流程控制的，感觉太麻烦了，就没做了。


> 适配不同版本的Gitlab在不同的tag中，请选择使用，直接运行方法中的jar为9.3.3版本gitlab工具


## 运行环境

* windows系统

* jre8

* 已安装git（目前支持9.3/11.8），并配置好环境变量（测试方法：打开cmd，执行代码```git version```）

    > 需要运行gitlab-9.3请使用[1.0](https://github.com/callELPSYCONGROO/gitlab-clone-assistant/releases/tag/1.0)
      
    > 需要运行gitlab-11.8.1请使用[2.0](https://github.com/callELPSYCONGROO/gitlab-clone-assistant/releases/tag/2.0)

* 运行账号能够在本地新建文件夹（测试方法：打开cmd，执行代码```md test```）

* 最新版目前支持指定完整命名空间下载

## 运行方法

直接运行方式：

1. 下载[build](./build)中所有文件

2. 运行[start.bat](./build/start.bat)


源码启动：

1. 运行StartMain即可
