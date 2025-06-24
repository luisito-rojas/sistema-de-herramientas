package ld.gestion_herramientas.util.reportes;

import org.apache.poi.ss.usermodel.CellType;
import ld.gestion_herramientas.entity.Herramienta;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HerramientaExporterExcel {

	private XSSFWorkbook libro;
	private XSSFSheet hoja;

	private List<Herramienta> listaHerramientas;

	public HerramientaExporterExcel(List<Herramienta> listaHerramientas) {
		this.listaHerramientas = listaHerramientas;
		libro = new XSSFWorkbook();
		hoja = libro.createSheet("Herramientas");
	}

	private void escribirCabeceraDeTabla() {
		Row fila = hoja.createRow(0);
		
		CellStyle estilo = libro.createCellStyle();
		XSSFFont fuente = libro.createFont();
		fuente.setBold(true);
		fuente.setFontHeight(16);
		estilo.setFont(fuente);
		
		Cell celda = fila.createCell(0);
		celda.setCellValue("ID");
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(1);
		celda.setCellValue("Modelo");
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(2);
		celda.setCellValue("Marca");
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(3);
		celda.setCellValue("Terminal");
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(4);
		celda.setCellValue("Ensamble");
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(5);
		celda.setCellValue("Tipo de Herramienta");
		celda.setCellStyle(estilo);

		celda = fila.createCell(6);
		celda.setCellValue("Próxima fecha de Mantenimiento");
		celda.setCellStyle(estilo);


		celda = fila.createCell(7);
		celda.setCellValue("GMT");
		celda.setCellStyle(estilo);





	}
	
	private void escribirDatosDeLaTabla() {
		int nueroFilas = 1;
		
		CellStyle estilo = libro.createCellStyle();
		XSSFFont fuente = libro.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);

		for(Herramienta herramienta: listaHerramientas) {
			Row fila = hoja.createRow(nueroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(herramienta.getId());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(herramienta.getModelo());
			hoja.autoSizeColumn(1);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(2);
			celda.setCellValue(herramienta.getMarca());
			hoja.autoSizeColumn(2);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(3);
			celda.setCellValue(herramienta.getTerminal());
			hoja.autoSizeColumn(3);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(4);
			celda.setCellValue(herramienta.getEnsamble());
			hoja.autoSizeColumn(4);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(5);
			celda.setCellValue(herramienta.getTipoHerramienta());
			hoja.autoSizeColumn(5);
			celda.setCellStyle(estilo);

			celda = fila.createCell(6);
			celda.setCellValue(herramienta.getFechaProximoMantenimiento().toString());
			hoja.autoSizeColumn(6);
			celda.setCellStyle(estilo);


			celda = fila.createCell(7);
			celda.setCellValue(herramienta.getGmt());
			hoja.autoSizeColumn(7);
			celda.setCellStyle(estilo);





		}
	}
	
	public void exportar(HttpServletResponse response) throws IOException {
		escribirCabeceraDeTabla();
		escribirDatosDeLaTabla();
		
		ServletOutputStream outPutStream = response.getOutputStream();
		libro.write(outPutStream);
		
		libro.close();
		outPutStream.close();
	}
}
