package ld.gestion_herramientas.util.reportes;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import ld.gestion_herramientas.entity.Herramienta;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class HerramientaExporterPDF {

	private List<Herramienta> listaHerramientas;

	public HerramientaExporterPDF(List<Herramienta> listaHerramientas) {
		super();
		this.listaHerramientas = listaHerramientas;
	}

	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();

		celda.setBackgroundColor(Color.BLUE);
		celda.setPadding(5);

		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);

		celda.setPhrase(new Phrase("ID", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Modelo", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Marca", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Terminal", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Ensamble", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Tipo de Herramienta", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Fecha", fuente));
		tabla.addCell(celda);


		celda.setPhrase(new Phrase("GMT", fuente));
		tabla.addCell(celda);

	}

	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		for (Herramienta herramienta : listaHerramientas) {
			tabla.addCell(String.valueOf(herramienta.getId()));
			tabla.addCell(herramienta.getModelo());
			tabla.addCell(herramienta.getMarca());
			tabla.addCell(herramienta.getTerminal());
			tabla.addCell(herramienta.getEnsamble());
			tabla.addCell(herramienta.getTipoHerramienta());
			tabla.addCell(herramienta.getFechaProximoMantenimiento().toString());
			tabla.addCell(herramienta.getGmt());

		}
	}

	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());

		documento.open();

		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.BLUE);
		fuente.setSize(18);

		Paragraph titulo = new Paragraph("Lista de Herramientas", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);

		PdfPTable tabla = new PdfPTable(8);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] { 1f, 3f, 3f, 3f, 3.5f, 2.8f, 2.8f, 2f });
		tabla.setWidthPercentage(110);

		escribirCabeceraDeLaTabla(tabla);
		escribirDatosDeLaTabla(tabla);

		documento.add(tabla);
		documento.close();
	}



}
