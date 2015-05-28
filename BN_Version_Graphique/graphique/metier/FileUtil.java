/**
 * 
 */
package metier;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

/**
 * Classe permettant la gestion de fichiers
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class FileUtil implements Serializable {

	private static final long serialVersionUID = 1L;
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
			e.printStackTrace();
		}
	}

	private static void loadProp() throws IOException {
		FileInputStream f = new FileInputStream(fileName);
		tableProprietes = new Properties();
		tableProprietes.load(f);
	}

	private FileUtil() throws BatailleNavaleException {
		try {
			// Ouverture du fichier cabMed.properties et
			// Chargement des proprietes dans la table des proprietes
			loadProp();

		} catch (Exception e) {
			e.printStackTrace();
			throw new BatailleNavaleException(
					"Erreur de chargement des propriétés !");
		}
	}

	public String getPropriete(String p) throws BatailleNavaleException {

		String prop = null;
		// recuperation des proprietes dans la table
		prop = tableProprietes.getProperty(p);

		if (prop == null) {

			throw new BatailleNavaleException(
					"Erreur de récupération de la propriété !");
		}

		return prop;
	}

	public static synchronized FileUtil getInstance()
			throws BatailleNavaleException {

		// test si les proprietes sont deja chargees ou non
		if (cp == null) {
			cp = new FileUtil();
		}

		return cp;
	}

}