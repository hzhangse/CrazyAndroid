package com.train.ws;


public class User
{
	private Integer id;
	private String name;
	private String gender;
	private double height;

	//无参数的构造器
	public User()
	{
	}
	//初始化全部属性的构造器
	public User(Integer id , String name , String gender , double height)
	{
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.height = height;
	}

	//id属性的setter和getter方法
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
	}

	//name属性的setter和getter方法
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	//gender属性的setter和getter方法
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public String getGender()
	{
		return this.gender;
	}

	//height属性的setter和getter方法
	public void setHeight(double height)
	{
		this.height = height;
	}
	public double getHeight()
	{
		return this.height;
	}
	//重写toString() 方法
	public String toString()
	{
		return "Person[name=" + name + ",gender=" + gender
			+ ",height=" + height + "]";
	}

}