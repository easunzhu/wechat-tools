package com.easun.wxtool.model;

public class WxConstants {

	// 微信API成功响应码
	public static final String RESULT_SUCCESS = "0";

	// 微信推送过来的消息的类型，和发送给微信xml格式消息的消息类型
	public static final String XML_TEXT = "text";
	public static final String XML_IMAGE = "image";
	public static final String XML_VOICE = "voice";
	public static final String XML_VIDEO = "video";
	public static final String XML_NEWS = "news";
	public static final String XML_MUSIC = "music";
	public static final String XML_LOCATION = "location";
	public static final String XML_LINK = "link";
	public static final String XML_EVENT = "event";
	public static final String XML_TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";

	// 微信端推送过来的事件类型
	public static final String EVT_SUBSCRIBE = "subscribe";
	public static final String EVT_UNSUBSCRIBE = "unsubscribe";
	public static final String EVT_SCAN = "SCAN";
	public static final String EVT_LOCATION = "LOCATION";
	public static final String EVT_CLICK = "CLICK";
	public static final String EVT_VIEW = "VIEW";
	public static final String EVT_MASS_SEND_JOB_FINISH = "MASSSENDJOBFINISH";
	public static final String EVT_SCANCODE_PUSH = "scancode_push";
	public static final String EVT_SCANCODE_WAITMSG = "scancode_waitmsg";
	public static final String EVT_PIC_SYSPHOTO = "pic_sysphoto";
	public static final String EVT_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
	public static final String EVT_PIC_WEIXIN = "pic_weixin";
	public static final String EVT_LOCATION_SELECT = "location_select";
	public static final String EVT_TEMPLATESENDJOBFINISH = "TEMPLATESENDJOBFINISH";
	public static final String EVT_ENTER_AGENT = "enter_agent";

	// 群发消息的消息类型
	public static final String MASS_NEWS = "mpnews";
	public static final String MASS_TEXT = "text";
	public static final String MASS_VOICE = "voice";
	public static final String MASS_IMAGE = "image";
	public static final String MASS_VIDEO = "mpvideo";
	public static final String MASS_WXCARD = "wxcard";

	// 上传多媒体文件的类型
	public static final String MEDIA_IMAGE = "image";
	public static final String MEDIA_VOICE = "voice";
	public static final String MEDIA_VIDEO = "video";
	public static final String MEDIA_THUMB = "thumb";

	// 文件类型
	public static final String FILE_JPG = "jpeg";
	public static final String FILE_MP3 = "mp3";
	public static final String FILE_AMR = "amr";
	public static final String FILE_MP4 = "mp4";

	// 自定义菜单的按钮类型
	/** 点击推事件 */
	public static final String BUTTON_CLICK = "click";
	/** 跳转URL */
	public static final String BUTTON_VIEW = "view";
	/** 扫码推事件 */
	public static final String BUTTON_SCANCODE_PUSH = "scancode_push";
	/** 扫码推事件且弹出“消息接收中”提示框 */
	public static final String BUTTON_SCANCODE_WAITMSG = "scancode_waitmsg";
	/** 弹出系统拍照发图 */
	public static final String BUTTON_PIC_SYSPHOTO = "pic_sysphoto";
	/** 弹出拍照或者相册发图 */
	public static final String BUTTON_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
	/** 弹出微信相册发图器 */
	public static final String BUTTON_PIC_WEIXIN = "pic_weixin";
	/** 弹出地理位置选择器 */
	public static final String BUTTON_LOCATION_SELECT = "location_select";

	/**
	 * 临时二维码
	 */
	public static final String QR_CODE_LIMIT_SCENE = "QR_LIMIT_SCENE";
	/**
	 * 永久二维码
	 */
	public static final String QR_CODE_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";

	// oauth2网页授权的scope
	/** 不弹出授权页面，直接跳转，只能获取用户openid */
	public static final String OAUTH2_SCOPE_BASE = "snsapi_base";
	/** 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息 */
	public static final String OAUTH2_SCOPE_USER_INFO = "snsapi_userinfo";

	// 永久素材类型
	public static final String MATERIAL_NEWS = "news";
	public static final String MATERIAL_VOICE = "voice";
	public static final String MATERIAL_IMAGE = "image";
	public static final String MATERIAL_VIDEO = "video";

	/////////////////////////////////// 请求URL常量//////////////////////////////////////////
	// ACCESSTOKEN
	public static final String URL_GET_ACCESSTOEKN =
			"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 菜单栏
	public static final String URL_CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static final String URL_CREATE_MENU_CONDITIONAL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN";
	public static final String URL_DELETE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	public static final String URL_DELETE_MENU_CONDITIONAL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN";
	public static final String URL_GET_MENU = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	// 多媒体相关
	public static final String URL_UPLOAD_TEMP_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	public static final String URL_DOWNLOAD_TEMP_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	public static final String URL_UPLOAD_MATERIAL_MEDIA =
			"https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
	public static final String URL_BATCHGET_MATERIAL_MEDIA_LIST =
			"https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
	public static final String URL_DOWNLOAD_MATERIAL_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
	public static final String URL_DELETE_MATERIAL_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";
	public static final String URL_GET_MATERIAL_COUNT = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";
	public static final String URL_ADD_NEWS_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
	public static final String URL_UPLOAD_IMAGE_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
	public static final String URL_UPDATE_NEWS_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";

	// 用户分组管理相关
	public static final String URL_CREATE_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
	public static final String URL_QUERY_ALL_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
	public static final String URL_QUERY_USER_GROUP_BY_OPENID = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
	public static final String URL_UPDATE_USER_GROUP_NAME = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
	public static final String URL_MOVING_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
	public static final String URL_BATCH_MOVING_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=ACCESS_TOKEN";
	public static final String URL_DELETE_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";

	// 用户相关
	public static final String URL_UPDATE_USER_REMARK = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";
	public static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public static final String URL_BATCH_GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
	public static final String URL_BATCH_GET_USER_OPENID =
			"https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	public static final String URL_OAUTH2_GET_CODE =
			"https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	public static final String URL_OAUTH2_GET_ACCESSTOKEN =
			"https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public static final String URL_OAUTH2_GET_REFRESH_ACCESSTOKEN =
			"https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	public static final String URL_OAUTH2_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public static final String URL_OAUTH2_CHECK_ACCESSTOKEN = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";

	// 二维码相关
	public static final String URL_GET_QR_CODE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
	public static final String URL_DOWNLOAD_QR_CODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
	public static final String URL_LONGURL_TO_SHORTURL = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";

	// JsapiTicket
	public static final String URL_GET_JS_API_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	// 群发相关
	public static final String URL_GROUP_SEND_ALL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	public static final String URL_OPENID_SEND_ALL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
	public static final String URL_DELETE_SEND_ALL = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN";
	public static final String URL_PREVIEW_SEND_ALL = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";
	public static final String URL_GET_STATUS_SEND_ALL = "https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=ACCESS_TOKEN";

	// 模板消息
	public static final String URL_TEMPLATE_SET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
	public static final String URL_TEMPLATE_GET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
	public static final String URL_TEMPLATE_GET_ID = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
	public static final String URL_TEMPLATE_GET_LIST =
			"https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
	public static final String URL_TEMPLATE_DELETE = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";
	public static final String URL_TEMPLATE_SEND = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	// 图灵机器人
	public static final String URL_TURING_ROBOT = "http://www.tuling123.com/openapi/api?key=apiKey&info=";

}
