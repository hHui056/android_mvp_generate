# android_base_library

#### 介绍
此项目是本人构建Android项目的基准库，包含一些常用的依赖库如（RxJava,PickerView,retrofit,fastJson,自定义的Alert弹框等等），自用的基础组件如（Log,ObjectBox,RxBus等），
此处记录保存方便之后快速构建Android项目。

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
    implementation 'io.reactivex.rxjava2:rxjava:2.1.16'
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.alibaba:fastjson:1.1.70.android'
    implementation 'com.pnikosis:materialish-progress:1.0'
    implementation 'com.contrarywind:Android-PickerView:4.1.9'
    implementation 'com.readystatesoftware.systembartint:systembartint:1.0.3'
    implementation 'org.greenrobot:eventbus:3.2.0'
#### 更新日志
##### V1.0.7
- 1、已知问题修复

##### V1.0.1
- 1、增加了支持右上角红点显示的RedTipTextView
- 2、增加通用的菜单列表ClickItemView

##### V1.0.0
- 第一次发布