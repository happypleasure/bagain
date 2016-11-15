package com.bagain.mongodb.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Hello world!
 *
 */
@Document(collection = "b_hospital")
public class VoTest 
{
	@Id
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 地区编码
     **/
    private Integer province;
    /**
     * 地区编码
     **/
    private Integer city;
    /**
     * 地区编码
     **/
    private Integer country;
    /**
     * 医院等级
     **/
    private String level;
    /**
     * 医院经度
     **/
    private String lat;
    /**
     * 医院维度
     **/
    private String lng;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getCountry() {
		return country;
	}
	public void setCountry(Integer country) {
		this.country = country;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
    
}
