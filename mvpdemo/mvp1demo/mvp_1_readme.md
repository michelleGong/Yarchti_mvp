# 要解决的问题

1. 做分层，更加便于工作分配。功能业务逻辑，视图逻辑，业务主线的控制，可以解耦，这样更加利于我们工作的开展。
2. 规范。我们在分层方式上达成共识后，一些约束，大家共同遵守，为我们后续程序可读性，减少我们联调等协同工作的工作量。
3. 该模式的优缺点： 使用Activity或者Fragment作为Presenter，可以利用Activity或者Fragment的声明周期做业务控制，例如在声明周期里对service，广播等的操作。但同时这就放弃了Activity和Fragment操作VIew的便利性。一些view操作的组件，例如FragmentManager等需要利用属性set进去。

# 1. MVP1使用

参考archti_1_mvp

本模式的基类中定义好了三个接口，如下：

**1. Model层基类接口，IBusiness。**
​    再该接口中，定义了一个统一的callback接口
```java
public interface IBusiness {

    interface Callback<T> {

        void onResult(T result);
        void onError(Throwable e);
    }

}
```

使用时，通过定义接口继承IBusiness，来定义模块M层的接口。

**2. View层基类接口，IView。**

​     使用时，通过定义接口继承IView，来定义模块View层的接口。

**3.Presenter层基类接口，IPresenter。 **

​    使用时，通过定义接口继承IView，来定义模块View层的接口。



## 一、定义各层的接口

规范建议采用一个Constract接口来作为该模块的合约接口，各层的接口作为内部接口定义，可读性更好。

```
1.命名规则： [模块代号]Constract ,eg:PatientDetailConstract
2.三层接口均作为内部元素定义，命名规则：I[模块代号][层] ，eg:IPatientDetailModel
3.声明的接口，请写明确清晰注释
```

示例代码（完整代码请参考MVP1demo->mvp1test.mvp1_test1->PatientDetailConstract.java）：

```java

public interface PatientDetailConstract {

    interface IPatientDetailModel extends IBusiness{
		//声明各种业务层接口
    }

    interface IPatientDetailView extends IView{
		//声明各种View层的视图逻辑接口
    }

    interface IPatientDetailPresenter extends IPresenter{
        //声明各种Presenter层的模块逻辑控制接口
    }
}
```





## 二、实现该模块的Model层

Model层的具体实现，只需要定义普通java类实现Constract中定义的Model层的接口，即可。 

```
1.命名规则： [模块代号]Business ,eg:PatientDetailBusiness
(使用Business做后缀，而没用Model的原因在于，根据不同的习惯对Model的定义有区别，例如数据模型的命名等，因此这里用Business做后缀，避免歧义)

```

示例代码如下(完整代码请参考mvp1demo->mvp1test.mvp1_test1->PatientDetailBusiness.java)：

```
public class PatientDetailBusiness implements PatientDetailConstract.IPatientDetailModel{
  
}
```





## 三、  实现该模块的View层

View层的具体实现，只需要定义普通java类继承BaseViewDelegate，并实现Constract中定义的该模块的View接口即可，示例代码如下（完整代码请参考mvp1demo->mvp1test.mvp1_test1->PatientDetailViewDelegate.java）：

```java
public class PatientDetailViewDelegate extends BaseViewDelegate implements PatientDetailConstract.IPatientDetailView {
```

View层也解耦出来，也可单独做任务进行划分。

**BaseViewDelegate的使用如下：**

* 必须实现abstarct 方法getRootLayoutId()指定View层的布局文件。

```
 @Override
    public int getRootLayoutId() {
        return R.layout.activity_patient_detail;
    }
```
* 可选实现abstract方法initView() 来初始化view层，该方法会在下文讲到的ActivityPresenter和FragmentPresenter的 onCreate() 和 onCreateView()生命周期方法中回调

```
 @Override
    public void initView() {
       
    }
```
* 提供属性：View rootView  ：该View层的根View


* 提供public 方法：public View getView(int id)：来通过id获取View组件的实例


* 提供public 方法：public void setOnClickListener(View.OnClickListener l, int... ids)：设置点击监听



## 四、 实现该模块的Presenter层

在本模式中，Activity和Fragment组件作为了Presenter的组件。这样设计的着眼点在于：

1. Presenter本身就是做控制项目逻辑的作用，而本身Activty和Fragment的声明周期方法，也会进行我们一些逻辑控制，例如我们和硬件交互的一些服务的初始化等。可以原封不动的使用Activity本身的生命周期去处理项目逻辑，而不需要强加给另外一个包含类，甚至记忆额外自定义的生命周期。
2. View层只需要让Activity和Fragment持有ViewDelegate对象即可。
3. 也规避了内存泄露的情况。



本模式基类提供了2个基类，ActivityPresenter 和 FragmentPresenter。

1. Presenter层只需要继承ActivityPresenter或者FragmentPresenter并指定好泛型，并且实现该模块Constract定义的Presenter接口接口,并实现相应的接口。

```java
public class PatientDetailActivity extends ActivityPresenter<PatientDetailViewDelegate> implements PatientDetailConstract.IPatientDetailPresenter {
```

 **使用方法：**

* **必须实现抽象方法 getDelegateClass()指定ViewDelegate的类型；则该Fragment或者Activity会持有指定ViewDelegate类型的引用viewDelegate**


* 根据需要，可以通过实现抽象方法initViewDelegate()来设置viewDelegate所需要的一些属性


* 根据需要，可以通过实现抽象方法bindEventListener()来给view控件设置监听。


示例如下完整代码请参考mvp1demo->mvp1test.mvp1_test1->PatientDetailActivity.java：

```java
  @Override
    protected Class<PatientDetailViewDelegate> getDelegateClass() {
        return PatientDetailViewDelegate.class;
    }

    @Override
    protected void initViewDelegate() {
        viewDelegate.setFragmentManager(getFragmentManager());
        viewDelegate.setmActivity(this);
    }

    @Override
    protected void bindEventListener() {
        viewDelegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.bt_addPatient:
                        addPatient();
                        break;
                    case R.id.bt_changeSync:  //测试ViewModel用
                        doFakeBackgroundOperation();
                        break;
                    case R.id.bt_In:
                        doPatientIn();
                        break;
                    case R.id.bt_edit:
                        editPatient();
                        break;
                    case R.id.bt_save:
                        doModifyPatient();
                        break;
                }
            }
        }, R.id.bt_addPatient,R.id.bt_save,R.id.bt_changeSync,R.id.bt_edit,R.id.bt_In);
    }
```



### 1. 其他 

 一、 MVP模式，P关联M和V，该模式中...
1. 该基类中已经通过Presenter的基类，做好了View的关联。使用时无需再做该方面的工作

2. Model需要在Presenter中客户端代码通过new的方式，或者通过依赖注入的方式进行关联。

   ​

二、当需要Activity和Fragment声明周期方法处理项目逻辑控制时...

​        因为该模式下，Activity和Fragment作为Presenter，因此可以直接利用Activity和Fragment的声明周期方法，进行项目逻辑的控制

示例代码：

```
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实例化关联的业务
        patientDetailBusiness = new PatientDetailBusiness();
        //利用生命周期方法，进行项目逻辑的控制
        loadPatientInfo("123");
    }
```

1. 另外一个如果Model设计功能绝对单一的，可能会存在一个Presenter对应多Model的情况。所以此处也未在基类中对Model进行关联。



三、从实际项目开发的角度

1. Model层代码，View层代码，Presenter层代码完全解耦，这三层的代码，完全可以由不同的人负责。
2. 各层负责人员可单独进行单元测试。



四、 关于如果需要弹出对话框

​		首先，我们做分层并不是程序中的任何一个Activity，或者任何一个Fragment就是一个Presenter。而是靠业务模块去划分的。所以，有的时候，即便弹出一个dialog，这个工作交由view层去弹出就可以。那么dialog上有点击事件处理，怎么办？view层提供接口，还是交由presenter就完成点击事件的处理。

​           我觉得如果需要弹对话框，一般的情景是某个模块中，存在个业务环节是对在对话框中进行，（包括像AdapterVIew中的一些事件也属于这种情况），这个时候，还是在这个把这个业务环节开始的Actvity或者Fragment作为Presenter，这个Dialog或者AdapterView中的事件，做接口回调，在Presenter中做处理。不需要嵌套ViewDelegate。

​	   这样从分层的角度，不会破坏分层，又省去了一些繁琐代码。









# 2.mvp1 支持数据绑定

本模式轻量，也可支持多种，View -  DataModel 数据模型之间可以进行绑定的方式。 





## 2.1 对DataBinding的支持

Google退出的DataBinding组件，可以支持双向绑定，即可以做到用户在View控件上做了修改之后，数据模型随之变化；也可以做到数据模型发生变化之后，通过DataBinding做VIew的数据刷新。但是前者教繁琐，后者还是比较好用的。














