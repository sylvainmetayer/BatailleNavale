package com.bataille.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.bataille.application.BatailleNavaleException;



public class FileUtil {

	private static Properties tableProprietes = new Properties();
	private static FileUtil cp = null;
	private static String fileName = "scenario.txt";
	
	public static String getFileName() {
		return fileName;
	}

	public static Properties getTableProprietes() {
		return tableProprietes;
	}

	public static void setTableProprietes(Properties tableProprietes) {
		FileUtil.tableProprietes = tableProprietes;
	}

	public static void setFileName(String fileName) {
		FileUtil.fileName = fileName;
		try {
			loadProp();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void  loadProp() throws IOException{
		FileInputStream f = new FileInputStream(fileName);	
		tableProprietes = new Properties();
		tableProprietes.load(f);		
	}

	private FileUtil() throws BatailleNavaleException {
		try {
			// Ouverture du fichier cabMed.properties et
			// Chargement des propri�t�s dans la table des propri�t�s
			loadProp();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new BatailleNavaleException ("Erreur de chargement des propri�t�s !");
		}
	}
	
		public String getPropriete (String p) throws BatailleNavaleException {
		
		
		
		String prop = null;
		// r�cup�ration des propri�t�s dans la table
		prop = tableProprietes.getProperty(p);
		
		if (prop==null) {
			
			throw new BatailleNavaleException ("Erreur de r�cup�ration de la propri�t� !");
		}
		
		
		
		return prop;
	}
	
	
	public static synchronized FileUtil getInstance() throws BatailleNavaleException {
				
		// test si les propri�t�s sont d�j� charg�es ou non
		if (cp==null) {
			cp = new FileUtil();
		}		
				
		return cp;
	}
	
	
}
