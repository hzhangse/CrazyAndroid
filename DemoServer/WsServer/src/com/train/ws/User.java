package com.train.ws;


public class User
{
	private Integer id;
	private String name;
	private String gender;
	private double height;

	//�޲����Ĺ�����
	public User()
	{
	}
	//��ʼ��ȫ�����ԵĹ�����
	public User(Integer id , String name , String gender , double height)
	{
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.height = height;
	}

	//id���Ե�setter��getter����
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
	}

	//name���Ե�setter��getter����
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	//gender���Ե�setter��getter����
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public String getGender()
	{
		return this.gender;
	}

	//height���Ե�setter��getter����
	public void setHeight(double height)
	{
		this.height = height;
	}
	public double getHeight()
	{
		return this.height;
	}
	//��дtoString() ����
	public String toString()
	{
		return "Person[name=" + name + ",gender=" + gender
			+ ",height=" + height + "]";
	}

}