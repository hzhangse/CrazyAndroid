package com.train.activity.other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import android.app.Activity;
import android.util.Log;

public class FindClassUtils {

	public static void findClassInPackageByFile(String packageName,
			String filePath, final boolean recursive, List<Class> clazzs) {
		File dir = new File(filePath);
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		// �ڸ�����Ŀ¼���ҵ����е��ļ������ҽ�����������
		File[] dirFiles = dir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				boolean acceptDir = recursive && file.isDirectory();// ����dirĿ¼
				boolean acceptClass = file.getName().endsWith("class");// ����class�ļ�
				return acceptDir || acceptClass;
			}
		});

		for (File file : dirFiles) {
			if (file.isDirectory()) {
				findClassInPackageByFile(packageName + "." + file.getName(),
						file.getAbsolutePath(), recursive, clazzs);
			} else {
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				try {
					clazzs.add(Thread.currentThread().getContextClassLoader()
							.loadClass(packageName + "." + className));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static List<Class> getClasssFromPackage(String pack) {
		List<Class> clazzs = new ArrayList<Class>();

		// �Ƿ�ѭ�������Ӱ�
		boolean recursive = true;

		// ������
		String packageName = pack;
		// ������Ӧ��·������
		String packageDirName = packageName.replace('.', '/');

		Enumeration<URL> dirs;

		try {
			dirs = Thread.currentThread().getContextClassLoader()
					.getResources(packageDirName);
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();

				String protocol = url.getProtocol();

				if ("file".equals(protocol)) {
					System.out.println("file���͵�ɨ��");
					//Log.v("-error-" , "file���͵�ɨ��");
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					findClassInPackageByFile(packageName, filePath, recursive,
							clazzs);
				} else if ("jar".equals(protocol)) {
					System.out.println("jar���͵�ɨ��");
					//Log.v("-error-" , "jar���͵�ɨ��");
				} else {
					//Log.v("-error-" , "other type scan");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			//Log.v("-error-" , e.getLocalizedMessage());
		}

		return clazzs;
	}

	public static List<Class> getClasssFromPackage(String pack,Class parent) {
		List<Class> clazzs = new ArrayList<Class>();
		List<Class> classlist = getClasssFromPackage(pack);
		for (Class cls:classlist){
			if (parent.isAssignableFrom(cls)){
				clazzs.add(cls);
			}else{
				System.out.println(cls.getName());
			}
		}
		return clazzs;
	}

	public List<Class> getClasssFromJarFile(String jarPaht, String filePaht) {
		List<Class> clazzs = new ArrayList<Class>();

		JarFile jarFile = null;
		try {
			jarFile = new JarFile(jarPaht);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		List<JarEntry> jarEntryList = new ArrayList<JarEntry>();

		Enumeration<JarEntry> ee = jarFile.entries();
		while (ee.hasMoreElements()) {
			JarEntry entry = (JarEntry) ee.nextElement();
			// �������ǳ�������������Ķ���
			if (entry.getName().startsWith(filePaht)
					&& entry.getName().endsWith(".class")) {
				jarEntryList.add(entry);
			}
		}
		for (JarEntry entry : jarEntryList) {
			String className = entry.getName().replace('/', '.');
			className = className.substring(0, className.length() - 6);

			try {
				clazzs.add(Thread.currentThread().getContextClassLoader()
						.loadClass(className));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return clazzs;
	}

	public static void getStream(String jarPaht, String filePaht) {
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(jarPaht);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Enumeration<JarEntry> ee = jarFile.entries();

		List<JarEntry> jarEntryList = new ArrayList<JarEntry>();
		while (ee.hasMoreElements()) {
			JarEntry entry = (JarEntry) ee.nextElement();
			// �������ǳ�������������Ķ����������fileName��ָ��һ��������ļ��Ķ����������·��������com/mypackage/test.txt
			if (entry.getName().startsWith(filePaht)) {
				jarEntryList.add(entry);
			}
		}
		try {
			InputStream in = jarFile.getInputStream(jarEntryList.get(0));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String s = "";

			while ((s = br.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FindClassUtils classUtil = new FindClassUtils();
		List<Class> clazzs = classUtil.getClasssFromPackage("com.train",Activity.class);//
		for (Class clazz : clazzs) {
			//System.out.println(clazz.getName());
		}

//		clazzs = classUtil.getClasssFromJarFile("Jar�ļ���·��", "Jar�ļ�����İ�·��");
//		for (Class clazz : clazzs) {
//			System.out.println(clazz.getName());
//		}
//
//		classUtil.getStream("Jar�ļ���·��", "Jar�ļ��ܵ�һ�������ļ���·��");

	}

}
