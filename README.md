# wechat-tools
## 微信公众号快速开发框架

本框架是帮助开发人员快速开发微信公众号后端服务器    
方便开发人员只需要关注业务实现，抛弃繁琐的公众号接入 

若有什么BUG或建议，请联系：easun_zhu@foxmail.com，谢谢支持~ 

### 依赖jar包：
httpclient  
commons-lang3  
fastjson  
slf4j-api  

# 接入教程
框架已默认实现主要服务类DefaultServiceHandler  
只需要在servlet实现的时候进行初始化服务类，并添加微信参数，添加消息处理器即可使用  
消息处理器提供Handle接口，实现具体业务逻辑就行，需要处理多个消息类型，就实现多个  

## 初始化服务类
可在init()方法中进行初始化  
// 设置微信公众号参数，若消息传输不是加密类型，encodingAesKey可为空  
WxConfig wxConfig = new WxConfig("appId","appSecret","token", encodingAesKey");  
// 开启框架日志，服务类已将接入完全封装，若想打印消息日志，请开启框架日志，默认为关闭，框架日志不影响项目日志  
ToolLog.on_off(true);
  
// 初始化核心处理器  
serviceHandler = DefaultServiceHandler.createService();  
// 初始化微信参数  
serviceHandler.initConfig(wxConfig);

// 非事件消息处理器，用于管理所有非事件消息处理器  
MessageHandler messageHandler = new MessageHandler();  
// 事件消息处理器，用于管理所有事件消息处理器  
EventHandler eventHandler = new EventHandler();  

// 开发人员的消息处理实现类
Handler textMessageHandler = XXXXX;  
Handler subscribeMessageHandler = XXXXX;  
// 添加到对应的处理器中，并标注该处理类所处理的消息类型  
messageHandler.addHandler(WxConstants.XML_TEXT, textMessageHandler);
eventHandler.addEventHandler(WxConstants.EVT_SUBSCRIBE, bscribeMessageHandler);  
// 将消息处理器添加到服务类中，整个服务类初始化完毕  
serviceHandler.addMessageHandler(messageHandler);  
serviceHandler.addEventHandler(eventHandler);  

## 使用服务类接入  
```  
    /**
     * 微信接入认证
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        serviceHandler.checkConnect(request, response);
    }

    /**
     * 处理微信服务器发来的消息
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        serviceHandler.messageHandle(request, response);
    }
```
# 消息业务处理接口Handler  
开发人员实现本接口，进行对应的消息进行处理，返回消息结果
## 例：  
### 文本消息处理器  
```
public class TextMessageHandler implements Handler {
    @Override
    public String handle(MessageReq msg) throws Exception {
        //  调用图灵机器人
        String content = Turing.tulingAPI(msg.getContent());
        // 使用文本消息转换类返回文本消息
        return TextMessageConvert.convert(msg, content);
    }
}
```
### 用户关注事件消息处理器
```
public class SubscribeMessageHandler implements Handler {
    @Override
    public String handle(MessageReq msg) throws Exception {
         // 使用文本消息转换类返回文本消息
        return TextMessageConvert.convert(msg, "欢迎光临~~~ \n 请领取新手大礼包!!!");
    }
}
```

# 以上是框架基本使用教程，下面是通用工具类，后续会陆续更新。。。

## 返回消息转换工具集 com.easun.wxtool.model.convert  
开发人员无需关心返回消息格式，只需要使用对应的消息转换类就能快速返回

## 菜单工具类 MenuButtonUtil  
实现了普通菜单和个性化菜单的增删查，使用方便   


## 若开发人员不想使用现有的服务类，也可自行接入，本框架内也有大量的工具类，能使接入变的简单方便
