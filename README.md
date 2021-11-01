# android_base_library

#### 介绍
此项目是本人构建Android项目的基准库，包含一些常用的依赖库，自用的基础组件等，方便之后快速构建Android项目，持续更新。

项目包含的常用组件有：
- MVP基础类：Activity,Fragment，View、Model、Presenter等基类
- 常用工具：SP、定时器、状态栏、文件读写、时间、字符串校验、常用加密
- 常用组件：底部Table、顶部状态栏、带红点的TextView、功能列表（支持图片、文字、文字红点）、OptionView、Dialog(支持SweetAlert风格和IOS风格)、
  MyEditText(自定义文本输入框，支持左边图片右边文字)、底部选项框、Loading框
- 网络请求：基于retrofit2封装、支持文件上传
- 日志模块：支持控制台打印、文件、UDP网络日志（需配置项目中的LogViewer日志工具使用）三种模式
- 硬件功能：二维码、条形码扫描识别
- 事件总线：RxBus


#### 导入
Add it in your root build.gradle at the end of repositories:

    allprojects {
    		repositories {
    			...
    			maven { url 'https://jitpack.io' }
    		}
    	}
  
 Add the dependency

 	implementation 'com.gitee.allen056:android_base_library:1.0.7'
	//依赖的其他开源库
    implementation 'io.reactivex.rxjava2:rxjava:2.2.10'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.6.0'
    implementation 'com.squareup.retrofit2:retrofit:2.6.0'
    implementation 'com.alibaba:fastjson:1.1.70.android'
    implementation 'com.pnikosis:materialish-progress:1.0'
    implementation 'com.contrarywind:Android-PickerView:4.1.9'
    implementation 'com.readystatesoftware.systembartint:systembartint:1.0.3'


#### 更新日志
##### V1.0.8
- 1、支持UDP日志打印，方便查看

##### V1.0.7
- 1、已知问题修复

##### V1.0.1
- 1、增加了支持右上角红点显示的RedTipTextView
- 2、增加通用的菜单列表ClickItemView

##### V1.0.0
- 第一次发布