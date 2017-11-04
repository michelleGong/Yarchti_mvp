该模式的优缺点： 使用Activity或者Fragment作为View，普通类做Presenter。优点利用了A和F对View处理的方便性。缺点就是需要在Prsenter里复制一份生命周期，进行生病周期的业务逻辑的控制，例如service和广播接触器的控制的处理不太便利。

# 使用

该模式中，提供定义MVP三层的接口分别如下：

1. **Model层的基类接口，IBusines**

   在该接口中定义了统一的回调接口CallBack。

   ```java
   public interface IBusiness {

       interface Callback<T> {

           void onResult(T result);
           void onError(Throwable e);
       }

   }
   ```

   使用时，通过定义接口继承IBusiness，来定义模块M层的接口。

2. **View层的基类接口，IVIew**

   使用时，通过定义接口继承IView，来定义模块View层的接口。

3. **Presenter层的基类接口，抽象类BasePresenter**

   使用时，通过定义抽象类继承BasePresenter并且通过泛型声明View层的方式，来定义模块P层的接口。



## 一、定义各层的接口

另外，规范建议采用一个Constract接口来作为该模块的合约接口，各层的接口作为内部接口定义，可读性更好。

```
1.命名规则： [模块代号]Constract ,eg:PatientDetailConstract
2.三层接口均作为内部元素定义，命名规则：I[模块代号][层] ，eg:IPatientDetailModel
3.声明的接口，请写明确清晰注释
```
示例代码（完整代码请参考mvp2demo->mvp2test.mvp2_test1->PatientDetailConstract.java）：

```java
public interface PatientDetailConstract {


    public interface IPatientDetailModel extends IBusiness{
        //声明各种业务层接口
    }

    public interface IPatientDetailView extends IView{
       //声明各种View层的视图逻辑接口
    }

    public abstract class IPatientDetailPresenter extends BasePresenter<IPatientDetailView>{
		//声明各种Presenter层的模块逻辑控制接口
    }
}
```





## 二、实现模块的Model层
Model层的具体实现，只需要定义类实现Constract中定义的Model层的接口，即可。 

```
1.命名规则： [模块代号]Business ,eg:PatientDetailBusiness
(使用Business做后缀，而没用Model的原因在于，根据不同的习惯对Model的定义有区别，例如数据模型的命名等，因此这里用Business做后缀，避免歧义)

```

示例代码如下(完整代码请参考mvp2demo->mvp2test.mvp2_test1->PatientDetailBusiness.java)：

```
public class PatientDetailBusiness implements PatientDetailConstract.IPatientDetailModel{
  
}
```





## 二、实现模块的View层

Activity和Fragment作View层的角色，声明Activity或者Fragment继承BaseActivity<P>，并且实现Constract中定义的该模块View层的接口。

```
public class PatientDetailActivity extends BaseActivity<PatientDetailPresenter> implements PatientDetailConstract.IPatientDetailView{
  
}
```

*注意：如果当前Presenter层未声明的话，需要先声明Presenter，做泛型传入*。并且使用注解声明

```
@RequirePresenter(PatientDetailPresenter.class)
```



*  基类BaseActivity提供四个抽象方法需要在模块的View层中具体实现。 
   1. getLayoutID()： 返回布局文件ID；必须实现

   2. initFields():初始化其他属性；根据需要

   3. bindEventListener():设置监听；根据需要，若实现，请将事件的处理，交由Presenter层的人员处理。（接口回掉）

*  基类BaseActivity中提供过了工具类
   * getView(int id):实例化View控件；**请使用这个方法替代findviewbyid**

   * getContext():返回上下文


     **注意：View层的人员，请不要再Activity或者Fragment的生命周期方法里进行业务或者其他代码控制，全部交由Presenter层控制；例如在生命周期方法里注册广播接收器，Service等等的。**







## 三、实现模块的Presenter层


定义类，实现Contract合约接口中定义的该模块的Presenter层的接口，和基类中复制的生命周期方法接口。

```java
public class PatientDetailPresenter extends PatientDetailConstract.IPatientDetailPresenter{
  
  /**
     * 设置监听
     *
     * 这里可以在View层做给View组件设置监听的操作，留出回调接口，在presenter层在该方法里，设置回掉。
     *
     * 测试demo这里，在该方法里直接设置监听，作为演示。
     */
    @Override
    public void setListeners() {
       
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
       
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onResult() {

    }

    @Override
    void editPatient() {
        mView.setUIEditPatient();
    }

    @Override
    void doModifyPatient() {
     
    }

    @Override
    void doFakeBackgroundOperation() {
       
    }

    @Override
    void addPatient() {
        
    }

    @Override
    void doPatientIn() {
       
    }

    @Override
    void loadPatientInfo(String patientId) {
        
    }
}
```

**在该类中需要控制各种生命周期方法和业务接口，因此当你声明好类之后，你发现有好多方法需要实现。其中的业务方法肯定必须要实现了，至于生命周期方法，请根据具体业务需求进行控制**

1. Presenter层基类里已经关联了View层，mView变量即该模块View层类的实例，也就是Activity或者Fragment的实例。
2. 基类提供供线程切换的Handler。
3. 通过方法getView(int id),可以获取该模块View层中的View变量。

 请参考PatientDetailPresenter.java对接口的实现



### 1. 其他 

 一、 MVP模式，P关联M和V，该模式中...
1. 该基类中已经通过Presenter的基类和注解@RequirePresenter  做好了View的关联。使用时无需再做该方面的工作

2. Model需要在Presenter中客户端代码通过new的方式，或者通过依赖注入的方式进行关联。

   ​

二、当需要Activity和Fragment声明周期方法处理项目逻辑控制时...

​        因为该模式下，Activity和Fragment作为View，独立的java类作为Presenter，但是基类中已经复制了A和F的声明周期方法，因此负责View的人员请专注于View层的视图逻辑，不要在生命周期方法中进行逻辑操作。所有的声明周期的处理，交由Presenter处理。

1. 另外一个如果Model设计功能绝对单一的，可能会存在一个Presenter对应多Model的情况。所以此处也未在基类中对Model进行关联。

   ​



三、从实际项目开发的角度

1. Model层代码，View层代码，Presenter层代码完全解耦，这三层的代码，完全可以由不同的人负责。

2. 各层负责人员可单独进行单元测试。

   ​


​		
