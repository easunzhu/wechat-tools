package com.easun.wxtool.model.pojo;

/**
 * 个性化菜单匹配规则
 * 
 * matchrule共六个字段，均可为空，但不能全部为空，至少要有一个匹配信息是不为空的。
 * country、province、city组成地区信息，将按照country、province、city的顺序进行验证，要符合地区信息表的内容。
 * 地区信息从大到小验证，小的可以不填，即若填写了省份信息，则国家信息也必填并且匹配，城市信息可以不填。 例如 “中国 广东省 广州市”、“中国 *
 * 广东省”都是合法的地域信息，而“中国 广州市”则不合法，因为填写了城市信息但没有填写省份信息。
 * 
 * @author dongxu.zhu
 *
 */
public class Matchrule {

	private String tag_id;
	private String sex;
	private String country;
	private String province;
	private String city;
	private String client_platform_type;
	private String language;

	public String getTag_id() {
		return tag_id;
	}

	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getClient_platform_type() {
		return client_platform_type;
	}

	public void setClient_platform_type(String client_platform_type) {
		this.client_platform_type = client_platform_type;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
