package modele.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.DocListener;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import modele.metier.Chemin;
import modele.metier.Tournee;

import com.itextpdf.text.Document;

public class SerialiseurFeuilleDeRoute {
	public static Document feuilleDeRoute;
	
	public static Document exportFeuilleDeRoute(ArrayList<Tournee> tournees) throws FileNotFoundException, DocumentException {
		feuilleDeRoute = new Document();
		String date = new SimpleDateFormat("MM.dd.HH.mm.ss").format(new Date());
		File f = new File("feuilleDeRoute " + date + ".pdf");
		FileOutputStream fop = new FileOutputStream(f);
		PdfWriter.getInstance(feuilleDeRoute, fop);
		feuilleDeRoute.open();
		
		Paragraph title = new Paragraph("Recapitulatif des tournees");
		title.setAlignment(Element.ALIGN_CENTER);
		
		feuilleDeRoute.add(title);
		addEmptyLine(2);
		for (int i = 0; i < tournees.size(); i++) {
			ecrireTournee(tournees.get(i), i);
		}
		feuilleDeRoute.close();
		System.out.println(feuilleDeRoute.toString());
		return feuilleDeRoute;
	}
	public static void ecrireTournee(Tournee t, Integer num) throws DocumentException {
		ArrayList<Chemin> listeChemins = t.getListeChemins();
		ajouterParagraphe("Tournee numero " + Integer.toString(num+1)," " , false);
		for (int j = 0; j < listeChemins.size(); j++) {
			ajouterParagraphe("Livraison " + Integer.toString(j), " ", false);
			ajouterParagraphe("Depart", listeChemins.get(j).getIntersectionDepart().toString(), true);
			ajouterParagraphe("Arrivee", listeChemins.get(j).getIntersectionDest().toString(), true);
			ajouterParagraphe("Duree", Integer.toString(listeChemins.get(j).getDuree()), true);
		}
	}
	
	public static void ajouterParagraphe(String paragraphName, String content, boolean addLine) throws DocumentException {
		feuilleDeRoute.add(new Paragraph(paragraphName));
		if (addLine)
			addEmptyLine(1);
		feuilleDeRoute.add(new Paragraph(content));
	}
    
	public static void addEmptyLine(int number) throws DocumentException {
        for (int i = 0; i < number; i++) {
            feuilleDeRoute.add(new Paragraph(" "));
        }
    }
}
