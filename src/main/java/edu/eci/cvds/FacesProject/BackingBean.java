package edu.eci.cvds.FacesProject;

import java.util.*;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "calculadoraBean")

//@ApplicationScoped
@SessionScoped
public class BackingBean {

	private ArrayList<Float> datosFloat;
	private ArrayList<String> datosTable;
	private String datos;
	private float promedio;
	private float desviacionEstandar;
	private float varianza;
	private float moda;
	
	public BackingBean() {
		datosFloat = new ArrayList<>();
		datosTable = new ArrayList<>();
	}
	
	public void calculateValues(String datos) {
		datosTable.add(datos);
	
		convertToArray(datos);
		promedio = calculateMean(datosFloat);
		desviacionEstandar = calculateStandardDeviation(datosFloat);
		varianza = calculateVariance(datosFloat);
		moda = calculateMode(datosFloat);
	}
			
	public void convertToArray(String datos) {
		String[] datosCadena =  datos.split(",");
		for (String s : datosCadena){
			datosFloat.add(Float.parseFloat(s));	
		}
	}
	
	/**
	 * Calcula la media de los valores ingresados
	 * @param datosFloat - listado de valores float
	 * @return promedio - El promedio de los valores en la lista.
	 */
	public float calculateMean(ArrayList<Float> datosFloat) {
		promedio = 0;
		for (Float i : datosFloat){ 
			promedio += i ;
			promedio = promedio / datosFloat.size();
		}
	    return promedio;
	}
	
	/**
	 * Calcula la desviación estándar de los valores ingresados
	 * @param datosFloat - listado de valores float
	 * @return desviacionEstandar - Retorna la desviación estandar de los valores en la lista
	 */
	public float calculateStandardDeviation(ArrayList<Float> datosFloat) {
		desviacionEstandar = 0;
		desviacionEstandar =(float) Math.pow(calculateVariance(datosFloat), 0.5);
		return desviacionEstandar;
	}
		 
	/**
	 * Calcula la varianza de los valores ingresados
	 * @param datosFloat - listado de valores float
	 * @return varianza - Retorna la varianza de los valores en la lista
	 */
	public float calculateVariance(ArrayList<Float> datosFloat) {
		varianza = 0;
		promedio = calculateMean(datosFloat);
		for (Float i : datosFloat) varianza+= Math.pow(i-promedio,2);
		varianza = varianza / datosFloat.size();
		return varianza;
	}
	
	/**
	 * Calcula la moda de los valores ingresados
	 * @param datosFloat - listado de valores float
	 * @return varianza - Retorna la moda de los valores en la lista
	 */
	public float calculateMode(ArrayList<Float> datosFloat) {
		float moda = 0;
		int maxVeces = 0; 
		int veces = 0;

	    for (int i = 0;i < datosFloat.size(); i++) {
	     	for (int j = 0; j < datosFloat.size(); j++) {
	     		if (datosFloat.get(i).equals(datosFloat.get(j)) && i != j) {
	     			veces++;
	     		}
	        }
	     	if (veces > maxVeces) { 
	           	moda = datosFloat.get(i);
	           	maxVeces=veces;
	        }
	    }
	    return moda;
	}
	
	
	/**
	  * Borra el campo de texto para que el usuario agregue los datos de nuevo.
	  */
	public void restart(){
		promedio = 0;
		desviacionEstandar = 0;
		varianza = 0;
		moda = 0;
		datos = "";
	}
	
	//	GET's
	public String getDatos() {
		return datos;
	}
	
	public float getPromedio() {
		return promedio;
	}
	
	public float getDesviacionEstandar() {
		return desviacionEstandar;
	}
	
	public float getVarianza() {
		return varianza;
	}
	
	public float getModa() {
		return moda;
	}
	
	public ArrayList<String> getDatosTable() {
		return datosTable;
	}
	
	//	SET's
	public void setDatos(String datos) {
		this.datos = datos;
	}

	public void setPromedio(float promedio) {
		this.promedio = promedio;
	}

	public void setDesviacionEstandar(float desviacionEstandar) {
		this.desviacionEstandar = desviacionEstandar;
	}

	public void setVarianza(float varianza) {
		this.varianza = varianza;
	}

	public void setModa(float moda) {
		this.moda = moda;
	}
	
	public void setDatosTable(ArrayList<String> datosTable) {
		this.datosTable = datosTable;
	}
}
